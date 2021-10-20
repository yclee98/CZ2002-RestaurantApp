import java.lang.reflect.Array;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class InvoiceManager {
    private static DecimalFormat df = new DecimalFormat("0.00");

    public OrderInvoice convertOrderToInvoice(Order orderToStore) {
        // convert all Orderitems to itemName and qty in string
        String orderItemStr = "";
        ArrayList<String> orderItemStrList = new ArrayList<String>();
        for (int i = 0; i < orderToStore.getOrderItemList().size(); i++) {
            // extract the name of the orderitem MenuItem
            String itemNameStr = orderToStore.getOrderItemList().get(i).getItem().getName();
            String itemQtyStr = Long.toString(orderToStore.getOrderItemList().get(i).getQuantity());

            orderItemStr = orderItemStr.concat(itemNameStr + "/" + itemQtyStr + "|");
        }

        return new OrderInvoice( orderToStore.getOrderID(), orderToStore.getStaffID(),
                orderToStore.getCustomer().getCustomerID(), orderToStore.getTableNumber(),
                orderToStore.getOrderDateTime(), orderItemStr, orderToStore.getTotalPrice(),
                orderToStore.getFinalPaymentPrice());
    }

    public void printInvoice(long orderID){
        OrderFlatFileHelper orderHelper = new OrderFlatFileHelper();
        orderHelper.retrieveData();
        for(int i = 0; i < orderHelper.orderInvoices.size(); i++){
            if(orderHelper.orderInvoices.get(i).getOrderID() == orderID){
                System.out.println("Order Invoice: " + orderID);
                System.out.println("Customer ID: " + orderHelper.orderInvoices.get(i).getCustomerID());
                System.out.println("Staff ID: " + orderHelper.orderInvoices.get(i).getStaffID());
                System.out.println("Table Number: " + orderHelper.orderInvoices.get(i).getTableNumber());
                System.out.println("Date & Time: " + orderHelper.orderInvoices.get(i).getOrderDateTime());
                System.out.println("============= Items ==============");
                ArrayList<String> orderItems = unpackOrderItemStr(orderHelper.orderInvoices.get(i).getOrderitems());
                for(int j = 0; j < orderItems.size(); j++){
                    System.out.println(orderItems.get(j));
                }
                System.out.println("============= Prices =============");
                System.out.printf("Total Price:        $%.2f\n", orderHelper.orderInvoices.get(i).getTotalPrice());
                System.out.printf("Payment Price:      $%.2f\n", orderHelper.orderInvoices.get(i).getFinalPaymentPrice());
                System.out.println("==================================");
            }
        }
    }

    public ArrayList<String> unpackOrderItemStr(String orderItemStr){
        ArrayList<String> rebuiltOrderItem = new ArrayList<String>();
        String[] parts = orderItemStr.split("\\|");
        for(int i = 0; i < parts.length; i++){
            String[] subParts = parts[i].split("\\/");
            rebuiltOrderItem.add(subParts[0] + "                " + subParts[1]);
        }
        return rebuiltOrderItem;
    }
}
