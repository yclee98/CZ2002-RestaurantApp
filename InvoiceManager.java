import java.util.ArrayList;

public class InvoiceManager {
    public static OrderInvoice convertOrderToInvoice(Order orderToStore) {
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
}
