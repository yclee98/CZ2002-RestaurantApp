package OrdersPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import Utility.*;
/**
 * Enumeration of the period of the sale report either day or month
 */
enum Period{
    Day,
    Month
}

/**
 * This class is use to generate a sales report given an array of invoices 
 */
public class SalesReportGenerator {
    /**
     * Scanner to accept user input
     */
    private Scanner userInput = new Scanner(System.in);

    /**
     * Constructor for the SalesReportGenerator class
     */
    public SalesReportGenerator(){}

    /**
     * Perform users input to determine the period to generate the sales report 
     * @param allOrderInvoices all the invoices that will be part of the sales report
     */
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

    /**
     * Generate the sales report given the period
     * It will loop through all the invoices, if it is part of the period it will be added into the array of saleReportItems
     * It keep track of the Total price, GST, Service Charge, Discond and Final payment within the period 
     * @param period either day or month
     * @param startDate the starting period 
     * @param allOrderInvoices all the invoices that will be part of the sales report
     */
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

                dataArray[0]+=orderInvoice.getTotalPrice();
                dataArray[1]+=orderInvoice.getGST();
                dataArray[2]+=orderInvoice.getServiceCharge();
                dataArray[3]+=orderInvoice.getDiscount();
                dataArray[4]+=orderInvoice.getFinalPaymentPrice();

                orderItems = unpackOrderItemStr(orderInvoice.getOrderItems());
                
                for(String[] item: orderItems){

                    if((index=findItemIndex(saleReportItems, item[0]))!=-1){
                        saleReportItems.get(index).addQuantity(Integer.parseInt(item[1]));
                        saleReportItems.get(index).addSales(Double.parseDouble(item[2]));
                    }
                    else{
                        saleReportItems.add(new SaleReportItem(item[0], Integer.parseInt(item[1]), Double.parseDouble(item[2])));
                    }
                } 
            }
        }
        printReport(saleReportItems, dataArray);
        
    }
    
    /**
     * Print the sales report 
     * @param saleReportItems all the items sold and quantity within this period 
     * @param dataArray contain the Total price, GST, Service Charge, Discond and Final payment within the period
     */
    public void printReport(ArrayList<SaleReportItem> saleReportItems, double[] dataArray){
        if(saleReportItems.size()==0){
            System.out.println("No Sales during this period");
            return;
        }
        Collections.sort(saleReportItems);
        System.out.println("----------------------------------------------------");
        System.out.printf("%-20s%-20s%-20s\n", "Name", "Quantity Sold", "Sales");
        System.out.println("----------------------------------------------------");

        for(SaleReportItem i: saleReportItems){
            System.out.printf("%-20s%-20s%.2f\n", i.getName(), i.getQuantity(), i.getTotalSales());
        }

        System.out.println("------------------------------------------------");
        System.out.printf("%-40s$%.2f\n","Total Sales", dataArray[0]);
        System.out.printf("%-40s$%.2f\n","Total GST", dataArray[1]);
        System.out.printf("%-40s$%.2f\n","Total Service Charge", dataArray[2]);
        System.out.printf("%-40s($%.2f)\n","Total Discount", dataArray[3]);
        System.out.printf("%-40s$%.2f\n","Total Revenue", dataArray[4]);
    }

    /**
     * Unpack the string of orderItemStr into an array of string 
     * @param orderItemStr all the ordered item in string
     * @return an array of string consist of the name and the quantity for each item
     */
    public ArrayList<String[]> unpackOrderItemStr(String orderItemStr) {
        ArrayList<String[]> rebuiltOrderItem = new ArrayList<String[]>();
        String[] parts = orderItemStr.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            String[] subParts = parts[i].split("\\/");
            rebuiltOrderItem.add(subParts);
        }
        return rebuiltOrderItem;
    }

    /**
     * Find the index inside the array given the name
     * @param array to look inside 
     * @param name to find
     * @return the index of the name inside the array or -1 if not found 
     */
    public static int findItemIndex(ArrayList<SaleReportItem> array, String name){
        for(int i=0; i<array.size();i++){
            if(array.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
}
