package RestaurantProject;

import java.util.Scanner;

public class RestaurantUI {
    private static Scanner userInput = new Scanner(System.in);
    
	public static MenuManager menu = new MenuManager();

    public static void main(String[] args){
    	
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
            System.out.println("4. Check Table Availability");
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
            System.out.println("*****Menu Items/Promotion*****");
            System.out.println("Select an option");
            System.out.println("1. Menu Item Options");
            System.out.println("2. Promotion Set Options");
            System.out.println("3. Category Options");
            System.out.println("4. Save Menu");
            System.out.println("5. Load Menu");
            System.out.println("6. Back");
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
                case 4: //Save All
                	menu.cate.saveData(); //save categories
                	menu.saveData(); //save menu items
                	menu.promo.saveData(); //save promotions
                	
                	System.out.println("Menu Saved.");
            		System.out.println();
            		break;
                case 5:
                	
                	//load categories
                	menu.cate.getCateList().clear();
                	menu.cate.retrieveData(); 
                	
                	//load menu items
                	menu.item.getItemList().clear();
                	menu.retrieveData(); 
                	
                	//load promotions
                	menu.promo.getPromoList().clear();
                	menu.promo.retrieveData();
                	menu.populateAllPromos();
                	
                	System.out.println("Menu Loaded.");
            		System.out.println();
            		break;
        		default:
        			break;
            }

        }while(option>0 && option<6);
    }
    
    
    private static void orderPage(){
        int option;
        do{
            System.out.println("*****Order*****");
            System.out.println("Select an option");
            System.out.println("1. View All Orders");
            System.out.println("2. Create Order");
            System.out.println("3. View Individual Order");
            System.out.println("4. Add Items to Order"); 
            System.out.println("5. Remove items from Order");
            System.out.println("6. Print Order Invoice");
            System.out.println("7. Back");
            option = userInput.nextInt();
            System.out.println();

            switch(option){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
            }
        }while(option>0 && option<7);
    }

    private static void reservationPage(){
        int option;
        do{
            System.out.println("*****Reservation*****");
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
            System.out.println("*****Staff*****");
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
            System.out.println("*****Customer*****");
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