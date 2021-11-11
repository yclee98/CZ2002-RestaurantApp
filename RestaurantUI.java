import CustomerPackage.CustomerManager;
import OrdersPackage.InvoiceManager;
import OrdersPackage.OrderManager;
import OrdersPackage.OrderUI;
import TablePackage.TableManager;
import TableResPackage.TableResManager;

import java.util.ArrayList;
import java.util.Scanner;
import MenuCatePackage.*;
import MenuItemPackage.*;
import PromoPackage.*;
import StaffPackage.*;
import ReservationPackage.*;
import TableResPackage.*;

public class RestaurantUI {
    protected static Scanner userInput = new Scanner(System.in);
    protected static OrderManager order_Mngr = new OrderManager();
    protected static InvoiceManager invoice_Mngr = new InvoiceManager();
    protected static StaffManager staff_Mngr = new StaffManager();
    protected static CustomerManager cust_Mngr = new CustomerManager();
    protected static MenuManager menu_Mngr = new MenuManager();
    protected static TableResManager tableRes_Mngr = new TableResManager();


    public static void main(String[] args){
	//Autoload Menu
    	//load categories
    	menu_Mngr.cate_Mngr.getCateList().clear();
    	menu_Mngr.cate_Mngr.retrieveData(); 
    	
    	//load menu items
    	menu_Mngr.item_Mngr.getItemList().clear();
    	menu_Mngr.retrieveData(); 
    	
    	//load promotions
    	menu_Mngr.promo_Mngr.getPromoList().clear();
    	menu_Mngr.promo_Mngr.retrieveData();
    	menu_Mngr.populateAllPromos();
        mainMenuPage();        
    }

    private static void mainMenuPage(){
        int option;
        do{
            System.out.println("*****Main Menu*****");
            System.out.println("Select an option");
            System.out.println("1. Menu Items/Promotion");
            System.out.println("2. Order");
            System.out.println("3. Reservation");
            System.out.println("4. Table");
            System.out.println("5. Print Sale Revenue Report");
            System.out.println("6. Staff");
            System.out.println("7. Customer");
            System.out.println("8. Exit");
            option = userInput.nextInt();
            System.out.println();

            switch(option){
                case 1: 
                    menuPage();
                    break;
                case 2:
                    orderPage();
                    break;
                case 3:
                    tableRes_Mngr.reservationMenu();                  
                    break;
                case 4:
                    tableRes_Mngr.tableMenu();  
                    break;
                case 5:
                    invoice_Mngr.viewSaleReport();
                    break;
                case 6:
                    staffPage();
                    break;
                case 7:
                    customerPage();
                    break;
                default:
            }
            System.out.println();
        }while(option>0 && option<8);
    }

    private static void menuPage(){
        int option;
        do{
            System.out.println("*****Menu Items/Promotion*****");
            System.out.println("Select an option");
            System.out.println("1. Menu Item Options");
            System.out.println("2. Promotion Set Options");
            System.out.println("3. Category Options");
            System.out.println("4. Save Menu");
            System.out.println("5. Back");
            option = userInput.nextInt();
            userInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(option){
            	case 1: //Menu Options
            		
            		menu_Mngr.menuOptions();
            		
            		break;
                case 2: //Promotion Options
                	
            		menu_Mngr.promotionOptions();
            		
            		break;

                case 3: //Category Options
                	
                	menu_Mngr.categoryOptions();
            		
            		break;
                case 4: //Save All
                	menu_Mngr.cate_Mngr.saveData(); //save categories
                	menu_Mngr.saveData(); //save menu items
                	menu_Mngr.promo_Mngr.saveData(); //save promotions
                	
                	System.out.println("Menu Saved.");
            		System.out.println();
            		break;
        		default:
        			break;
            }

        }while(option>0 && option<5);
    }
    
    private static void orderPage(){
        int option;
        do{
            System.out.println("***** Order *****");
            System.out.println("Select an option");
            System.out.println("1. View All Orders");
            System.out.println("2. Create Order");
            System.out.println("3. View Individual Order");
            System.out.println("4. Add Items to Order");
            System.out.println("5. Remove items from Order");
            System.out.println("6. Pay for items from Order");
            System.out.println("7. Print Order Invoice");
            System.out.println("8. Print All Invoice");
            System.out.println("9. Back");
            option = userInput.nextInt();
            System.out.println();

            switch(option){

                case 1: // view all orders
                    OrderUI.viewAllOrders(order_Mngr);
                    break;

                case 2: // Create orders
                    OrderUI.CreateOrders(cust_Mngr, staff_Mngr, tableRes_Mngr.tm, order_Mngr, tableRes_Mngr.rm);
                    break;

                case 3: //View Individual OrdersPackage.Order
                    OrderUI.viewIndividualOrders(order_Mngr);
                    break;

                case 4: // add items to order
                    // print all the customer names and the associated orderIDs
                    OrderUI.AddItemsToOrder(order_Mngr, menu_Mngr.item_Mngr, menu_Mngr.promo_Mngr);
                    break;

                case 5: // Remove items from OrdersPackage.Order
                    OrderUI.removeItemsFromOrder(order_Mngr);
                    break;

                case 6:
                    OrderUI.settlePayment(order_Mngr, invoice_Mngr, tableRes_Mngr.tm);
                    break;

                case 7: // print order invoice
                    OrderUI.printOrderInvoice(invoice_Mngr);
                    break;

                case 8: // print all invoice
                    OrderUI.printAllInvoices(invoice_Mngr);
                    break;
                default:
            }
        }while(option>0 && option<9);
    }

    private static void reservationPage(){
        int option;
        do{
            System.out.println("***** Reservation *****");
            System.out.println("Select an option");
            System.out.println("1. View All Reservations");
            System.out.println("2. Create Reservation Booking");
            System.out.println("3. View Individual Reservation Booking");
            System.out.println("4. Remove Reservation Booking");
            System.out.println("5. Back");
            option = userInput.nextInt();
            System.out.println();

            switch(option){
                case 1:
                    break;
                case 2:
                    break;
                default:
            }
        }while(option>0 && option<5);
    }

    private static void staffPage(){
        int option;
        do{
            System.out.println("***** Staff *****");
            System.out.println("Select an option");
            System.out.println("1. View All Staff");
            System.out.println("2. Create Staff");
            System.out.println("3. View Staff");
            System.out.println("4. Remove Staff");
            System.out.println("5. Back");
            option = userInput.nextInt();
            System.out.println();

            switch(option){
                case 1:
                    staff_Mngr.printAllStaff();
                    break;
                case 2:
                    staff_Mngr.createStaff();
                    break;
                case 3:
                    staff_Mngr.viewStaffByID();
                    break;
                case 4:
                    staff_Mngr.removeStaffById();
                    break;
                default:
                    staff_Mngr.saveData();
            }
            System.out.println();
        }while(option>0 && option<5);
    }

    private static void customerPage(){
        int option;
        do{
            System.out.println("***** Customer *****");
            System.out.println("Select an option");
            System.out.println("1. View All Customer");
            System.out.println("2. Create Customer");
            System.out.println("3. View Customer");
            System.out.println("4. Remove Customer");
            System.out.println("5. Register Customer membership");
            System.out.println("6. Back");
            option = userInput.nextInt();
            System.out.println();

            switch(option){
                case 1:
                    cust_Mngr.printCustomers();
                    break;
                case 2:
                    cust_Mngr.createCustomer();
                    break;
                case 3:
                    cust_Mngr.displayCustomerInfo();
                    break;
                case 4:
                    cust_Mngr.removeCustomer();
                    break;
                case 5:
                    cust_Mngr.registerMembership();
                default:
                    cust_Mngr.saveData();
            }
        }while(option>0 && option<6);
    }
}
