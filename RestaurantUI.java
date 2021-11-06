import CustomerPackage.CustomerManager;
import OrdersPackage.InvoiceManager;
import OrdersPackage.OrderManager;
import OrdersPackage.OrderUI;
import StaffPackage.StaffManager;
import TablePackage.TableManager;
import java.util.ArrayList;
import java.util.Scanner;
import MenuCatePackage.*;
import MenuItemPackage.*;
import PromoPackage.*;

public class RestaurantUI {
    protected static Scanner userInput = new Scanner(System.in);
    protected static OrderManager order_Mngr = new OrderManager();
    protected static InvoiceManager invoice_Mngr = new InvoiceManager();
    protected static PromoManager promo_Mngr = new PromoManager();
    protected static ItemManager item_Mngr = new ItemManager();
    protected static TableManager table_Mngr = new TableManager();
    protected static StaffManager staff_Mngr = new StaffManager();
    public static CustomerManager cust_Mngr = new CustomerManager();
	public static MenuManager menu = new MenuManager();
	public static ArrayList<MenuCate> cateList = new ArrayList<MenuCate>();

    public static void main(String[] args){
        mainMenuPage();        
    }

    private static void mainMenuPage(){
        int option;
        do{
            System.out.println("*****Main Menu*****");
            System.out.println("Select an option");
            System.out.println("1. Menu Items/Promotion");
            System.out.println("2. OrdersPackage.Order");
            System.out.println("3. Reservation");
            System.out.println("4. Check Table Availability");
            System.out.println("5. Print Sale Revenue Report");
            System.out.println("6. StaffPackage.Staff");
            System.out.println("7. CustomerPackage.Customer");
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
                    reservationPage();
                    break;
                case 4:
                    //go to check table availability
                    break;
                case 5:
                    //go to print sales revenue report
                    break;
                case 6:
                    staffPage();
                    break;
                case 7:
                    customerPage();
                    break;
                default:
            }
        }while(option>0 && option<8);
    }

    private static void menuPage(){
        int option;
        do{
            System.out.println("***** Menu Items/Promotion *****");
            System.out.println("Select an option");
            System.out.println("1. Menu Options");
            System.out.println("2. Promotion Options");
            System.out.println("3. Category Options");
            System.out.println("4. Back");
            option = userInput.nextInt();
            userInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(option){
            	case 1: //Menu Options
            		
            		menu.menuOptions();
            		
            		break;
                case 2: //Promotion Options
                	
            		menu.promotionOptions();
            		
            		break;

                case 3: //Category Options
                	
                	menu.categoryOptions();
            		
            		break;
                
        		default:
        			break;
            }

        }while(option>0 && option<4);
    }
    
    private static void orderPage(){
        int option;
        long orderID;
        MenuItem cusItem;
        PromoSetMeal cusMeal;
        do{
            System.out.println("***** OrdersPackage.Order *****");
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
                    OrderUI.CreateOrders(cust_Mngr, staff_Mngr, table_Mngr, order_Mngr);
                    break;

                case 3: //View Individual OrdersPackage.Order
                    OrderUI.viewIndividualOrders(order_Mngr);
                    break;

                case 4: // add items to order
                    // print all the customer names and the associated orderIDs
                    OrderUI.AddItemsToOrder(order_Mngr, item_Mngr, promo_Mngr);
                    break;

                case 5: // Remove items from OrdersPackage.Order
                    OrderUI.removeItemsFromOrder(order_Mngr);
                    break;

                case 6:
                    OrderUI.settlePayment(order_Mngr, invoice_Mngr, table_Mngr);
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
                    break;
                case 2:
                    break;
                default:
            }
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
}
