package RestaurantProject;

import java.util.ArrayList;

import RestaurantProject.FlatFile.FlatFileAdapter;

public class MenuManager extends Manager {
	
	public ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
	
	public PromoManager promo = new PromoManager();
	
	public CateManager cate = new CateManager();
	
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
    }

    public void viewIndividualMenuItem(long itemId){
    	boolean found = false;
    	MenuItem foundItem = null;
    	
    	for(int i=0; i < menuList.size(); i++) {
			if(menuList.get(i).getItemID() == itemId) {
				found = true; //found item ID
				foundItem = menuList.get(i);
				break; //exit for loop
			}
		}
    	
    	if(found) {
    		System.out.println("Name: " + foundItem.getName());
    		System.out.println("Category: " + foundItem.getItemCate());
			System.out.println("Price: " + foundItem.getPrice());
			System.out.println("Description:\n" + foundItem.getDescription());
			System.out.println("Item ID: " + foundItem.getItemID());
			
    	}
    }
    
    public void viewIndividualMenuItem(String itemName){
    	boolean found = false;
    	MenuItem foundItem = null;
    	
    	for(int i=0; i < menuList.size(); i++) {
			if(menuList.get(i).getName().equals(itemName)) {
				found = true; //found item ID
				foundItem = menuList.get(i);
				break; //exit for loop
			}
		}
    	
    	if(found) {
    		System.out.println("Name: " + foundItem.getName());
    		System.out.println("Category: " + foundItem.getItemCate());
			System.out.println("Price: " + foundItem.getPrice());
			System.out.println("Description:\n" + foundItem.getDescription());
			System.out.println("Item ID: " + foundItem.getItemID());
			
    	}
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
    
    public void updateMenuItem(long itemId){
        //can do polymorphism to update no need create seperately for ala carte and promotion as both inherit the same
    }

    public void removeMenuItem(long itemId){
    	boolean found = false;
    	MenuItem foundItem = null;
    	
    	for(int i=0; i < menuList.size(); i++) {
			if(menuList.get(i).getItemID() == itemId) {
				found = true; //found item ID
				foundItem = menuList.get(i);
				menuList.remove(i); //remove from menuList
				break; //exit for loop
			}
		}
    	
    	if(found) System.out.println("ID " + itemId + " removed.");
    	else System.out.println("ID " + itemId + " not found.");
    	
    	foundItem = null; //removing the pointer, the item will be deleted by garbage collector
    }
    
    public void removeMenuItem(String itemName){
    	boolean found = false;
    	MenuItem foundItem = null;
    	
    	for(int i=0; i < menuList.size(); i++) {
			if(menuList.get(i).getName().equals(itemName)){
				found = true; //found item name
				foundItem = menuList.get(i);
				menuList.remove(i); //remove from menuList
				break; //exit for loop
			}
		}
    	
    	if(found) System.out.println(itemName + " removed.");
    	else System.out.println(itemName + " not found.");
    	
    	foundItem = null; //removing the pointer, the item will be deleted by garbage collector
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
