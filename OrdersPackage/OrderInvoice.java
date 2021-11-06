package OrdersPackage;

import FlatFile.CSVFormat;

/**
 * This is an entity class that records the attributes of the order object after it is paid.
 */
public class OrderInvoice implements CSVFormat{

    /**
     * OrderIDs of the order that the invoice once was.
     */
    private long invoiceID;

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
     * ID of the customer that the invoice belongs to.
     */
    private long customerID;
    /**
     * Sub-total price of the order. No charges/discounts applied.
     */
    private double totalPrice;
    /**
     * Discount amount that was removed from the final payment price.
     */
    private double discount;
    /**
     * Service charge added to the final payment price.
     */
    private double serviceCharge;
    /**
     * Goods and Service tax amount that was added to the final payment price.
     */
    private double gST;
    /**
     * Price the customer had paid in the end.
     */
    private double finalPaymentPrice;
    /**
     * The list of MenuItems and their quantities converted into string format: "item/quantity/price|"
     */
    private String orderitems;

    /**
     * Creates the OrderInvoice object from attributes from the order that is to be converted
     * @param orderID OrderIDs of the order that the invoice once was.
     * @param staffID Table number assigned to the customer.
     * @param customerID ID of the customer that the invoice belongs to
     * @param tableNumber Table number assigned to the customer.
     * @param orderDateTime ID of the customer that the invoice belongs to.
     * @param orderitems The list of MenuItems and their quantities converted into string format: "item/quantity/price|"
     * @param totalPrice Sub-total price of the order. No charges/discounts applied.
     * @param gST Goods and Service tax amount that was added to the final payment price.
     * @param serviceCharge Service charge added to the final payment price.
     * @param discount Discount amount that was removed from the final payment price.
     * @param finalPaymentPrice Price the customer had paid in the end.
     */
    public OrderInvoice(long orderID, long staffID, long customerID, int tableNumber, long orderDateTime, 
                        String orderitems, double totalPrice, double gST, double serviceCharge, double discount, double finalPaymentPrice){
        this.invoiceID = orderID;
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
    /**
     * Gets the invoiceID/OrderID of the specific invoice
     * @return ID of the invoice
     */
    public long getInvoiceID() {
        return invoiceID;
    }
    /**
     * Gets the StaffID of the staff that took the order.
     * @return The ID of the Staff
     */
    public long getStaffID() {
        return staffID;
    }
    /**
     * Gets the date and time the order was once taken
     * @return Date and time in long format (requires Epoch time to format)
     */
    public long getOrderDateTime() {
        return orderDateTime; 
    }
    /**
     * Gets the customerID which this invoice belongs to
     * @return ID of the Customer this invoice belongs to
     */
    public long getCustomerID() {
        return customerID;
    }
    /**
     * Gets the sub-total price of the order when it was paid.
     * @return order sub-total price
     */
    public double getTotalPrice() {
        return totalPrice;
    }
    /**
     * Gets the table number assigned to the customer who has this orders.
     * @return Table number
     */
    public int getTableNumber() {
        return tableNumber;
    }
    /**
     * Gets the payment price of the order when it was paid
     * @return order payment price.
     */
    public double getFinalPaymentPrice() {
        return finalPaymentPrice;
    }
    /**
     * Gets all the OrderItem in string format that is ordered by the customer who this order belongs to
     * @return ArrayList of orderItems string
     */
    public String getOrderItems() {
        return orderitems;
    }
    /**
     * Gets the discount value that was applied to the order
     * @return Discount value in $
     */
    public double getDiscount() {
        return discount;
    }
    /**
     * Gets the service charge that was applied to the order
     * @return value of the charge in $
     */
    public double getServiceCharge() {
        return serviceCharge;
    }
    /**
     * Gets the GST amount that was applied to the order
     * @return value of the tax in $
     */
    public double getGST() {
        return gST;
    }
    /**
     * Converts the invoice object to a format that can be stored by the csv file
     * @return Converted format as a string
     */
    public String toCSVFormat() {
        return invoiceID + "," + staffID + "," + customerID + "," + tableNumber + "," + orderDateTime
                + "," + orderitems + "," + totalPrice + "," + gST + "," + serviceCharge + "," + discount + "," +
                finalPaymentPrice;
    }
}
