import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

enum Period{
    Day,
    Month
}

public class SalesReportGenerator {
    private Scanner userInput = new Scanner(System.in);
    public SalesReportGenerator(){

    }

    public void viewSaleReport(ArrayList<OrderInvoice> allOrderInvoices){
        int day, month, year;
        System.out.println("Enter the start date (day month year)");
        day = userInput.nextInt(); 
        month = userInput.nextInt();
        year = userInput.nextInt();

        Period period;
        System.out.println("Enter the Period for the sale report");
        System.out.println("1. Day");
        System.out.println("2. Month");
        switch(userInput.nextInt()){
            case 1:
                period=Period.Day;
                break;
            default:
                period=Period.Month;
        }

        long startDate = DateTime.dateToEpoch(day, month, year);
        generateSaleReport(period, startDate, allOrderInvoices);
        
    }

    public void generateSaleReport(Period period, Long startDate, ArrayList<OrderInvoice> allOrderInvoices){
        ArrayList<SaleReportItem> saleReportItems = new ArrayList<>();
        ArrayList<String[]> orderItems;
        int index;
        double[] dataArray = new double[]{0,0,0,0,0}; //0: Total price, 1: GST, 2: Service Charge, 3:discount, 4:final payment
        Long endDate;
        Long orderDateTime;

        if(period == Period.Day){
            endDate=startDate + 86400000L; //add in 1 day
        }
        else{
            endDate=startDate + 2678400000L; //add in 31 day
        }

        System.out.println("Generating Sales Report for a period of 1 " + period.toString());
        System.out.println("From\t"+ DateTime.epochToDate(startDate, true));
        System.out.println("To\t"+ DateTime.epochToDate(endDate, true));

        for (OrderInvoice orderInvoice: allOrderInvoices){
            orderDateTime = orderInvoice.getOrderDateTime(); 

            if(orderDateTime>=startDate && orderDateTime<=endDate){ //if invoice date between startdate and enddate
                System.out.println("Adding invoice " + orderInvoice.getOrderID() + " @ " +DateTime.epochToDate(orderDateTime, true));//remove

                dataArray[0]+=orderInvoice.getTotalPrice();
                dataArray[1]+=orderInvoice.getGST();
                dataArray[2]+=orderInvoice.getServiceCharge();
                dataArray[3]+=orderInvoice.getDiscount();
                dataArray[4]+=orderInvoice.getFinalPaymentPrice();

                orderItems = unpackOrderItemStr(orderInvoice.getOrderItems());
                
                for(String[] item: orderItems){

                    if((index=findItemIndex(saleReportItems, item[0]))!=-1){
                        saleReportItems.get(index).addQuantity(Integer.parseInt(item[1]));
                    }
                    else{
                        saleReportItems.add(new SaleReportItem(item[0], Integer.parseInt(item[1])));
                    }
                } 
            }
            
        }
        printReport(saleReportItems, dataArray);
        
    }
    
    public void printReport(ArrayList<SaleReportItem> saleReportItems,double[] dataArray){
        Collections.sort(saleReportItems);
        System.out.println("------------------------------------------------");
        System.out.printf("%-20s%-20s\n", "Name", "Quantity Sold");
        System.out.println("------------------------------------------------");

        for(SaleReportItem i: saleReportItems){
            System.out.printf("%-20s%02d\n", i.getName(), i.getQuantity());
        }

        System.out.println("------------------------------------------------");
        System.out.printf("%-40s$%.2f\n","Total Sales", dataArray[0]);
        System.out.printf("%-40s$%.2f\n","Total GST", dataArray[1]);
        System.out.printf("%-40s$%.2f\n","Total Service Charge", dataArray[2]);
        System.out.printf("%-40s($%.2f)\n","Total Discount", dataArray[3]);
        double cal = dataArray[0] + dataArray[1] + dataArray[2] - dataArray[3];
        System.out.printf("%-40s$%.2f\n","Total Revenue cal", cal);
        System.out.printf("%-40s$%.2f\n","Total Revenue", dataArray[4]);
    }

    public ArrayList<String[]> unpackOrderItemStr(String orderItemStr) {
        ArrayList<String[]> rebuiltOrderItem = new ArrayList<String[]>();
        String[] parts = orderItemStr.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            String[] subParts = parts[i].split("\\/");
            rebuiltOrderItem.add(subParts);
        }
        return rebuiltOrderItem;
    }

    public static int findItemIndex(ArrayList<SaleReportItem> array, String name){
        for(int i=0; i<array.size();i++){
            if(array.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        ArrayList<OrderInvoice> oi = new ArrayList<OrderInvoice>();
        oi.add(new OrderInvoice(1, 15, 9, 2021, 1631635200000L, "burger weqwe/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(2, 18, 9, 2021, 1631894400000L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(3, 24, 9, 2021, 1632412800000L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(4, 30, 9, 2021, 1632998467787L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(5, 23 ,10, 2021, 1634996839529L, "burger weqwe/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(6, 24, 10, 2021, 1635011585756L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(7, 25, 10, 2021, 1635097985756L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(8, 26, 10, 2021, 1635184385756L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(9, 27, 10, 2021, 1635270785756L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(10, 30, 10, 2021, 1635270785756L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(11, 30, 10, 2021, 1635601682783L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(12, 13, 11, 2021, 1636811282783L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));
        oi.add(new OrderInvoice(13, 4, 12, 2021, 1638625682783L, "burger/1|thai/2|er/3|wer/4",1,2,3,4,5));


        SalesReportGenerator sg = new SalesReportGenerator();
        sg.viewSaleReport(oi);
                
    }

}
