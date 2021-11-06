package MenuItemPackage;

import java.util.ArrayList;

import MenuCatePackage.MenuCate;

public class ItemManager {
	
	private ArrayList<MenuItem> itemList = new ArrayList<MenuItem>();

	public ItemManager() {

	}
	
	public ArrayList<MenuItem> getItemList(){
		return itemList;
	}
	
	public void createMenuItem(String name, String description, 
			float price, long itemID, MenuCate itemCate) {
		MenuItem temp = new MenuItem(name, description, price, itemID, itemCate);
		
		itemList.add(temp);
        
    }

	//Viewing Items from List
	
    public void viewAllMenuItems() {
    	//Printing names based on creation time
    	for(int i=0; i < itemList.size(); i++) {
    		System.out.println("--------------------");
			System.out.printf((i+1)+ ". " + itemList.get(i).getName() + " | $%.2f\n", itemList.get(i).getPrice());
			System.out.printf("ID: %.0f\n", itemList.get(i).getItemID());
			System.out.println(itemList.get(i).getItemCate().getCatName());
			System.out.println(itemList.get(i).getDescription());
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
    	
    	for(int i=0; i < itemList.size(); i++) {
			if(itemList.get(i).getItemID() == itemId) {
				return itemList.get(i); //found
			}
		}
    	
    	return null; //returns null if not found
    }
    
    public MenuItem returnIndividualMenuItem(String itemName){
    	
    	for(int i=0; i < itemList.size(); i++) {
			if(itemList.get(i).getName().equals(itemName)) {
				return itemList.get(i); //found
			}
		}
    	
    	return null; //returns null if not found
    }
	// ************** Added this to return Item by index ******************
	public MenuItem returnIndividualMenuItem(int index){
		return itemList.get(index-1);
	}
    
    public boolean checkItemExists(String itemName) {
    	
    	for(int i=0; i < itemList.size(); i++) {
    		if(itemList.get(i).getName().equals(itemName)) {
    			return true; //exists
    		}
    	}

    	return false; //does not exist
    	
    }
    public boolean checkItemExists(long itemID) {
    	
    	for(int i=0; i < itemList.size(); i++) {
    		if(itemList.get(i).getItemID() == itemID) {
    			return true; //exists
    		}
    	}

    	return false; //does not exist
    	
    }

    
}
