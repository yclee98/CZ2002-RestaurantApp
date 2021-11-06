package OrdersPackage;
import CustomerPackage.*;
import TablePackage.TableManager;
import MenuItemPackage.*;
import PromoPackage.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class OrderManager {
    private ArrayList<Order> orderList = new ArrayList<>();
//    private OrdersPackage.InvoiceManager iManager = new OrdersPackage.InvoiceManager();
    private double discount = 0.1;
    private double taxes = 0.07;
    private double service = 0.05;
    private Scanner userInput = new Scanner(System.in);

    public OrderManager(){}

    public void printCustomerAndOrders(){
        System.out.printf("%-20s %20s", "Customers", "OrderID\n");
        System.out.println("----------------------------------------");
        for(int i = 0; i < orderList.size(); i++){
            String custName = orderList.get(i).getCustomer().getCustomerName();
            System.out.printf("%-20s %17s\n", custName, String.valueOf(orderList.get(i).getOrderID()));
        }
        System.out.println("----------------------------------------");
    }

    public long createOrder(long staffID, Customer customer, int inOut, TableManager table_Mngr){
        boolean existOrder = checkOrderExists(customer);
        int tableNo = -1;

        if(!existOrder) {
            if (inOut == 1) {
                tableNo = table_Mngr.findAvailableTable();
                if (tableNo == -1) {
                    System.out.println("ERROR: No tables available");
                }
                else{
                    table_Mngr.setAssign(tableNo, true);
                }
            }
            Order newOrder = new Order(staffID, customer, tableNo);
            orderList.add(newOrder);
            System.out.println("SUCCESS! Order created!");
            return newOrder.getOrderID();
        }
        else{System.out.println("ERROR! Order already exists!");}
        return -1;
    }

    public void addItemToOrder(long orderID, MenuItem newItem, int quantity){
        if(quantity <= 0){
            System.out.println("Please enter a number > 0");
            return;
        }
        for(int i = 0; i < orderList.size(); i++){
            // find the corresponding order using orderID
            if(orderList.get(i).getOrderID() == orderID){
                orderList.get(i).addOrderItem(newItem, quantity);
            }
        }
    }

    public void addItemToOrder(long orderID, PromoSetMeal newItem, int quantity){
        if(quantity <= 0){
            System.out.println("Please enter a number > 0");
            return;
        }
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
                viewOrder(orderID);
                while(true){
                    System.out.print("Please select the index number of the order you want to remove: ");
                    removeItemIndex = userInput.nextInt();
                    if(removeItemIndex > orderList.get(i).getOrderItemList().size() +
                            orderList.get(i).getPromoItemList().size()) {
                        System.out.println("ERROR: Index out of bounds");
                        continue;
                    }
                    System.out.print(" Please state the quantity to remove.");
                    quantity = userInput.nextInt();
                    if(quantity <= 0){
                        System.out.println("Please enter a number > 0");
                        continue;
                    }
                    break;
                }
                orderList.get(i).removeOrderItem(removeItemIndex-1, quantity);
                System.out.println("Please verify ");
                viewOrder(orderID);
            }
        }
        if(!found){
            System.out.println("ERROR: Order not found!");
        }
    }

    public void viewAllOrders(){
        System.out.println("---------------- Orders ----------------");
        for(int i = 0; i < orderList.size(); i++){
            viewOrder(orderList.get(i).getOrderID());
        }
    }

    public void viewOrder(long orderID){
        boolean found = false;
        for(int i = 0; i < orderList.size(); i++){
            if(orderID == orderList.get(i).getOrderID()){
                int j;
                found = true;
                ArrayList<OrderItems> itemsInOrder = orderList.get(i).getOrderItemList();
                ArrayList<OrderPromoItems> promoInOrder = orderList.get(i).getPromoItemList();
                System.out.println("==============================================");
                System.out.println("Order: " + orderID);
                for(j = 0; j < itemsInOrder.size(); j++){
                    System.out.printf("%2d. %2d %-20s \t\t $%-10.2f\n", (j+1), itemsInOrder.get(j).getQuantity(), itemsInOrder.get(j).getItem().getName(),
                            (itemsInOrder.get(i).getItem().getPrice()*itemsInOrder.get(j).getQuantity()));
                }
                for(j = j - itemsInOrder.size(); j < promoInOrder.size(); j++){
                    System.out.printf("%2d. %2d %-20s \t\t $%-10.2f\n", (j+itemsInOrder.size()+1), promoInOrder.get(j).getQuantity(), promoInOrder.get(j).getPromoItem().getName(),
                            (promoInOrder.get(i).getPromoItem().getPrice()*promoInOrder.get(j).getQuantity()));
                }
                System.out.println("----------------------------------------------");
                System.out.printf("Sub-Total = $%.2f\n", orderList.get(i).getTotalPrice());
                System.out.println("==============================================");
            }
        }
        if(!found){
            System.out.println("ERROR: Order " + orderID + " is not found!");
        }
    }

    public void settlePayment(long orderID, InvoiceManager invoice_Mngr){
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
            payOrder.setFinalPaymentPrice(paymentPrice);
            invoice_Mngr.saveOrder(payOrder);
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

    private boolean checkOrderExists(Customer newCust){
        long custID = newCust.getCustomerID();
        for(int i  = 0; i < orderList.size(); i++){
            if(orderList.get(i).getCustomer().getCustomerID() == custID){
                return true;
            }
        }
        return false;
    }

    public boolean checkOrderExists(long OrderID){
        for(int i  = 0; i < orderList.size(); i++){
            if(orderList.get(i).getOrderID() == OrderID){
                return true;
            }
        }
        return false;
    }
}
