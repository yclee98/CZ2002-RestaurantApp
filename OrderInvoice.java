import java.util.ArrayList;

public class OrderInvoice {

    private long orderID;
    private long staffID;
    private int tableNumber;
    private String orderDateTime;
    private long customerID;
    private double totalPrice;
    private double finalPaymentPrice;
    private String orderitems;

    public OrderInvoice(long orderID, long staffID, long customerID, int tableNumber, String orderDateTime,
                        String orderitems, double totalPrice, double finalPaymentPrice){
        this.orderID = orderID;
        this.staffID = staffID;
        this.tableNumber = tableNumber;
        this.orderDateTime = orderDateTime;
        this.customerID = customerID;
        this.totalPrice = totalPrice;
        this.finalPaymentPrice = finalPaymentPrice;
        this.orderitems = orderitems;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getStaffID() {
        return staffID;
    }

    public void setStaffID(long staffID) {
        this.staffID = staffID;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {this.orderDateTime = orderDateTime;}

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public double getFinalPaymentPrice() {
        return finalPaymentPrice;
    }

    public void setFinalPaymentPrice(double finalPaymentPrice) {
        this.finalPaymentPrice = finalPaymentPrice;
    }

    public String getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(String orderitems) {
        this.orderitems = orderitems;
    }

    public String toCSVFormat() {
        return orderID + "," + staffID + "," + customerID + "," + tableNumber + "," + orderDateTime
                + "," + orderitems + "," + totalPrice + "," + finalPaymentPrice;
    }
}
