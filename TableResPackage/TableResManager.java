package TableResPackage;

import ReservationPackage.*;
import TablePackage.*;
import Utility.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A control class that is used to interact with and mange the reservation and table manger
 */
public class TableResManager {
    /**
     * Create a scanner to take user input
     */
   public static Scanner sc = new Scanner(System.in);
   /**
    * Create an TableManager to manipulate 
    */
   public static TableManager tm = new TableManager();
   /**
    * Create an ReservationManager to manipulate 
    */
   public static ReservationManager rm = new ReservationManager();
    

    /**
     * tableRes UI Main Page
     */
    public static void tableResMainPage(){
        int option;
        do{
            System.out.println("*****Main Menu*****");
            System.out.println("Select an option"); 
            System.out.println("1. Tables Menu");
            System.out.println("2. Reservation Menu");
            System.out.println();
            option = sc.nextInt();

            switch (option) {
                case 1:
                    tableMenu();
                    break;
                case 2:
                    reservationMenu();
                    break;
                default:
                 System.out.println("choose from 1 and 2");
            }
        }while(option>0 && option <3);
    }

    
    /**
     * tableMenu UI Main Page
     */
    public static void tableMenu(){
        int option;
        do{
            System.out.println();
            System.out.println("*****Main Menu*****");
            System.out.println("Select an option");
            System.out.println("1. Add Tables");
            System.out.println("2. Remove Tables");
            System.out.println("3. Check if Table number is available");
            System.out.println("6. Set table as taken / not taken");
            System.out.println("4. Show all the currently available tables");
            System.out.println("5. Save the tables into CSV");
            System.out.println("6. Set table as taken / not taken");
            System.out.println("7. Back");
            System.out.println();
            option = sc.nextInt();

            switch(option){
                case 1:
                    System.out.println("Please key in the Table Number ");
                    int tableNum = sc.nextInt();
                    if(tm.ifExist(tableNum) == 0){
                        System.out.println("Please key in the capacity ");
                        int capacity = sc.nextInt();
                        
                        
                        if(capacity<2 || capacity>10) {
                            System.out.println("Number of seats should be between 2 to 10");
                            break;
                        }

                        else if(capacity%2 == 1 ) {
                            System.out.println("Number of seats should be even");
                            break;
                        }

                        else
                            tm.addTable(tableNum, capacity);
                            System.out.println("Table Number " + tableNum + " added");
                    }
                    else
                        System.out.println("The table is already in existance please key in a differnet table number ");
                    break;
                case 2:
                    System.out.println("Please key in the Table Number to remove ");
                    int tableNumRemove = sc.nextInt();
                    tm.removeTable(tableNumRemove);
                    break;
                case 3:
                    System.out.println("Please key in the Table Number ");
                    tm.isTableNumAvailable(sc.nextInt());
                    break;
                case 4: 
                    tm.sortByNum();
                    
                    tm.showAvailableTables();
                    break;
                case 5:
                    tm.saveData();
                    break;
                case 6:
                    System.out.println("Please key in the Table Number ");
                    int tableNum6 = sc.nextInt();
                    System.out.println("Please key in the 'true' to set as taken or 'false' as not taken ");
                    boolean tf = sc.nextBoolean();
                    tm.setAssign(tableNum6, tf);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Please choose from options 1 to 7 ");
            }
        }while(option>0 && option <7);
    }


    /**
     * reservationMenu UI Main page
     */
    public static void reservationMenu(){
        int option;
        do{
            System.out.println();
            System.out.println("*****Main Menu*****");
            System.out.println("Select an option");
            System.out.println("1. Add Reservation");
            System.out.println("2. Remove Reservation");
            System.out.println("3. Check Reservation");
            System.out.println("4. Back");
            System.out.println();
            option = sc.nextInt();

            switch(option){
                case 1:
                    System.out.println("Please key in Customer Contact Number");
                    int contact = sc.nextInt();
                    if(rm.ifexist(contact) == 0){
                        System.out.println("Please key in the date and time in the format of day, month, year, HH, MM");
                        long dtTime = Utility.DateTime.dateTimeToEpoch(sc.nextInt(), sc.nextInt(), 
                                                                       sc.nextInt(), sc.nextInt(), sc.nextInt());
                        //Reservation must be done 2 hrs in advance 
                        if(rm.isReservationInAdvance(dtTime) == false){
                            System.out.println("Reservation must be done at least 4hrs in advance");
                        }

                        else{
                            System.out.println("Please key in the Party Size");
                            int capacity = sc.nextInt();

                            //Creating an array list of all the reservations to ensure there is no clashes
                            //Assume that each customer takes the most 2 hrs from their reservation time to dine
                            //7200000 translates to 2hrs in epoch
                            ArrayList<Integer> num = new ArrayList<Integer>();
		                    for(int i=0; i<rm.getReservationList().size(); i++){
			                    if(((dtTime <= (rm.getReservationList().get(i).getEpochDateTime() + 7200000)) &&
			                        ((dtTime + 7200000) >= (rm.getReservationList().get(i).getEpochDateTime())))){
				                        num.add(rm.getReservationList().get(i).getTableNum());
			                    }
                            }
        
                            int tableNum = tm.assignTable(capacity, num);
                            //if there is a table
                            if(tableNum != -1 ){
                                //Ask the customer for his name
                                System.out.println("Please enter the customer's name");
                                String custname = sc.next();
                                //Enter into the Reservation Package
                                rm.addReservation(custname, contact, capacity, dtTime, tableNum);
                                System.out.println("Reservation under contact number " + contact + " has been confirmed ");
                            }

                            else{
                                System.out.println("There is no available tables for reservation at the specificed time");
                            }
                        }
                    }
                    else
                        System.out.println("There is already a reservation made with this number");
                    break;
                    
                case 2:
                    System.out.println("Please key in Customer Contact Number");
                    rm.removeReservation(sc.nextInt());
                    break;

                case 3:
                    System.out.println("Please key in Customer Contact Number");
                    rm.checkReservation(sc.nextInt());
                    break;

                case 4:
                    break;
                   
                default:
                    System.out.println("Please choose from options 1 to 4");
            }
        }while(option>0 && option <4);
    }
}
