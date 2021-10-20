import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Order {

    private long orderID;
    private ArrayList<OrderItems> orderItemList = new ArrayList<OrderItems>();
    private long staffID;
    private int tableNumber;
    private String orderDateTime;
    private Customer customer;
    private double totalPrice;
    private double finalPaymentPrice;

    public Order(long sID, Customer customer, int tableNo) {
        Random oIDgen = new Random();
        orderID = 10000000 + oIDgen.nextInt(90000000);
        staffID = sID;
        this.customer = customer;
        tableNumber = tableNo;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        orderDateTime = now.format(format);
        finalPaymentPrice = 0;
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

    public String getOrderDateTime() { return orderDateTime; }

    public ArrayList<OrderItems> getOrderItemList(){
        return orderItemList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getFinalPaymentPrice(){return finalPaymentPrice;}

    public void setFinalPaymentPrice(double finalPaymentPrice) {
        this.finalPaymentPrice = finalPaymentPrice;
    }

    public void addOrderItem(MenuItem orderItem, long quantity) {
        // check if orderItem exists in the list
        boolean found = false;
        for (int i = 0; i < orderItemList.size(); i++) {
            // if item exists in the list, update it
            if (orderItemList.get(i).getItem().getName() == orderItem.getName()) {
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
    }

    public void removeOrderItem(int orderItemIndex, long quantity) {
        // if user specified more quantity than recorded, Assume user wants to remove all of the item order
        if(quantity >= orderItemList.get(orderItemIndex).getQuantity()){
            orderItemList.remove(orderItemIndex);
            totalPrice = totalPrice - orderItemList.get(orderItemIndex).getItem().getPrice() * orderItemList.get(orderItemIndex).getQuantity();
        }
        else{
            orderItemList.get(orderItemIndex).setQuantity(orderItemList.get(orderItemIndex).getQuantity() - quantity);
            totalPrice = totalPrice - orderItemList.get(orderItemIndex).getItem().getPrice() * quantity;
        }
    }


}
