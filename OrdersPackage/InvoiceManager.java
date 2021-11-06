package OrdersPackage;
import java.util.ArrayList;

import FlatFile.FlatFileAdapter;
import Utility.*;

/**
 * This a control class is used to interact with and manage invoice entities
 */
public class InvoiceManager extends Manager{

    /**
     * The list of all invoices in the system
     */
    protected ArrayList<OrderInvoice> invoiceList = new ArrayList<>();

    /**
     * Creates a new Invoice manager.
     * During creation, application will read all existing invoices stored inside the Orders.csv and
     * recreate the invoice objects to store within the invoiceList.
     */
    public InvoiceManager(){
        initializeInvoiceList();
    }

    /**
     * Prints the Invoice and the date in which they are created
     */
    public void printInvoiceIDAndDate(){
        long invoiceID;
        System.out.println("InvoiceID               Date");
        System.out.println("----------------------------");
        for(int i = 0; i < invoiceList.size(); i++){
            invoiceID = invoiceList.get(i).getInvoiceID();
            System.out.println(invoiceID + "          " + DateTime.epochToDate(invoiceList.get(i).getOrderDateTime(), true));
        }
        System.out.println("----------------------------");
    }

    /**
     * This function is invoked after the customer has paid for the order.
     * Converts the order object into an invoice object.
     * orderItemList and orderPromoList arrayList in Order object is converted to a string in "item/quantity/price|"
     * format for invoice storage.
     * @param orderToStore Order object to convert into invoice
     * @return Invoice object converted from orderToStore.
     */
    public OrderInvoice convertOrderToInvoice(Order orderToStore) {
        // convert all Orderitems to itemName and qty in string
        String orderItemStr = "";
        String itemNameStr;
        String itemQtyStr;
        String itemPriceStr;
        for (int i = 0; i < orderToStore.getOrderItemList().size(); i++) {
            // extract the name of the orderitem MenuItem
            itemNameStr = orderToStore.getOrderItemList().get(i).getItem().getName();
            itemQtyStr = Long.toString(orderToStore.getOrderItemList().get(i).getQuantity());
            itemPriceStr = Double.toString(orderToStore.getOrderItemList().get(i).getItem().getPrice());
            orderItemStr = orderItemStr.concat(itemNameStr + "/" + itemQtyStr + "/" + itemPriceStr + "|");
        }
        for (int i = 0; i < orderToStore.getPromoItemList().size(); i++) {
            // extract the name of the Promo meal item
            itemNameStr = orderToStore.getPromoItemList().get(i).getPromoItem().getName();
            itemQtyStr = Long.toString(orderToStore.getPromoItemList().get(i).getQuantity());
            itemPriceStr = Double.toString(orderToStore.getPromoItemList().get(i).getPromoItem().getPrice());
            orderItemStr = orderItemStr.concat(itemNameStr + "/" + itemQtyStr + "/" + itemPriceStr + "|");
        }
        return new OrderInvoice(orderToStore.getOrderID(), orderToStore.getStaffID(),
                orderToStore.getCustomer().getCustomerID(), orderToStore.getTableNumber(),
                orderToStore.getOrderDateTime(), orderItemStr, orderToStore.getTotalPrice(),
                orderToStore.getGstValue(), orderToStore.getServiceCharge(), orderToStore.getDiscountValue(),
                orderToStore.getFinalPaymentPrice());
    }

    /**
     * Searches and prints the Invoice details based on user entered invoiceID/orderID
     * @param orderID orderID/invoiceID of the invoice to print.
     */
    public void printInvoice(long orderID) {
        for (int i = 0; i < this.invoiceList.size(); i++) {
            if (this.invoiceList.get(i).getInvoiceID() == orderID) {
                System.out.println("Order Invoice: " + orderID);
                System.out.println("Customer ID: " + this.invoiceList.get(i).getCustomerID());
                System.out.println("Staff ID: " + this.invoiceList.get(i).getStaffID());
                System.out.println("Table Number: " + this.invoiceList.get(i).getTableNumber());
                //*************change to display date time in string format from long
                System.out.println("Date & Time: " + DateTime.epochToDate(this.invoiceList.get(i).getOrderDateTime(), true));
                System.out.println("==============================================");
                ArrayList<String> orderItems = unpackOrderItemStr(this.invoiceList.get(i).getOrderItems());
                for (int j = 0; j < orderItems.size(); j++) {
                    System.out.println(orderItems.get(j));
                }
                System.out.println("----------------------------------------------");
                System.out.printf("Sub Total:          $%.2f\n", this.invoiceList.get(i).getTotalPrice());
                System.out.printf("GST:               +$%.2f\n", this.invoiceList.get(i).getGST());
                System.out.printf("Service Charge:    +$%.2f\n", this.invoiceList.get(i).getServiceCharge());
                System.out.printf("Member Discount:   -$%.2f\n", this.invoiceList.get(i).getDiscount());
                System.out.printf("Total Price:        $%.2f\n", this.invoiceList.get(i).getFinalPaymentPrice());
                System.out.println("==============================================");
                return;
            }
        }
    }

    /**
     * Invoice stores orderItems as a one long string. Splitting is required to seperate the items, quantity
     * and price to be able to print properly.
     * @param orderItemStr  The concatenated string that contains orderItems in "item/quantity/price|" format
     * @return The result of the unpacking of the long orderItemStr stored in String arrayList
     */
    public ArrayList<String> unpackOrderItemStr(String orderItemStr) {
        ArrayList<String> rebuiltOrderItem = new ArrayList<String>();
        String[] parts = orderItemStr.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            String[] subParts = parts[i].split("\\/");
//            String reformedParts = String.format("%-20s%02d", subParts[0], subParts[1]);
            rebuiltOrderItem.add(String.format("%2d. %-10s %-18d \t\t $%-10.2f\n", (i+1), subParts[0], Integer.parseInt(subParts[1]),
                    Double.parseDouble(subParts[2])));
        }
        return rebuiltOrderItem;
    }

    /**
     * Converts order to invoice and saves into Orders.csv
     * @param paidOrder Order to be saved
     */
    public void saveOrder(Order paidOrder) {
        OrderInvoice newInvoice = convertOrderToInvoice(paidOrder);
        this.retrieveData();
        this.saveData();
    }

    /**
     * Retrieves all stored invoices from Orders.csv to repopulate invoiceList
     */
    private void initializeInvoiceList(){
        this.retrieveData();
    }

    /**
     * Prints all invoices inside the invoice arrayList referenced by invoiceList
     */
    public void printAllInvoices(){
        long invoiceIDs;
        System.out.println("************* Invoices **************");
        for(int i = 0; i < invoiceList.size(); i++){
            invoiceIDs = invoiceList.get(i).getInvoiceID();
            printInvoice(invoiceIDs);
        }
    }

    /**
     * Prints sales report using invoices referenced by invoiceList
     */
    public void viewSaleReport(){
        SalesReportGenerator salesReportGenerator = new SalesReportGenerator();
        salesReportGenerator.viewSaleReport(invoiceList);
    }

    public void createFlatFileAdapter(){
        super.addFlatFileAdapter(new FlatFileAdapter(){

            /**
             * get the name of the csv file to store the invoices into
             * @return The file name
             */
            @Override
            public String getFileName() {
                return "Orders.csv";
            }

            /**
             * Gets all the column names inside of the csv file
             * @return String of the column names
             */
            @Override
            public String getColumnsName() {
                return "OrderID, StaffID, CustomerID, Table Number, date, " +
                        "orderItems, totalPrice, GST, Service, Member_Discount, paymentPrice";
            }

            /**
             * Inserts an invoice into a row in the csv file
             * @param index invoice to store into the csv file based on the index in the invoiceList
             * ArrayList
             * @return the invoice in csv format if successful, null if not.
             */
            @Override
            public String insertRow(int index) {
                try{
                    return invoiceList.get(index).toCSVFormat();
                }catch (Exception e){
                    return null;
                }
            }

            /**
             * Extracts as single row from the csv file and remakes it into a invoice object
             * @param row Row string read from the csv file
             */
            @Override
            public void extractRow(String[] row) {
                invoiceList.add(new OrderInvoice(Long.parseLong(row[0]), Long.parseLong(row[1]),
                        Long.parseLong(row[2]), Integer.parseInt(row[3]), Long.parseLong(row[4]), row[5],
                        Double.parseDouble(row[6]), Double.parseDouble(row[7]), Double.parseDouble(row[8]),
                        Double.parseDouble(row[9]), Double.parseDouble(row[10])));
            }
        });
    }
}