import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class OrderManager {
    private ArrayList<Order> orderList = new ArrayList<Order>();
    private double discount = 0.1;
    private double taxes = 0.07;
    private double service = 0.05;
    private Scanner userInput = new Scanner(System.in);

    public OrderManager(){}

    public long createOrder(long staffID, Customer customer, int tableNo){
        Order newOrder = new Order(staffID, customer, tableNo);
        orderList.add(newOrder);
        return newOrder.getOrderID();
    }

    public void addItemToOrder(long orderID, MenuItem newItem, int quantity){
        for(int i = 0; i < orderList.size(); i++){
            // find the corresponding order using orderID
            if(orderList.get(i).getOrderID() == orderID){
                orderList.get(i).addOrderItem(newItem, quantity);
            }
        }
    }

    public void removeItemFromOrder(long orderID){
        int removeItemIndex, quantity;
        boolean found = false;
        // find the corresponding order
        for(int i = 0; i < orderList.size(); i++){
            if(orderID == orderList.get(i).getOrderID()){
                found = true;
                ArrayList<OrderItems> itemsInOrder = orderList.get(i).getOrderItemList();
                viewOrder(orderID);
                while(true){
                    System.out.print("Please select the index number of the order you want to remove: ");
                    removeItemIndex = userInput.nextInt();
                    System.out.print(" Please state the quantity to remove.");
                    quantity = userInput.nextInt();
                    if(quantity > orderList.get(i).getOrderItemList().size()) {
                        System.out.println("ERROR: Removing more than there is!");
                        break;
                    }
                    break;
                }
                orderList.get(i).removeOrderItem(removeItemIndex-1, quantity);
                System.out.print("Please verify ");
                viewOrder(orderID);
            }
        }
        if(!found){
            System.out.println("ERROR: Order not found!");
        }
    }

    public void viewOrder(long orderID){
        boolean found = false;
        for(int i = 0; i < orderList.size(); i++){
            if(orderID == orderList.get(i).getOrderID()){
                found = true;
                ArrayList<OrderItems> itemsInOrder = orderList.get(i).getOrderItemList();
                System.out.println("Order: " + orderID + " has the following items");
                for(int j = 0; j < itemsInOrder.size(); j++){
                    System.out.println("    "+ (j+1) + ": " + itemsInOrder.get(j).getItem().getName()
                    + " " + itemsInOrder.get(j).getQuantity());
                }
                System.out.printf("    Order price = $%.2f\n", orderList.get(i).getTotalPrice());
            }
        }
        if(!found){
            System.out.println("ERROR: Order " + orderID + " is not found!");
        }
    }

    public void settlePayment(long orderID){
        String userReply;
        Order payOrder = null;
        double paymentPrice;
        boolean found = false;
        for(int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderID() == orderID) {
                found = true;
                payOrder = orderList.get(i);
                break;
            }
        }
        if(!found){
            System.out.println("ERROR: Unable to find order");
            return;
        }
        System.out.println("Paying for order: " + orderID);
        System.out.println("Please verify the order: ");
        viewOrder(orderID);
        paymentPrice = calculateFinalPrice(orderID);
        System.out.println("\nConfirm and proceed to payment? (Y/N): ");
        userReply = userInput.next();
        if(Objects.equals(userReply, "Y")){
            InvoiceManager iManager = new InvoiceManager();
            payOrder.setFinalPaymentPrice(paymentPrice);
            iManager.saveOrder(payOrder);
        }
    }

    public double calculateFinalPrice(long orderID){
        Order payOrder;
        double finalPrice = 0;

        for(int i = 0; i < orderList.size(); i++)
        {
            if(orderList.get(i).getOrderID() == orderID){
                payOrder = orderList.get(i);
                double taxesValue = payOrder.getTotalPrice() * taxes;
                double serviceValue = payOrder.getTotalPrice() * service;
                payOrder.setServiceCharge(serviceValue);
                payOrder.setGstValue(taxes);
                if(payOrder.getCustomer().isMember()){
                    finalPrice = payOrder.getTotalPrice() * (1+taxes+service) * (1-discount);
                    System.out.println("-------------------------------------");
                    double discountValue = payOrder.getTotalPrice() * discount;
                    System.out.printf("    Goods & Service Tax 7%%:     +$%.2f\n", taxesValue);
                    System.out.printf("    MemberShip Discount 10%%:    -$%.2f\n", discountValue);
                    payOrder.setDiscountValue(discountValue);
                }
                else{
                    finalPrice = payOrder.getTotalPrice() * (1+taxes);
                    System.out.println("-------------------------------------");
                    System.out.printf("    Goods & Service Tax 7%%: +$%.2f", taxesValue);
                }

            }
        }
        System.out.printf("Final payment Price = $%.2f", finalPrice);
        return finalPrice;
    }
}
