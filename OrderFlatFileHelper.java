import FlatFile.FlatFileAdapter;

import java.util.ArrayList;

public class OrderFlatFileHelper extends Manager{
    ArrayList<OrderInvoice> orderInvoices = new ArrayList<OrderInvoice>();
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
                        Long.parseLong(row[2]), Integer.parseInt(row[3]), row[4], row[5],
                        Double.parseDouble(row[6]), Double.parseDouble(row[7]), Double.parseDouble(row[8]),
                        Double.parseDouble(row[9]), Double.parseDouble(row[10])));
            }
        });
    }

    public void addToArray(OrderInvoice invoice){
        orderInvoices.add(invoice);
    }

    public static void main(String[] args){
        OrderFlatFileHelper orderHelper = new OrderFlatFileHelper();

        MenuCate fastfood = new MenuCate("Fastfood", 1432);
        MenuItem burger = new MenuItem("Hamburger", "A hamburger", 5, 1200, fastfood);
        MenuItem wrap = new MenuItem("Wrap", "A Wrap", (float)5.60, 1201, fastfood);
        Customer bob = new Customer(123, "Bob", true);

        OrderManager oManager = new OrderManager();
        long orderID = oManager.createOrder(123, bob, 4);
        oManager.addItemToOrder(orderID, burger, 2);
        oManager.addItemToOrder(orderID, wrap, 2);

        oManager.settlePayment(orderID);
        InvoiceManager iManager = new InvoiceManager();
        iManager.printInvoice(orderID);

//        System.out.println(orderHelper.orderInvoices.get(0).getOrderID());
//        System.out.println(orderHelper.orderInvoices.get(0).getStaffID());
//        System.out.println(orderHelper.orderInvoices.get(0).getCustomerID());
//        System.out.println(orderHelper.orderInvoices.get(0).getTableNumber());
//        System.out.println(orderHelper.orderInvoices.get(0).getOrderitems());
//        orderHelper.addToArray(new OrderInvoice(123, 456, 789, 4, "20-10-2021 18:48:35",
//                "burger/2|fries/2|milkshake/2|", 12, 14));
//        orderHelper.saveData();
    }
}
