package OrdersPackage;

import Utility.Manager;
import FlatFile.FlatFileAdapter;
import java.util.ArrayList;

/**
 *  The OrderFlatFileHelper is used to read and write the attributes of the invoice into the Orders.csv file
 */
public class OrderFlatFileHelper extends Manager {

    /**
     * contains all the invoice that is read from the Orders.csv file
     */
    protected ArrayList<OrderInvoice> orderInvoices = new ArrayList<OrderInvoice>();

    /**
     * used to format the objects to store the attributes such that it is compatible with the csv file format
     */
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
             * @param index invoice to store into the csv file based on the index in the orderInvoices
             * ArrayList
             * @return the invoice in csv format if successful, null if not.
             */
            @Override
            public String insertRow(int index) {
                try{
                    return orderInvoices.get(index).toCSVFormat();
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
                orderInvoices.add(new OrderInvoice(Long.parseLong(row[0]), Long.parseLong(row[1]),
                        Long.parseLong(row[2]), Integer.parseInt(row[3]), Long.parseLong(row[4]), row[5],
                        Double.parseDouble(row[6]), Double.parseDouble(row[7]), Double.parseDouble(row[8]),
                        Double.parseDouble(row[9]), Double.parseDouble(row[10])));
            }
        });
    }

    /**
     * Adds an invoice to the orderInvoices array to be saved.
     * @param invoice Invoice to be saved
     */
    public void addToArray(OrderInvoice invoice){
        orderInvoices.add(invoice);
    }

//    public static void main(String[] args){
//        OrdersPackage.OrderFlatFileHelper orderHelper = new OrdersPackage.OrderFlatFileHelper();
//
//        MenuCate fastfood = new MenuCate(1432, "Fastfood");
//        MenuItem burger = new MenuItem("Hamburger", "A hamburger", 5, 1200, fastfood);
//        MenuItem wrap = new MenuItem("Wrap", "A Wrap", (float)5.60, 1201, fastfood);
//        CustomerPackage.Customer bob = new CustomerPackage.Customer(123, "Bob", true);
//
//        OrdersPackage.OrderManager oManager = new OrdersPackage.OrderManager();
//        long orderID = oManager.createOrder(123, bob, 4);
//        oManager.addItemToOrder(orderID, burger, 2);
//        oManager.addItemToOrder(orderID, wrap, 2);
//
//
//        long orderID2 = oManager.createOrder(456, bob, 5);
//        oManager.addItemToOrder(orderID2, burger, 2);
//        oManager.addItemToOrder(orderID2, wrap, 2);
//
//        oManager.removeItemFromOrder(orderID2);
//
//        oManager.settlePayment(orderID);
//        OrdersPackage.InvoiceManager iManager = new OrdersPackage.InvoiceManager();
//        iManager.printInvoice(orderID);
//
//        oManager.settlePayment(orderID2);
//        iManager.printInvoice(orderID2);
//
////        System.out.println("Invoices in InvoiceList = " + iManager.invoiceList.size());
//
//        iManager.printAllInvoices();

//        System.out.println(orderHelper.orderInvoices.get(0).getOrderID());
//        System.out.println(orderHelper.orderInvoices.get(0).getStaffID());
//        System.out.println(orderHelper.orderInvoices.get(0).getCustomerID());
//        System.out.println(orderHelper.orderInvoices.get(0).getTableNumber());
//        System.out.println(orderHelper.orderInvoices.get(0).getOrderitems());
//        orderHelper.addToArray(new OrdersPackage.OrderInvoice(123, 456, 789, 4, "20-10-2021 18:48:35",
//                "burger/2|fries/2|milkshake/2|", 12, 14));
//        orderHelper.saveData();
//    }
}
