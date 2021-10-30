package RestaurantProject;

import java.util.ArrayList;
import java.util.Scanner;

import RestaurantProject.FlatFile.FlatFileAdapter;

public class MenuManager extends Manager {
	
	private static Scanner menuInput = new Scanner(System.in);
	
	private ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
	
	private PromoManager promo = new PromoManager();
	
	private CateManager cate = new CateManager();
	
	public MenuManager() {
	}
	
	public void createMenuItem(String name, String description, 
			float price, long itemID, MenuCate itemCate) {
		MenuItem temp = new MenuItem(name, description, price, itemID, itemCate);
		
		menuList.add(temp);
        
    }
	
	public ArrayList<MenuItem> getMenuList(){
		return menuList;
	}

	//Viewing Items from List
	
    public void viewAllMenuItems() {
    	//Printing names based on creation time
    	for(int i=0; i < menuList.size(); i++) {
    		System.out.println("--------------------");
			System.out.printf((i+1)+ ". " + menuList.get(i).getName() + " | $%.2f\n", menuList.get(i).getPrice());
			System.out.printf("ID: %.0f\n", menuList.get(i).getItemID());
			System.out.println(menuList.get(i).getItemCate().getCatName());
			System.out.println(menuList.get(i).getDescription());
			System.out.println("--------------------");
		}
    	
    	System.out.println();
    }

    public void viewIndividualMenuItem(long itemId){
    	
    	if(!checkItemExists(itemId)) {
    		System.out.println("ID " + itemId + " not found.");
    		System.out.println();
    		return;
    	}
    	
    	MenuItem foundItem = returnIndividualMenuItem(itemId);
    	
    	System.out.println("--------------------");
		System.out.printf(foundItem.getName() + " | $%.2f\n", foundItem.getPrice());
		System.out.printf("ID: %.0f\n", foundItem.getItemID());
		System.out.println(foundItem.getItemCate().getCatName());
		System.out.println(foundItem.getDescription());
		System.out.println("--------------------");
		
		System.out.println();
    }
    
    public void viewIndividualMenuItem(String itemName){
    	
    	if(!checkItemExists(itemName)) {
    		System.out.println(itemName + " not found.");
    		System.out.println();
    		return;
    	}
    	
    	MenuItem foundItem = returnIndividualMenuItem(itemName);
    	
    	System.out.println("--------------------");
		System.out.printf(foundItem.getName() + " | $%.2f\n", foundItem.getPrice());
		System.out.printf("ID: %.0f\n", foundItem.getItemID());
		System.out.println(foundItem.getItemCate().getCatName());
		System.out.println(foundItem.getDescription());
		System.out.println("--------------------");
		
		System.out.println();
    	
    }

    public MenuItem returnIndividualMenuItem(long itemId){
    	
    	for(int i=0; i < menuList.size(); i++) {
			if(menuList.get(i).getItemID() == itemId) {
				return menuList.get(i); //found
			}
		}
    	
    	return null; //returns null if not found
    }
    
    public MenuItem returnIndividualMenuItem(String itemName){
    	
    	for(int i=0; i < menuList.size(); i++) {
			if(menuList.get(i).getName().equals(itemName)) {
				return menuList.get(i); //found
			}
		}
    	
    	return null; //returns null if not found
    }
    
    public boolean checkItemExists(String itemName) {
    	
    	for(int i=0; i < menuList.size(); i++) {
    		if(menuList.get(i).getName().equals(itemName)) {
    			return true; //exists
    		}
    	}

    	return false; //does not exist
    	
    }
    
    public boolean checkItemExists(long itemID) {
    	
    	for(int i=0; i < menuList.size(); i++) {
    		if(menuList.get(i).getItemID() == itemID) {
    			return true; //exists
    		}
    	}

    	return false; //does not exist
    	
    }
   
    //Editing Items in List
    
    public void updateMenuItem(MenuItem foundItem){
    	
    	int option = 0;
    	
    	do {
    		System.out.println("*****Update Menu Item*****");
        	viewIndividualMenuItem(foundItem.getName());
            System.out.println("Select an option to edit");
            System.out.println("1. Name"); 
            System.out.println("2. Price");
            System.out.println("3. Item ID");
            System.out.println("4. Category");
            System.out.println("5. Description");
            System.out.println("6. Back");
            
            option = menuInput.nextInt();
            menuInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(option) {
            case 1: //Name
            	System.out.println("Enter new Name:");
            	String name = menuInput.nextLine();
            	
            	if(checkItemExists(name)) { //already has an item named this
            		System.out.println("Menu Item called " + name + " already exists!");
            		System.out.println("Name not updated.");
                	System.out.println();
            	}
            	else {
            		foundItem.setName(name);
                	
                	System.out.println("Name updated to " + name + ".");
                	System.out.println();
            	}
            	break;
            case 2: //Price
            	System.out.println("Enter new Price:");
            	float price = menuInput.nextFloat();
            	menuInput.nextLine(); //clear \n buffer
            	
            	foundItem.setPrice(price);
            	
            	System.out.printf("Price updated to %.2f\n", price);
            	System.out.println();
            	break;
            case 3: //ID
            	System.out.println("Enter new Item ID:");
            	long itemId = menuInput.nextLong();
            	menuInput.nextLine(); //clear \n buffer
            	
            	if(checkItemExists(itemId)) { //already has an item with this ID
            		System.out.println("Menu Item with ID " + itemId + " already exists!");
            		System.out.println("Item ID not updated.");
                	System.out.println();
            	}
            	else {
            		foundItem.setItemID(itemId);
                	
                	System.out.println("Item ID updated to " + itemId + ".");
                	System.out.println();
            	}
            	break;
            case 4: //Category
                System.out.println("Select an option");
                System.out.println("1. Update Category by Name"); 
                System.out.println("2. Update Category by ID");
                System.out.println("3. Back");
                
                int choice = menuInput.nextInt();
                menuInput.nextLine(); //clear \n buffer
                System.out.println();
                
                switch(choice) {
                case 1: //By Name
                	System.out.println("Enter the name of the category");
        			String cateName = menuInput.nextLine();
        			System.out.println();
        			
        			if(!cate.checkCateExists(cateName)) { //category does not exist
        				System.out.println(cateName + " does not exist.");
        				System.out.println("Item Category not updated.");
        			}
        			else {
        				foundItem.setItemCate(cate.returnMenuCate(cateName));
        				System.out.println("Item Category updated to " + cateName + ".");
        			}
        			System.out.println();
                	break;
                case 2: //By ID
                	System.out.println("Enter the ID of the category");
        			long cateID = menuInput.nextLong();
        			menuInput.nextLine(); //clear \n buffer
        			System.out.println();
        			
        			if(!cate.checkCateExists(cateID)) { //category does not exist
        				System.out.println("ID " + cateID + " does not exist.");
        				System.out.println("Item Category not updated.");
        			}
        			else {
        				foundItem.setItemCate(cate.returnMenuCate(cateID));
        				System.out.println("Item Category updated to ID " + cateID + ".");
        			}
        			System.out.println();
                	break;
                default:
                	break;
                }
            	break;
            case 5: //Description
            	System.out.println("Enter new Description:");
            	String description = menuInput.nextLine();
            	
            	foundItem.setDescription(description);
            	
            	System.out.println("Description updated to " + description + ".");
            	System.out.println();
            	break;
            default:
            	break;
            }
    	} while(option > 0 && option < 6);
        
    }
 
    public void removeMenuItem(long itemId){
    	
    	if(!checkItemExists(itemId)) {
    		System.out.println("ID " + itemId + " not found.");
    		System.out.println();
    		return;
    	}
    	
    	removeMenuItemfromPromo(itemId, true);
    		
    }

    
    public void removeMenuItem(String itemName){
    	
    	if(!checkItemExists(itemName)) {
    		System.out.println(itemName + " not found.");
    		System.out.println();
    		return;
    	}
    	
    	removeMenuItemfromPromo(itemName, true);
    	
    }
    
    public void removeMenuItemfromPromo(String itemName, boolean allowCancel) {
    	
    	ArrayList<String> foundPromos = new ArrayList<String>(returnPromoWithMenuItem(itemName));
    	
    	boolean remove = true;
    	
    	if(foundPromos.size() == 0) { //menu item is not in a promo set at all
    		remove = true;
    	}
    	else { //menu item is in at least 1 promo set
    		
    		System.out.println(itemName + " was found in these Promotion Sets:");
    		
    		for(int i=0; i < foundPromos.size(); i++) {
    			System.out.println((i+1) + ". " + foundPromos.get(i));
    		}
    		
    		System.out.println();
    		
    		if(allowCancel) {
    			
    			System.out.println("*****Decide what to do with the Promotion Sets*****");
                System.out.println("Select an option");
                System.out.println("1. Remove the Promotion Sets"); 
                System.out.println("2. Remove the Menu Item from the Promotion Sets");
                System.out.println("3. Cancel Removal of Menu Item");
                
                int option = menuInput.nextInt();
                menuInput.nextLine(); //clear \n buffer
                System.out.println();
                
                switch(option) {
                case 1: //Remove Promo
                	
                	for(int i=0; i < foundPromos.size(); i++) {
                		promo.removePromo(foundPromos.get(i));
                	}
                	remove = true;
                	break;
                case 2: //Remove Menu Item
                	
                	for(int i=0; i < foundPromos.size(); i++) {
                		promo.returnPromo(foundPromos.get(i)).removeItem(itemName);
                	}
                	remove = true;
                	break;
                default:
                	remove = false;
                	break;
                }
    		}
    		else {
    			System.out.println("*****Decide what to do with the Promotion Sets*****");
                System.out.println("Select an option");
                System.out.println("1. Remove the Promotion Sets"); 
                System.out.println("2. Remove the Menu Item from the Promotion Sets");
                
                int option = menuInput.nextInt();
                menuInput.nextLine(); //clear \n buffer
                System.out.println();
                
                switch(option) {
                case 1: //Remove Promo
                	
                	for(int i=0; i < foundPromos.size(); i++) {
                		promo.removePromo(foundPromos.get(i));
                	}
                	remove = true;
                	break;
                case 2: //Remove Menu Item
                	
                	for(int i=0; i < foundPromos.size(); i++) {
                		promo.returnPromo(foundPromos.get(i)).removeItem(itemName);
                	}
                	remove = true;
                	break;
                default:
                	remove = true;
                	break;
                }
    		}
    		
    	}
    	
    	if(remove) { //if chose to remove menu item
    		
    		int i;
        	MenuItem foundItem = null;
        	
        	for(i=0; i < menuList.size(); i++) {
    			if(menuList.get(i).getName().equals(itemName)){
    				foundItem = menuList.get(i);
    				break; //exit for loop
    			}
    		}
        	
    		menuList.remove(i); //remove from menuList
    		System.out.println(itemName + " removed.");
    		System.out.println();
        	
        	foundItem = null; //removing the pointer, the item will be deleted by garbage collector
    	}
    }

    public void removeMenuItemfromPromo(long itemId, boolean allowCancel) {
    	
    	ArrayList<String> foundPromos = new ArrayList<String>(returnPromoWithMenuItem(itemId));
    	
    	boolean remove = true;
    	
    	if(foundPromos.size() == 0) { //menu item is not in a promo set at all
    		remove = true;
    	}
    	else { //menu item is in at least 1 promo set
    		
    		System.out.println("ID " + itemId + " was found in these Promotion Sets:");
    		
    		for(int i=0; i < foundPromos.size(); i++) {
    			System.out.println((i+1) + ". " + foundPromos.get(i));
    		}
    		
    		System.out.println();
    		
    		if(allowCancel) {
    			
    			System.out.println("*****Decide what to do with the Promotion Sets*****");
                System.out.println("Select an option");
                System.out.println("1. Remove the Promotion Sets"); 
                System.out.println("2. Remove the Menu Item from the Promotion Sets");
                System.out.println("3. Cancel Removal of Menu Item");
                
                int option = menuInput.nextInt();
                menuInput.nextLine(); //clear \n buffer
                System.out.println();
                
                switch(option) {
                case 1: //Remove Promo
                	
                	for(int i=0; i < foundPromos.size(); i++) {
                		promo.removePromo(foundPromos.get(i));
                	}
                	remove = true;
                	break;
                case 2: //Remove Menu Item
                	
                	for(int i=0; i < foundPromos.size(); i++) {
                		promo.returnPromo(foundPromos.get(i)).removeItem(returnIndividualMenuItem(itemId).getName());
                	}
                	remove = true;
                	break;
                default:
                	remove = false;
                	break;
                }
    		}
    		else {
    			System.out.println("*****Decide what to do with the Promotion Sets*****");
                System.out.println("Select an option");
                System.out.println("1. Remove the Promotion Sets"); 
                System.out.println("2. Remove the Menu Item from the Promotion Sets");
                
                int option = menuInput.nextInt();
                menuInput.nextLine(); //clear \n buffer
                System.out.println();
                
                switch(option) {
                case 1: //Remove Promo
                	
                	for(int i=0; i < foundPromos.size(); i++) {
                		promo.removePromo(foundPromos.get(i));
                	}
                	remove = true;
                	break;
                case 2: //Remove Menu Item
                	
                	for(int i=0; i < foundPromos.size(); i++) {
                		promo.returnPromo(foundPromos.get(i)).removeItem(returnIndividualMenuItem(itemId).getName());
                	}
                	remove = true;
                	break;
                default:
                	remove = true;
                	break;
                }
    		}
    		
    	}
    	
    	if(remove) { //if chose to remove menu item
    		
    		int i;
        	MenuItem foundItem = null;
        	
        	for(i=0; i < menuList.size(); i++) {
    			if(menuList.get(i).getItemID() == itemId){
    				foundItem = menuList.get(i);
    				break; //exit for loop
    			}
    		}
        	
    		menuList.remove(i); //remove from menuList
    		System.out.println("ID " + itemId + " removed.");
    		System.out.println();
        	
        	foundItem = null; //removing the pointer, the item will be deleted by garbage collector
    	}
    }
    //Promotion Functions
    
    public void populatePromoList(PromoSetMeal promoItem) {
    	
    	for(int i=0; i < promoItem.getItemNameList().size(); i++) {
    		if(checkItemExists(promoItem.getItemNameList().get(i))) { //MenuItem name exists in the menu
    			promoItem.addItem(returnIndividualMenuItem(promoItem.getItemNameList().get(i)));
    			System.out.println(promoItem.getItemNameList().get(i) + " added.");
    		}
    		else {
    			System.out.println(promoItem.getItemNameList().get(i) + " not found.");
    		}
    		
    	}
    	
    }
    
    public void populateAllPromos() {

    	for(int i=0; i < promo.getPromoList().size(); i++) {
    		
    		for(int j=0; j < promo.getPromoList().get(i).getItemNameList().size(); j++) {
    		
    			promo.getPromoList().get(i).addItem(
    					returnIndividualMenuItem(promo.getPromoList().get(i).getItemNameList().get(j)));
    		}
    		
    	}
    }

    public ArrayList<String> returnPromoWithMenuItem(String itemName){
    	
    	ArrayList<String> foundPromos = new ArrayList<String>();
    	
    	for(int i=0; i < promo.getPromoList().size(); i++) {
    		
    		if(promo.getPromoList().get(i).doesIncludeItem(itemName)) { //look for this menu item in the promo
    			foundPromos.add(promo.getPromoList().get(i).getName()); //add this promo to the list
    		}
    	}
    	
    	return foundPromos;
    }
    
    public ArrayList<String> returnPromoWithMenuItem(long itemId){
    	
    	ArrayList<String> foundPromos = new ArrayList<String>();
    	
    	for(int i=0; i < promo.getPromoList().size(); i++) {
    		
    		if(promo.getPromoList().get(i).doesIncludeItem(itemId)) { //look for this menu item in the promo
    			foundPromos.add(promo.getPromoList().get(i).getName()); //add this promo to the list
    		}
    	}
    	
    	return foundPromos;
    }
    
    //UI Functions
    //Menu Items
    
    public void menuOptions() {
    	
    	int choice;
    	
    	do {
			System.out.println("*****Menu Options*****");
    		System.out.println("1. View Menu");
    		System.out.println("2. Create Menu Item");
            System.out.println("3. Update Menu Item");
            System.out.println("4. Remove Menu Item");
            System.out.println("5. Save Menu");
            System.out.println("6. Load Menu");
            System.out.println("7. Back");
            choice = menuInput.nextInt();
            menuInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(choice) {
            	case 1: //View Menu
                	viewAllMenuItems();
                    break;
            	case 2: //Create Menu Item
            		menuOptionsCreate();
            		break;
            	case 3: //Update Menu Item
            		menuOptionsUpdate();
            		break;
            	case 4: //Remove Menu Item
            		menuOptionsDelete();
            		break;
            	case 5: //Save Menu
            		saveData();
            		System.out.println("Menu Saved.");
            		System.out.println();
            		break;
            	case 6: //Load Menu
            		getMenuList().clear();
                	retrieveData();
                	System.out.println("Menu Loaded.");
                	System.out.println();
            		break;
            	default:
            		break;
            }
            
		}while(choice > 0 && choice < 7);
    }
    
    public void menuOptionsCreate() {
    	
    	System.out.println("Enter the name of the menu item");
		String name = menuInput.nextLine();
		
		System.out.println("Enter the description of the menu item");
		String description = menuInput.nextLine();
		
		System.out.println("Enter the price of the menu item");
		float price = menuInput.nextFloat();
		menuInput.nextLine(); //clear \n buffer
		
		System.out.println("Enter the itemID of the menu item");
		long itemID = menuInput.nextInt();
		menuInput.nextLine(); //clear \n buffer
		
		System.out.println("Enter the category of the menu item");
		String itemCate = menuInput.nextLine();
		
		if(cate.checkCateExists(itemCate)) {
			createMenuItem(name, description, price, itemID, 
					cate.returnMenuCate(itemCate));
			System.out.println(name + " created.");
		}
		else {
			System.out.println(itemCate + " does not exist.");
		}
		
		System.out.println();
    }
    
    public void menuOptionsUpdate() {
    	
    	System.out.println("*****Update Menu Item*****");
        System.out.println("Select an option");
        System.out.println("1. Update by Name"); 
        System.out.println("2. Update by ID");
        System.out.println("3. Back");
        
        int option = menuInput.nextInt();
        menuInput.nextLine(); //clear \n buffer
        System.out.println();
        
        MenuItem foundItem;
        
        switch(option) {
        case 1: //By Name
        	System.out.println("Enter the name of the menu item");
			String name = menuInput.nextLine();
			System.out.println();
			
			if(!checkItemExists(name)) {
	    		System.out.println(name + " not found.");
	    		System.out.println();
	    		break;
	    	}
	    	
	    	foundItem = returnIndividualMenuItem(name);
			
			updateMenuItem(foundItem);
			System.out.println();
        	break;
        case 2: //By ID
        	System.out.println("Enter the ID of the menu item");
			long itemID = menuInput.nextLong();
			menuInput.nextLine(); //clear \n buffer
			System.out.println();
			
			if(!checkItemExists(itemID)) {
	    		System.out.println("ID " + itemID + " not found.");
	    		System.out.println();
	    		break;
	    	}
	    	
	    	foundItem = returnIndividualMenuItem(itemID);
			
			updateMenuItem(foundItem);
			System.out.println();
        	break;
        default:
        	break;
        }
    }
    
    public void menuOptionsDelete() {
    	
    	System.out.println("*****Remove Menu Item*****");
        System.out.println("Select an option");
        System.out.println("1. Remove by Name"); 
        System.out.println("2. Remove by ID");
        System.out.println("3. Back");
        
        int option = menuInput.nextInt();
        menuInput.nextLine(); //clear \n buffer
        System.out.println();
        
        switch(option) {
        case 1: //By Name
        	System.out.println("Enter the name of the menu item");
			String name = menuInput.nextLine();
			
			removeMenuItem(name);
			System.out.println();
        	break;
        case 2: //By ID
        	System.out.println("Enter the ID of the menu item");
			long itemID = menuInput.nextLong();
			menuInput.nextLine(); //clear \n buffer
			
			removeMenuItem(itemID);
			System.out.println();
        	break;
        default:
        	break;
        }
    }
    
    //Promotions
    
    public void promotionOptions() {
    	
    	int choice;
    	
    	do {
			System.out.println("*****Promotion Set Options*****");
    		System.out.println("1. View Promotion Sets");
    		System.out.println("2. Create Promotion Set");
            System.out.println("3. Update Promotion Set");
            System.out.println("4. Remove Promotion Set");
            System.out.println("5. Save Promotion Sets");
            System.out.println("6. Load Promotion Sets");
            System.out.println("7. Back");
            choice = menuInput.nextInt();
            menuInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(choice) {
            	case 1: //View Promotions
                	promo.viewAllPromo();
                    break;
            	case 2: //Create Promotion
            		promotionOptionsCreate();
            		break;
            	case 3: //Update Promotion
            		promotionOptionsUpdate();
            		break;
            	case 4: //Remove Promotion
            		promotionOptionsDelete();
            		break;
            	case 5: //Save Promotions
            		promo.saveData();
            		System.out.println("Promotion Sets Saved.");
            		System.out.println();
            		break;
            	case 6: //Load Promotions
            		promo.getPromoList().clear();
                	promo.retrieveData();
                	populateAllPromos();
                	System.out.println("Promotion Sets Loaded.");
                	System.out.println();
            		break;
            	default:
            		break;
            }
            
		}while(choice > 0 && choice < 7);
    }

    public void promotionOptionsCreate() {
    	
    	System.out.println("Enter the name of the promotion set");
		String promoName = menuInput.nextLine();
		System.out.println("Enter the price of the promotion set");
		float price = menuInput.nextFloat();
		menuInput.nextLine(); //clear \n buffer
		
		promo.createPromoSetMeal(promoName, price);
		
		addPromotionItem(promoName);
    }
    
    public void addPromotionItem(String promoName) {
    	int option;
		
		do {
			System.out.println("*****Add Promotion Item*****");
            System.out.println("Select an option");
            System.out.println("1. Add by Name"); 
            System.out.println("2. Add by ID");
            System.out.println("3. End");
            
            option = menuInput.nextInt();
            menuInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(option) {
            case 1: //By Name
            	System.out.println("Enter Name of the Item to Add");
				String name = menuInput.nextLine();
				
				if(checkItemExists(name)) { //if the item exists
					promo.returnPromo(promoName).addItem(returnIndividualMenuItem(name));
					System.out.println(name + " added.");
				}
				else {
					System.out.println(name + " not found.");
				}
				
        		System.out.println();
            	break;
            case 2: //By ID
            	System.out.println("Enter ID of the Item to Add");
				long itemID = menuInput.nextLong();
				menuInput.nextLine(); //clear \n buffer
				
				if(checkItemExists(itemID)) { //if the item exists
					promo.returnPromo(promoName).addItem(returnIndividualMenuItem(itemID));
					System.out.println("ID " + itemID + " added.");
				}
				else {
					System.out.println("ID " + itemID + " not found.");
				}
				
        		System.out.println();
            	break;
            default:
            	break;
            }
		} while(option > 0 && option < 3);
    }

    public void promotionOptionsUpdate() {

    	System.out.println("Enter Name of the Promotion Set to Update");
		String promoName = menuInput.nextLine();
		
		PromoSetMeal foundPromoSet;
		
		if(promo.checkPromoExists(promoName)) { //if the promo exists
			foundPromoSet = promo.returnPromo(promoName);
			System.out.println();
		}
		else {
			System.out.println(promoName + " not found.");
			System.out.println();
			return;
		}
			
    	int choice;
    	
    	do {
			System.out.println("*****Update Promotion Set*****");
			promo.viewIndividualPromo(foundPromoSet.getName());
    		System.out.println("1. Set Name");
    		System.out.println("2. Set Price");
            System.out.println("3. Add Item to Promotion Set");
            System.out.println("4. Remove Item from Promotion Set");
            System.out.println("5. Back");
            choice = menuInput.nextInt();
            menuInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(choice) {
            	case 1: //Name
            		System.out.println("Enter new Name");
            		String newName = menuInput.nextLine();
            		
            		if(promo.checkPromoExists(newName)) { //already has a set named this
                		System.out.println("Promotion Set with " + newName + " already exists!");
                		System.out.println("Name not updated.");
                    	System.out.println();
                	}
                	else {
                		foundPromoSet.setName(newName);
                    	
                    	System.out.println("Name updated to " + newName + ".");
                    	System.out.println();
                	}
                    break;
            	case 2: //Price
            		System.out.println("Enter new Price:");
                	float price = menuInput.nextFloat();
                	menuInput.nextLine(); //clear \n buffer
                	
                	foundPromoSet.setPrice(price);
                	
                	System.out.printf("Price updated to %.2f\n", price);
                	System.out.println();
            		break;
            	case 3: //Add Item
            		addPromotionItem(foundPromoSet.getName());
            		break;
            	case 4: //Remove Item
            		System.out.println("Enter Name of Item to Remove");
            		String removeName = menuInput.nextLine();
            		
            		if(foundPromoSet.doesIncludeItem(removeName)) {
            			foundPromoSet.removeItem(removeName);
            			System.out.println(removeName + " removed from " + foundPromoSet.getName() + ".");
                    	System.out.println();
            		}
            		else {
            			System.out.println(removeName + " not in " + foundPromoSet.getName() + ".");
                    	System.out.println();
            		}
            		break;
            	default:
            		break;
            }
            
		}while(choice > 0 && choice < 5);
    }
    
    public void promotionOptionsDelete() {
    	
    	System.out.println("Enter the name of the promotion set");
		String name = menuInput.nextLine();
    	promo.removePromo(name);
    }

    //Categories
    
    public void categoryOptions() {
    	
    	int choice;
    	
    	do {
			System.out.println("*****Category Options*****");
    		System.out.println("1. View Categories");
    		System.out.println("2. Create Category");
            System.out.println("3. Update Category");
            System.out.println("4. Remove Category");
            System.out.println("5. Save Categories");
            System.out.println("6. Load Categories");
            System.out.println("7. Back");
            choice = menuInput.nextInt();
            menuInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(choice) {
            	case 1: //View Categories
            		cate.viewAllMenuCate();
                    break;
            	case 2: //Create Category
            		categoryOptionsCreate();
            		break;
            	case 3: //Update Category
            		categoryOptionsUpdate();
            		break;
            	case 4: //Remove Category
            		categoryOptionsDelete();
            		break;
            	case 5: //Save Categories
            		cate.saveData();
            		System.out.println("Categories Saved.");
            		System.out.println();
            		break;
            	case 6: //Load Categories
            		cate.getCateList().clear();
                	cate.retrieveData();
                	System.out.println("Categories Loaded.");
            		System.out.println();
            		break;
            	default:
            		break;
            }
            
		}while(choice > 0 && choice < 7);
    }
       
    public void categoryOptionsCreate() {
    	
    	System.out.println("Enter the category name");
		String catName = menuInput.nextLine();
		
		System.out.println("Enter the category ID");
		long catID = menuInput.nextLong();
		menuInput.nextLine(); //clear \n buffer
		
		if(cate.checkCateExists(catName) || cate.checkCateExists(catID)) {
			System.out.println("Error! Category Name/ID already exists.");
		}
		else {
			cate.createMenuCate(catID, catName);
			System.out.println(catName + ": ID " + catID+ " created.");
		}
		
		System.out.println();
    }
    
	public void categoryOptionsUpdate() { 
		
		System.out.println("*****Select Category to Update*****");
        System.out.println("Select an option");
        System.out.println("1. Select by Name"); 
        System.out.println("2. Select by ID");
        System.out.println("3. Back");
        
        int option = menuInput.nextInt();
        menuInput.nextLine(); //clear \n buffer
        System.out.println();
        
        MenuCate foundCate;
        
        switch(option) {
        case 1: //By Name
        	System.out.println("Enter the name of the Category");
			String name = menuInput.nextLine();
			System.out.println();
			
			if(!cate.checkCateExists(name)) {
	    		System.out.println(name + " not found.");
	    		System.out.println();
	    		break;
	    	}
	    	
			foundCate = cate.returnMenuCate(name);
			
			cate.updateMenuCate(foundCate);
			System.out.println();
        	break;
        case 2: //By ID
        	System.out.println("Enter the ID of the Category");
			long cateID = menuInput.nextLong();
			menuInput.nextLine(); //clear \n buffer
			System.out.println();
			
			if(!cate.checkCateExists(cateID)) {
	    		System.out.println("ID " + cateID + " not found.");
	    		System.out.println();
	    		break;
	    	}
	    	
	    	foundCate = cate.returnMenuCate(cateID);
			
	    	cate.updateMenuCate(foundCate);
			System.out.println();
        	break;
        default:
        	break;
        }
	    	
	}
	
	public void categoryOptionsDelete() {
		
		System.out.println("*****Remove Category*****");
        System.out.println("Select an option");
        System.out.println("1. Remove by Name"); 
        System.out.println("2. Remove by ID");
        System.out.println("3. Back");
        
        int option = menuInput.nextInt();
        menuInput.nextLine(); //clear \n buffer
        System.out.println();
        
        switch(option) {
        case 1: //By Name
        	System.out.println("Enter the name of the Category");
			String name = menuInput.nextLine();
			
			deleteMenuCate(name);
			System.out.println();
        	break;
        case 2: //By ID
        	System.out.println("Enter the ID of the Category");
			long itemID = menuInput.nextLong();
			menuInput.nextLine(); //clear \n buffer
			
			deleteMenuCate(itemID);
			System.out.println();
        	break;
        default:
        	break;
        }
	}
	
	public void deleteMenuCate(String cateName) {
		
		if(!cate.checkCateExists(cateName)) {
    		System.out.println(cateName + " not found.");
    		System.out.println();
    		return;
    	}
    	
    	ArrayList<String> foundItems = new ArrayList<String>(returnMenuItemWithMenuCate(cateName));
    	
    	boolean remove = true;
    	
    	if(foundItems.size() == 0) { //menu item is not in a menu item at all
    		remove = true;
    	}
    	else { //menu item is in at least 1 menu item set
    		
    		System.out.println(cateName + " was found in these Menu Items:");
    		
    		for(int i=0; i < foundItems.size(); i++) {
    			System.out.println((i+1) + ". " + foundItems.get(i));
    		}
    		
    		System.out.println();
    		
    		System.out.println("*****Decide what to do with the Menu Items*****");
            System.out.println("Select an option");
            System.out.println("1. Remove the Menu Items"); 
            System.out.println("2. Cancel Removal of Category");
            
            int option = menuInput.nextInt();
            menuInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(option) {
            case 1: //Remove Category
            	
            	for(int i=0; i < foundItems.size(); i++) {
            		
            		removeMenuItemfromPromo(foundItems.get(i), false);
            	}
            	remove = true;
            	break;
            default:
            	remove = false;
            	break;
            }
    		
    	}
    	
    	if(remove) { //if chose to remove category
    		
    		MenuCate foundCate = cate.returnMenuCate(cateName);
    		
    		cate.removeMenuCate(cateName);
        	
        	foundCate = null; //removing the pointer, the item will be deleted by garbage collector
    	}
	}
	
	public void deleteMenuCate(long cateID) {
		
		if(!cate.checkCateExists(cateID)) {
    		System.out.println("Category ID " + cateID + " not found.");
    		System.out.println();
    		return;
    	}
    	
    	ArrayList<String> foundItems = new ArrayList<String>(returnMenuItemWithMenuCate(cateID));
    	
    	boolean remove = true;
    	
    	if(foundItems.size() == 0) { //menu item is not in a menu item at all
    		remove = true;
    	}
    	else { //menu item is in at least 1 menu item set
    		
    		System.out.println("Category ID " + cateID + " was found in these Menu Items:");
    		
    		for(int i=0; i < foundItems.size(); i++) {
    			System.out.println((i+1) + ". " + foundItems.get(i));
    		}
    		
    		System.out.println();
    		
    		System.out.println("*****Decide what to do with the Menu Items*****");
            System.out.println("Select an option");
            System.out.println("1. Remove the Menu Items"); 
            System.out.println("2. Cancel Removal of Category");
            
            int option = menuInput.nextInt();
            menuInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(option) {
            case 1: //Remove Category
            	
            	for(int i=0; i < foundItems.size(); i++) {
            		
            		removeMenuItemfromPromo(foundItems.get(i), false);
            	}
            	remove = true;
            	break;
            default:
            	remove = false;
            	break;
            }
    		
    	}
    	
    	if(remove) { //if chose to remove category
    		
    		MenuCate foundCate = cate.returnMenuCate(cateID);
    		
    		cate.removeMenuCate(cateID);
        	
        	foundCate = null; //removing the pointer, the item will be deleted by garbage collector
    	}
	}
	
	public ArrayList<String> returnMenuItemWithMenuCate(String cateName){
    	
    	ArrayList<String> foundItems = new ArrayList<String>();
    	
    	for(int i=0; i < menuList.size(); i++) {
    		
    		if(menuList.get(i).getItemCate().getCatName().equals(cateName)) { //look for the cate
    			foundItems.add(menuList.get(i).getName()); //add this menu item to the list
    		}
    	}
    	
    	return foundItems;
    }
	
	public ArrayList<String> returnMenuItemWithMenuCate(long cateID){
    	
    	ArrayList<String> foundItems = new ArrayList<String>();
    	
    	for(int i=0; i < menuList.size(); i++) {
    		
    		if(menuList.get(i).getItemCate().getCatID() == cateID) { //look for the cate
    			foundItems.add(menuList.get(i).getName()); //add this menu item to the list
    		}
    	}
    	
    	return foundItems;
    }
    
	//FlatFile
	
    @Override
    public void createFlatFileAdapter() {
        super.addFlatFileAdapter(new FlatFileAdapter(){
            @Override
            public String getFileName() {
                return "menu.csv";
            }

            @Override
            public String getColumnsName() {
                return "Item ID,Item Name,Price,Category,Description";
            }

            @Override
            public String insertRow(int index) {
                try{
                    return menuList.get(index).toCSVFormat();
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            public void extractRow(String[] row) {
            	
                createMenuItem(row[1], row[4], Float.parseFloat(row[2]), 
                		Long.parseLong(row[0]), cate.returnMenuCate(row[3]));
            }
        });  
    
    }
}
