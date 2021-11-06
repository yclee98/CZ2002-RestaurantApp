package OrdersPackage;
//package RestaurantApp;
//import RestaurantApp.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import MenuItemPackage.MenuItem;
import PromoPackage.*;
import Utility.DateTime;
import CustomerPackage.*;

public class Order {

    private long orderID;
    private ArrayList<OrderItems> orderItemList = new ArrayList<OrderItems>();
    private ArrayList<OrderPromoItems> orderPromoList = new ArrayList<OrderPromoItems>();
    private long staffID;
    private int tableNumber;
    //*************private String orderDateTime; change to long
    private long orderDateTime;
    private Customer customer;
    private double totalPrice;
    private double discountValue;
    private double gstValue;
    private double serviceCharge;
    private double finalPaymentPrice;

    public Order(long staffID, Customer customer, int tableNo) {
        Random oIDgen = new Random();
        orderID = 10000 + oIDgen.nextInt(90000);
        this.staffID = staffID;
        this.customer = customer;
        tableNumber = tableNo;
        //*************change orderDatetime to store epoch time in long 
        // LocalDateTime now = LocalDateTime.now();
        // DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        // orderDateTime = now.format(format);
        orderDateTime = DateTime.getEpochNow();
        finalPaymentPrice = 0;
        discountValue = 0;
        gstValue = 0;
        serviceCharge = 0;
    }

    public long getOrderID() {
        return orderID;
    }

    public long getStaffID() {
        return staffID;
    }

    public void setStaffID(long staffID) {
        this.staffID = staffID;
    }

    public Customer getCustomer(){ return customer; }

    public int getTableNumber(){ return tableNumber; }

    //*************change to long
    public long getOrderDateTime() { 
        return orderDateTime; 
    }

    public ArrayList<OrderItems> getOrderItemList(){
        return orderItemList;
    }

    public ArrayList<OrderPromoItems> getPromoItemList(){
        return orderPromoList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getFinalPaymentPrice(){return finalPaymentPrice;}

    public void setFinalPaymentPrice(double finalPaymentPrice) {
        this.finalPaymentPrice = finalPaymentPrice;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    public void setGstValue(double gstValue) {
        this.gstValue = gstValue;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public double getDiscountValue() {
        return discountValue;
    }

    public double getGstValue() {
        return gstValue;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public void addOrderItem(MenuItem orderItem, long quantity) {
        // check if orderItem exists in the list
        boolean found = false;
        for (int i = 0; i < orderItemList.size(); i++) {
            // if item exists in the list, update it
            if (Objects.equals(orderItemList.get(i).getItem().getName(), orderItem.getName())) {
                orderItemList.get(i).setQuantity(orderItemList.get(i).getQuantity() + quantity);
                found = true;
                break;
            }
        }
        // if not found, add new orderitem
        if (!found) {
            OrderItems newItem = new OrderItems(orderItem, quantity);
            orderItemList.add(newItem);
        }
        totalPrice = totalPrice + orderItem.getPrice() * quantity;
        System.out.println("Item Added!");
    }

    public void removeOrderItem(int orderItemIndex, long quantity) {
        // if user specified more quantity than recorded, Assume user wants to remove all of the item order
        if(orderItemIndex < orderItemList.size()) {
            if (quantity >= orderItemList.get(orderItemIndex).getQuantity()) {
                totalPrice = totalPrice - orderItemList.get(orderItemIndex).getItem().getPrice() * orderItemList.get(orderItemIndex).getQuantity();
                orderItemList.remove(orderItemIndex);
            } else {
                totalPrice = totalPrice - orderItemList.get(orderItemIndex).getItem().getPrice() * quantity;
                orderItemList.get(orderItemIndex).setQuantity(orderItemList.get(orderItemIndex).getQuantity() - quantity);
            }
        }
        else {
            orderItemIndex -= orderItemList.size();
            if (quantity >= orderPromoList.get(orderItemIndex).getQuantity()) {
                totalPrice = totalPrice - orderPromoList.get(orderItemIndex).getPromoItem().getPrice() * orderPromoList.get(orderItemIndex).getQuantity();
                orderPromoList.remove(orderItemIndex);
            } else {
                totalPrice = totalPrice - orderPromoList.get(orderItemIndex).getPromoItem().getPrice() * quantity;
                orderPromoList.get(orderItemIndex).setQuantity(orderPromoList.get(orderItemIndex).getQuantity() - quantity);
            }
        }
    }

    public void addOrderItem(PromoSetMeal promoM, long quantity){
        boolean found = false;
        for (int i = 0; i < orderPromoList.size(); i++) {
            // if item exists in the list, update it
            if (Objects.equals(orderPromoList.get(i).getPromoItem().getName(), promoM.getName())) {
                orderPromoList.get(i).setQuantity(orderPromoList.get(i).getQuantity() + quantity);
                found = true;
                break;
            }
        }
        // if not found, add new orderitem
        if (!found) {
            OrderPromoItems newItem = new OrderPromoItems(promoM, quantity);
            orderPromoList.add(newItem);
        }
        totalPrice = totalPrice + promoM.getPrice() * quantity;
        System.out.println("Meal Added!");
    }
}
