package RestaurantProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantUI {
    private static Scanner userInput = new Scanner(System.in);
    
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
            System.out.println("1. Menu Options");
            System.out.println("2. Promotion Options");
            System.out.println("3. Category Options");
            System.out.println("4. Back");
            option = userInput.nextInt();
            userInput.nextLine(); //clear \n buffer
            System.out.println();
            
            int choice;
            
            switch(option){
            	case 1: //Menu Options
            		
            		do {
            			System.out.println("*****Menu Options*****");
                		System.out.println("1. View Menu");
                		System.out.println("2. Create Menu Item");
                        System.out.println("3. Update Menu Item");
                        System.out.println("4. Remove Menu Item");
                        System.out.println("5. Save Menu");
                        System.out.println("6. Load Menu");
                        System.out.println("7. Back");
                        choice = userInput.nextInt();
                        userInput.nextLine(); //clear \n buffer
                        System.out.println();
                        
                        switch(choice) {
                        	case 1: //View Menu
	                        	menu.viewAllMenuItems();
	                            break;
                        	case 2: //Create Menu Item
                        		createMenuItem();
                        		break;
                        	case 3: //Update Menu Item
                        		
                        		break;
                        	case 4: //Remove Menu Item
                        		removeMenuItem();
                        		break;
                        	case 5: //Save Menu
                        		menu.saveData();
                        		break;
                        	case 6: //Load Menu
                        		menu.getMenuList().clear();
                            	menu.retrieveData();
                        		break;
                        	default:
                        		break;
                        }
                        
            		}while(choice > 0 && choice < 7);
            		
            		break;
                case 2: //Promotion Options
                	
            		do {
            			System.out.println("*****Promotion Options*****");
                		System.out.println("1. View Promotions");
                		System.out.println("2. Create Promotion");
                        System.out.println("3. Update Promotion");
                        System.out.println("4. Remove Promotion");
                        System.out.println("5. Save Promotion");
                        System.out.println("6. Load Promotion");
                        System.out.println("7. Back");
                        choice = userInput.nextInt();
                        userInput.nextLine(); //clear \n buffer
                        System.out.println();
                        
                        switch(choice) {
                        	case 1: //View Promotions
	                        	menu.promo.viewAllPromo();
	                            break;
                        	case 2: //Create Promotion
                        		createPromotion();
                        		break;
                        	case 3: //Update Promotion
                        		
                        		break;
                        	case 4: //Remove Promotion
                        		removePromotion();
                        		break;
                        	case 5: //Save Promotions
                        		menu.promo.saveData();
                        		break;
                        	case 6: //Load Promotions
                        		menu.promo.getPromoList().clear();
                            	menu.promo.retrieveData();
                            	menu.populateAllPromos();
                        		break;
                        	default:
                        		break;
                        }
                        
            		}while(choice > 0 && choice < 7);
            		
            		break;

                case 3: //Category Options
                	
                	do {
            			System.out.println("*****Category Options*****");
                		System.out.println("1. View Categories");
                		System.out.println("2. Create Category");
                        System.out.println("3. Update Category");
                        System.out.println("4. Remove Category");
                        System.out.println("5. Save Categories");
                        System.out.println("6. Load Categories");
                        System.out.println("7. Back");
                        choice = userInput.nextInt();
                        userInput.nextLine(); //clear \n buffer
                        System.out.println();
                        
                        switch(choice) {
                        	case 1: //View Categories
                        		menu.cate.viewAllMenuCate();
	                            break;
                        	case 2: //Create Category
                        		createCate();
                        		break;
                        	case 3: //Update Category
                        		
                        		break;
                        	case 4: //Remove Category
                        		
                        		break;
                        	case 5: //Save Categories
                        		menu.cate.saveData();
                        		break;
                        	case 6: //Load Categories
                        		menu.cate.getCateList().clear();
                            	menu.cate.retrieveData();
                        		break;
                        	default:
                        		break;
                        }
                        
            		}while(choice > 0 && choice < 7);
            		
            		break;
                
        		default:
        			break;
            }

        }while(option>0 && option<4);
    }
    
    private static void createMenuItem() {
    	
    	System.out.println("Enter the name of the menu item");
		String name = userInput.nextLine();
		
		System.out.println("Enter the description of the menu item");
		String description = userInput.nextLine();
		
		System.out.println("Enter the price of the menu item");
		float price = userInput.nextFloat();
		userInput.nextLine(); //clear \n buffer
		
		System.out.println("Enter the itemID of the menu item");
		long itemID = userInput.nextInt();
		userInput.nextLine(); //clear \n buffer
		
		System.out.println("Enter the category of the menu item");
		String itemCate = userInput.nextLine();
		
		if(menu.cate.checkCateExists(itemCate)) {
			menu.createMenuItem(name, description, price, itemID, 
					menu.cate.returnMenuCate(itemCate));
			System.out.println(name + " created.");
		}
		else {
			System.out.println(itemCate + " does not exist.");
		}
    }
    
    private static void removeMenuItem() {
    	
    	System.out.println("*****Remove Menu Item*****");
        System.out.println("Select an option");
        System.out.println("1. Remove by Name"); 
        System.out.println("2. Remove by ID");
        System.out.println("3. Back");
        
        int option = userInput.nextInt();
        userInput.nextLine(); //clear \n buffer
        System.out.println();
        
        switch(option) {
        case 1: //By Name
        	System.out.println("Enter the name of the menu item");
			String name = userInput.nextLine();
			menu.removeMenuItem(name);
        	break;
        case 2: //By ID
        	System.out.println("Enter the ID of the menu item");
			long itemID = userInput.nextLong();
			menu.removeMenuItem(itemID);
			userInput.nextLine(); //clear \n buffer
        	break;
        default:
        	break;
        }
    }

    private static void createPromotion() {
    	System.out.println("Enter the name of the promotion set");
		String promoName = userInput.nextLine();
		System.out.println("Enter the price of the promotion set");
		float price = userInput.nextFloat();
		userInput.nextLine(); //clear \n buffer
		
		menu.promo.createPromoSetMeal(promoName, price);
		
		int option;
		
		do {
			System.out.println("*****Add Promotion Item*****");
            System.out.println("Select an option");
            System.out.println("1. Add by Name"); 
            System.out.println("2. Add by ID");
            System.out.println("3. End");
            
            option = userInput.nextInt();
            userInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(option) {
            case 1: //By Name
            	System.out.println("Enter the name of the menu item");
				String name = userInput.nextLine();
				
				if(menu.checkItemExists(name)) { //if the item exists
					menu.promo.returnPromo(promoName).addItem(menu.returnIndividualMenuItem(name));
					System.out.println(name + " added.");
				}
				else {
					System.out.println(name + " not found.");
				}
            	break;
            case 2: //By ID
            	System.out.println("Enter the ID of the menu item");
				long itemID = userInput.nextLong();
				userInput.nextLine(); //clear \n buffer
				
				if(menu.checkItemExists(itemID)) { //if the item exists
					menu.promo.returnPromo(promoName).addItem(menu.returnIndividualMenuItem(itemID));
					System.out.println("ID " + itemID + " added.");
				}
				else {
					System.out.println("ID " + itemID + " not found.");
				}
				
            	break;
            default:
            	break;
            }
		} while(option > 0 && option < 3);
    }
    
    private static void removePromotion() {
    	System.out.println("Enter the name of the promotion set");
		String name = userInput.nextLine();
    	menu.promo.removePromo(name);
    }
    
    private static void createCate() {
    	System.out.println("Enter the category name");
		String catName = userInput.nextLine();
		
		System.out.println("Enter the category ID");
		long catID = userInput.nextLong();
		userInput.nextLine(); //clear \n buffer
		
		if(menu.cate.checkCateExists(catName) || menu.cate.checkCateExists(catID)) {
			System.out.println("Error! Category Name/ID already exists.");
		}
		else {
			menu.cate.createMenuCate(catID, catName);
			System.out.println(catName + ": ID " + catID+ " created.");
		}
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
