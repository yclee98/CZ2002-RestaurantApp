public class OrderInvoice {

    private long orderID;
    private long staffID;
    private int tableNumber;
    //*************private String orderDateTime; change to long
    private long orderDateTime;
    private long customerID;
    private double totalPrice;
    private double discount;
    private double serviceCharge;
    private double gST;
    private double finalPaymentPrice;
    private String orderitems;

    //*************change to datetime to long in constructor
    public OrderInvoice(long orderID, long staffID, long customerID, int tableNumber, long orderDateTime, 
                        String orderitems, double totalPrice, double gST, double serviceCharge, double discount, double finalPaymentPrice){
        this.orderID = orderID;
        this.staffID = staffID;
        this.tableNumber = tableNumber;
        this.orderDateTime = orderDateTime;
        this.customerID = customerID;
        this.totalPrice = totalPrice;
        this.finalPaymentPrice = finalPaymentPrice;
        this.orderitems = orderitems;
        this.discount = discount;
        this.gST = gST;
        this.serviceCharge = serviceCharge;
    }

    public long getOrderID() {
        return orderID;
    }

    public long getStaffID() {
        return staffID;
    }
    //*************change to return long
    public long getOrderDateTime() {
        return orderDateTime; 
    }

    public long getCustomerID() {
        return customerID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public double getFinalPaymentPrice() {
        return finalPaymentPrice;
    }

    public String getOrderItems() {
        return orderitems;
    }

    public double getDiscount() {
        return discount;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public double getGST() {
        return gST;
    }

    public String toCSVFormat() {
        return orderID + "," + staffID + "," + customerID + "," + tableNumber + "," + orderDateTime
                + "," + orderitems + "," + totalPrice + "," + gST + "," + serviceCharge + "," + discount + "," +
                finalPaymentPrice;
    }
}
