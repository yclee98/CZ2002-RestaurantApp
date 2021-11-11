package OrdersPackage;
import CustomerPackage.*;
import ReservationPackage.Reservation;
import TablePackage.TableManager;
import MenuItemPackage.*;
import PromoPackage.*;
import ReservationPackage.ReservationManager;

import javax.swing.text.Utilities;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * This is the Control class that manages the all the orders that customers create
 */
public class OrderManager {
    /**
     * contains all the active(unpaid) orders.
     */
    private ArrayList<Order> orderList = new ArrayList<>();

    /**
     * The discount percentage applicable only to members.
     */
    private double discount = 0.1;

    /**
     * The GST applicable to all orders.
     */
    private double taxes = 0.07;

    /**
     * The service charge applicable to all orders.
     */
    private double service = 0.05;
    private Scanner userInput = new Scanner(System.in);

    public OrderManager(){}

    /**
     * Prints all the customers currently still active and the orderIDs that belongs to them.
     */
    public void printCustomerAndOrders(){
        System.out.printf("%-20s %20s", "Customers", "OrderID\n");
        System.out.println("----------------------------------------");
        for(int i = 0; i < orderList.size(); i++){
            String custName = orderList.get(i).getCustomer().getCustomerName();
            System.out.printf("%-20s %17s\n", custName, String.valueOf(orderList.get(i).getOrderID()));
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Creates a new order.
     * @param staffID ID of the staff that took the order.
     * @param customer Customer object that the order belongs to.
     * @param inOut Indicates whether the customer is eating in or taking out.
     * @param table_Mngr Used to check and assign the table to the customer.
     */
    public void createOrder(long staffID, Customer customer, int inOut, TableManager table_Mngr, ReservationManager res_Mngr){
        long currentDT = Utility.DateTime.getEpochNow();
        boolean existOrder = checkOrderExists(customer);
        boolean resOrder = false;
        int tableNo = -1;
        int custCont = customer.getCustomerContact();

        if(!existOrder) {
            // If the customer is eating in
            if (inOut == 1) {
                // Find out if the customer has made a reservation
                Reservation cusRes = res_Mngr.returnReservation(custCont);
                // if customer arrives during the time of the reservation
                if(cusRes != null && currentDT >= cusRes.getEpochDateTime() && currentDT < cusRes.getEndDateTime()){
                    System.out.println("NOTICE: Customer has made reservation previously");
                    tableNo = cusRes.getTableNum();
                }
                // if customer has no reservation at the time -> assign any empty table
                else {
                    tableNo = table_Mngr.findAvailableTable();
                    if (tableNo == -1) {
                        System.out.println("ERROR: No tables available");
                        return;
                    } else {
                        table_Mngr.setAssign(tableNo, true);
                    }
                }
            }
            Order newOrder = new Order(staffID, customer, tableNo);
            orderList.add(newOrder);
            System.out.println("SUCCESS! Order created!");
        }
        else{System.out.println("ERROR! Order already exists!");}
    }

    /**
     * Adds a MenuItem (ala carte) to the order.
     * @param orderID ID of the order that the MenuItem is to be added into.
     * @param newItem MenuItem to be added to the order.
     * @param quantity Number of the MenuItem the customer wants to add.
     */
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

    /**
     * Adds a PromoSetMeal to the order.
     * @param orderID ID of the order that the PromoSetMeal is to be added into.
     * @param newItem PromoSetMeal to be added to the order.
     * @param quantity Number of the PromoSetMeal the customer wants to add.
     */
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

    /**
     * Removes an existing item (MenuItem/PromoMenuItem) from the order
     * @param orderID The orderID that the item is to be removed from
     */
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
                            orderList.get(i).getPromoItemList().size() || removeItemIndex < 1) {
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

    /**
     * Prints all existing orders and their order details
     */
    public void viewAllOrders(){
        System.out.println("---------------- Orders ----------------");
        for(int i = 0; i < orderList.size(); i++){
            viewOrder(orderList.get(i).getOrderID());
        }
    }

    /**
     * Prints the all the order details pertaining to the specified orderID
     * @param orderID ID of the order to print details for.
     */
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
                System.out.println("Customer ID: " + orderList.get(i).getCustomer().getCustomerID());
                System.out.println("Customer: " + orderList.get(i).getCustomer().getCustomerName());
                System.out.println("Table No: "+ orderList.get(i).getTableNumber());
                for(j = 0; j < itemsInOrder.size(); j++){
                    System.out.printf("%2d. %2d %-20s \t\t $%-10.2f\n", (j+1), itemsInOrder.get(j).getQuantity(), itemsInOrder.get(j).getItem().getName(),
                            (itemsInOrder.get(j).getItem().getPrice()*itemsInOrder.get(j).getQuantity()));
                }
                for(j = j - itemsInOrder.size(); j < promoInOrder.size(); j++){
                    System.out.printf("%2d. %2d %-20s \t\t $%-10.2f\n", (j+itemsInOrder.size()+1), promoInOrder.get(j).getQuantity(), promoInOrder.get(j).getPromoItem().getName(),
                            (promoInOrder.get(j).getPromoItem().getPrice()*promoInOrder.get(j).getQuantity()));
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

    /**
     * This method finalizes the order and prepares it for payment, final price is calculated and order is
     * converted to invoice if customer agrees to pay. Assigned table will be unassigned.
     * @param orderID orderID of the order to be paid.
     * @param invoice_Mngr object in charge of converting order to invoice.
     * @param table_Mngr used to unassign the table.
     */
    public void settlePayment(long orderID, InvoiceManager invoice_Mngr, TableManager table_Mngr){
        String userReply;
        Order payOrder = null;
        double paymentPrice;
        boolean found = false;
        int i;
        for(i = 0; i < orderList.size(); i++) {
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
            // if customer had been assign a table number, unassign it
            if(payOrder.getTableNumber() != -1){
                table_Mngr.setAssign(payOrder.getTableNumber(), false);
            }
            payOrder.setFinalPaymentPrice(paymentPrice);
            invoice_Mngr.saveOrder(payOrder);
            invoice_Mngr.printInvoice(payOrder.getOrderID());
            orderList.remove(i);
        }
    }

    /**
     * This method calculates the price the customer has to pay. It applies taxes, charges and discounts if
     * applicable
     * @param orderID ID of the order to be paid
     * @return the payment price the customer has to pay
     */
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
                    double discountValue = payOrder.getTotalPrice() * discount;
                    finalPrice = (payOrder.getTotalPrice() * (1+taxes+service)) - discountValue;
                    System.out.printf("    Goods & Service Tax 7%%:     +$%.2f\n", taxesValue);
                    System.out.printf("    Service Charge      5%%:     +$%.2f\n", serviceValue);
                    System.out.printf("    MemberShip Discount 10%%:    -$%.2f\n", discountValue);
                    payOrder.setDiscountValue(discountValue);
                }
                else{
                    finalPrice = payOrder.getTotalPrice() * (1+taxes+service);
                    System.out.printf("    Goods & Service Tax 7%%: +$%.2f\n", taxesValue);
                }
                break;
            }
        }
        System.out.printf("Final payment Price = $%.2f\n", finalPrice);
        System.out.println("=============================================");
        return finalPrice;
    }

    /**
     * Checks if the customer has an existing active order. Prevents recreation of order with same
     * customer.
     * @param newCust the subject customer to check existing orders for.
     * @return true if existing order is found, false if not.
     */
    private boolean checkOrderExists(Customer newCust){
        long custID = newCust.getCustomerID();
        for(int i  = 0; i < orderList.size(); i++){
            if(orderList.get(i).getCustomer().getCustomerID() == custID){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is an order with the specified orderID
     * @param OrderID the subject orderID to find the order
     * @return true if existing order is found, false if not.
     */
    public boolean checkOrderExists(long OrderID){
        for(int i  = 0; i < orderList.size(); i++){
            if(orderList.get(i).getOrderID() == OrderID){
                return true;
            }
        }
        return false;
    }
}
