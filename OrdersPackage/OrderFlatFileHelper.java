package OrdersPackage;

import Utility.Manager;
import FlatFile.FlatFileAdapter;
import java.util.ArrayList;

public class OrderFlatFileHelper extends Manager {

    protected ArrayList<OrderInvoice> orderInvoices = new ArrayList<OrderInvoice>();

    public void createFlatFileAdapter(){
        super.addFlatFileAdapter(new FlatFileAdapter(){
            @Override
            public String getFileName() {
                return "Orders.csv";
            }

            @Override
            public String getColumnsName() {
                return "OrderID, StaffID, CustomerID, Table Number, date, " +
                        "orderItems, totalPrice, GST, Service, Member_Discount, paymentPrice";
            }

            @Override
            public String insertRow(int index) {
                try{
                    return orderInvoices.get(index).toCSVFormat();
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            public void extractRow(String[] row) {
                orderInvoices.add(new OrderInvoice(Long.parseLong(row[0]), Long.parseLong(row[1]),
                        Long.parseLong(row[2]), Integer.parseInt(row[3]), Long.parseLong(row[4]), row[5],
                        Double.parseDouble(row[6]), Double.parseDouble(row[7]), Double.parseDouble(row[8]),
                        Double.parseDouble(row[9]), Double.parseDouble(row[10])));
            }
        });
    }

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
