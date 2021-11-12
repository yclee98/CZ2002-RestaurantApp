import CustomerPackage.CustomerManager;
import OrdersPackage.InvoiceManager;
import OrdersPackage.OrderManager;
import OrdersPackage.OrderTaker;
import TableResPackage.TableResManager;

import java.util.Scanner;

import StaffPackage.*;

/**
 * This class is implement the User Interface for the Restaurant 
 */
public class RestaurantUI {
    /**
     * Scanner to read user input
     */
    protected static Scanner userInput = new Scanner(System.in);
    /**
     * OrderManager responsible for managing orders part of the program
     */
    protected static OrderManager order_Mngr = new OrderManager();
    /**
     * InvoiceManager responsible for managing invoice part of the program
     */
    protected static InvoiceManager invoice_Mngr = new InvoiceManager();
    /**
     * StaffManager responsible for managing staff part of the program
     */
    protected static StaffManager staff_Mngr = new StaffManager();
    /**
     * CustomerManager responsible for managing Customer part of the program
     */
    protected static CustomerManager cust_Mngr = new CustomerManager();
    /**
     * MenuManager responsible for managing Menu part of the program
     */
    protected static MenuManager menu_Mngr = new MenuManager();
    /**
     * TableResManager responsible for managing Table and Reservation part of the program
     */
    protected static TableResManager tableRes_Mngr = new TableResManager();


    /**
     * The main entry point to start the application
     * It deos loading of the categories, menu and promotion items 
     * and then run the main menu page at start
     * @param args no argument needed 
     */
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

    /**
     * Display the main menu UI page 
     */
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
                    TableResManager.rm.endThread();
                    TableResManager.rm.saveData();
            }
            System.out.println();
        }while(option>0 && option<8);
    }

    /**
     * Display the menu items/promotion UI page 
     */
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
    
    /**
     * Display the order UI page
     */
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
                    OrderTaker.viewAllOrders(order_Mngr);
                    break;

                case 2: // Create orders
                    OrderTaker.CreateOrders(cust_Mngr, staff_Mngr, tableRes_Mngr.tm, order_Mngr, tableRes_Mngr.rm);
                    break;

                case 3: //View Individual OrdersPackage.Order
                    OrderTaker.viewIndividualOrders(order_Mngr);
                    break;

                case 4: // add items to order
                    // print all the customer names and the associated orderIDs
                    OrderTaker.AddItemsToOrder(order_Mngr, menu_Mngr.item_Mngr, menu_Mngr.promo_Mngr);
                    break;

                case 5: // Remove items from OrdersPackage.Order
                    OrderTaker.removeItemsFromOrder(order_Mngr);
                    break;

                case 6:
                    OrderTaker.settlePayment(order_Mngr, invoice_Mngr, tableRes_Mngr.tm);
                    break;

                case 7: // print order invoice
                    OrderTaker.printOrderInvoice(invoice_Mngr);
                    break;

                case 8: // print all invoice
                    OrderTaker.printAllInvoices(invoice_Mngr);
                    break;
                default:
            }
        }while(option>0 && option<9);
    }

    /**
     * Display the staff UI page 
     */
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

    /**
     * Display the customer UI page
     */
    private static void customerPage(){
        int option;
        do{
            System.out.println("***** Customer *****");
            System.out.println("Select an option.");
            System.out.println("1. View All Customer");
            System.out.println("2. Create Customer");
            System.out.println("3. View Customer");
            System.out.println("4. Remove Customer");
            System.out.println("5. Register Customer membership");
            System.out.println("6. De-register Customer membership");
            System.out.println("7. Back");
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
                    cust_Mngr.registerMembership(true);
                case 6:
                    cust_Mngr.registerMembership(false);
                default:
                    cust_Mngr.saveData();
            }
        }while(option>0 && option<7);
    }
}
