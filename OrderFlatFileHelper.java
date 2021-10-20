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
                        "orderItems, totalPrice, paymentPrice";
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
                        Double.parseDouble(row[6]), Double.parseDouble(row[7])));
            }
        });
    }

    public void addToArray(OrderInvoice an){
        orderInvoices.add(an);
    }
    // OrderID, StaffID, CustomerID, Table Number, date,
    // orderItems, totalPrice, paymentPrice
    public static void main(String[] args){
        OrderFlatFileHelper orderHelper = new OrderFlatFileHelper();
//        orderHelper.addToArray(new OrderInvoice(123, 456, 789, 4, "20-10-2021 18:48:35",
//                "burger/2|fries/2|milkshake/2|", 12, 14));
//        orderHelper.saveData();

        orderHelper.retrieveData();
        System.out.println(orderHelper.orderInvoices.get(0).getOrderID());
        System.out.println(orderHelper.orderInvoices.get(0).getOrderitems());
    }

}
