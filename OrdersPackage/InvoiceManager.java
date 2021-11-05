package OrdersPackage;
import java.util.ArrayList;
import Utility.*;

public class InvoiceManager {
    protected OrderFlatFileHelper orderHelper = new OrderFlatFileHelper();
    protected ArrayList<OrderInvoice> invoiceList;

    public InvoiceManager(){
        initializeInvoiceList();
    }
    // DateTime.epochToDate(
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

    public void printInvoice(long orderID) {
        OrderFlatFileHelper orderHelper = new OrderFlatFileHelper();
        orderHelper.retrieveData();
        for (int i = 0; i < orderHelper.orderInvoices.size(); i++) {
            if (orderHelper.orderInvoices.get(i).getInvoiceID() == orderID) {
                System.out.println("Order Invoice: " + orderID);
                System.out.println("Customer ID: " + orderHelper.orderInvoices.get(i).getCustomerID());
                System.out.println("Staff ID: " + orderHelper.orderInvoices.get(i).getStaffID());
                System.out.println("Table Number: " + orderHelper.orderInvoices.get(i).getTableNumber());
                //*************change to display date time in string format from long
                System.out.println("Date & Time: " + DateTime.epochToDate(orderHelper.orderInvoices.get(i).getOrderDateTime(), true));
                System.out.println("==============================================");
                ArrayList<String> orderItems = unpackOrderItemStr(orderHelper.orderInvoices.get(i).getOrderItems());
                for (int j = 0; j < orderItems.size(); j++) {
                    System.out.println(orderItems.get(j));
                }
                System.out.println("----------------------------------------------");
                System.out.printf("Sub Total:          $%.2f\n", orderHelper.orderInvoices.get(i).getTotalPrice());
                System.out.printf("GST:               +$%.2f\n", orderHelper.orderInvoices.get(i).getGST());
                System.out.printf("Service Charge:    +$%.2f\n", orderHelper.orderInvoices.get(i).getServiceCharge());
                System.out.printf("Member Discount:   -$%.2f\n", orderHelper.orderInvoices.get(i).getDiscount());
                System.out.printf("Total Price:        $%.2f\n", orderHelper.orderInvoices.get(i).getFinalPaymentPrice());
                System.out.println("==============================================");
                return;
            }
        }
    }

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

    public void saveOrder(Order paidOrder) {
        OrderInvoice newInvoice = convertOrderToInvoice(paidOrder);
        orderHelper.retrieveData();
        orderHelper.addToArray(newInvoice);
        orderHelper.saveData();
    }

    private void initializeInvoiceList(){
//        OrdersPackage.OrderFlatFileHelper orderHelper = new OrdersPackage.OrderFlatFileHelper();
        orderHelper.retrieveData();
        invoiceList = orderHelper.orderInvoices;
    }

    public void printAllInvoices(){
        long invoiceIDs;
        System.out.println("************* Invoices **************");
        for(int i = 0; i < invoiceList.size(); i++){
            invoiceIDs = invoiceList.get(i).getInvoiceID();
            printInvoice(invoiceIDs);
        }
    }

    public void viewSaleReport(){
        SalesReportGenerator salesReportGenerator = new SalesReportGenerator();
        salesReportGenerator.viewSaleReport(invoiceList);
    }
}