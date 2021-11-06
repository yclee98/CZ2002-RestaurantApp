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

/**
 * This is an entity class that is used to store information about a customer's order
 */
public class Order {
    /**
     * Auto generated ID for orders.
     */
    private long orderID;
    /**
     * List that stores orderItems specific to the order.
     */
    private ArrayList<OrderItems> orderItemList = new ArrayList<OrderItems>();
    /**
     * List that stores orderPromoItems specific to the order.
     */
    private ArrayList<OrderPromoItems> orderPromoList = new ArrayList<OrderPromoItems>();
    /**
     * ID of the staff that took the order.
     */
    private long staffID;
    /**
     * Table number assigned to the customer.
     */
    private int tableNumber;
    /**
     * DateTime the order was made. Requires Epoch time to format.
     */
    private long orderDateTime;
    /**
     * Customer that the order belongs to.
     */
    private Customer customer;
    /**
     * Sub-total price of the order. No charges/discounts applied.
     */
    private double totalPrice;
    /**
     * Discount amount removed from the final payment price.
     */
    private double discountValue;
    /**
     * Goods and Service tax amount added to the final payment price.
     */
    private double gstValue;
    /**
     * Service charge added to the final payment price.
     */
    private double serviceCharge;
    /**
     * Price the customer has to pay in the end.
     */
    private double finalPaymentPrice;

    /**
     * Creates a new order
     * @param staffID ID of the staff that took the order.
     * @param customer Customer object which the newly created order belongs to.
     * @param tableNo Assigned table number of the customer
     */
    public Order(long staffID, Customer customer, int tableNo) {
        Random oIDgen = new Random();
        orderID = 10000 + oIDgen.nextInt(90000);
        this.staffID = staffID;
        this.customer = customer;
        tableNumber = tableNo;
        orderDateTime = DateTime.getEpochNow();
        finalPaymentPrice = 0;
        discountValue = 0;
        gstValue = 0;
        serviceCharge = 0;
    }

    /**
     * Gets the orderID of the order
     * @return ID of the specific order
     */
    public long getOrderID() {
        return orderID;
    }

    /**
     * Gets the ID of the staff who took the order
     * @return StaffID of the staff who took the order
     */
    public long getStaffID() {
        return staffID;
    }

    public void setStaffID(long staffID) {
        this.staffID = staffID;
    }

    /**
     * Gets the customer which this order belongs to
     * @return Customer object this order belongs to
     */
    public Customer getCustomer(){ return customer; }

    /**
     * Gets the table number assigned to the customer who has this orders.
     * @return Table number
     */
    public int getTableNumber(){ return tableNumber; }

    //*************change to long
    /**
     * Gets the date and time this order was taken
     * @return Date and time in long format (requires Epoch time to format)
     */
    public long getOrderDateTime() {
        return orderDateTime;
    }

    /**
     * Gets all the OrderItem objects that is ordered by the customer who this order belongs to
     * @return ArrayList of orderItems objects
     */
    public ArrayList<OrderItems> getOrderItemList(){
        return orderItemList;
    }

    /**
     * Gets all the promo meal objects that is ordered by the customer who this order belongs to
     * @return ArrayList of OrderPromoItem objects
     */
    public ArrayList<OrderPromoItems> getPromoItemList(){
        return orderPromoList;
    }

    /**
     * Gets the sub-total price of the order.
     * @return order sub-total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Gets the discount amount that was applied to the order
     * @return value of the discount in $
     */
    public double getDiscountValue() {
        return discountValue;
    }

    /**
     * Gets the GST amount that was applied to the order
     * @return value of the tax in $
     */
    public double getGstValue() {
        return gstValue;
    }

    /**
     * Gets the service charge that was applied to the order
     * @return value of the charge in $
     */
    public double getServiceCharge() {
        return serviceCharge;
    }

    /**
     * Gets the payment price of the order
     * @return order payment price.
     */
    public double getFinalPaymentPrice(){return finalPaymentPrice;}

    /**
     * Assigns the final payment prices the customer has to pay to the order.
     * @param finalPaymentPrice Price customer has to pay
     */
    public void setFinalPaymentPrice(double finalPaymentPrice) {
        this.finalPaymentPrice = finalPaymentPrice;
    }
    /**
     * Sets the discount value applied to the order
     * @param discountValue value of the discount in $
     */
    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }

    /**
     * Sets the GST value applied to the order
     * @param gstValue value of the charge in $
     */
    public void setGstValue(double gstValue) {
        this.gstValue = gstValue;
    }

    /**
     * Sets the service charge incurred to the order
     * @param serviceCharge value of the charge in $
     */
    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    /**
     * Adds the MenuItem into the order. Requires conversion to orderItems first.
     * Checks if MenuItem has been added before. If true, add the quantity to the existing orderItem quantity
     * Else, add menuitem as new orderItem
     * @param orderItem MenuItem to be added
     * @param quantity Amount of the MenuItem to be added
     */
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

    /**
     * Removes orderItem/MenuItem from a current order.
     * If quantity of the item is more than what is recorded, System prints error.
     * @param orderItemIndex Index of the item which corresponds to the printing of the order.
     * @param quantity Amount of the item to remove.
     */
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

    /**
     * Adds the PromoSetMeal into the order. Requires conversion to OrderPromoItems first.
     * Checks if PromoSetMeal has been added before. If true, add the quantity to the existing OrderPromoItems quantity
     * Else, add PromoSetMeal as new orderItem
     * @param promoM PromoSetMeal to be added
     * @param quantity Amount of the PromoSetMeal to be added
     */
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
