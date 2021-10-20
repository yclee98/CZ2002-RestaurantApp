import java.util.ArrayList;
import java.util.List;

public class MenuManager {
	
	private List<MenuItem> menuList = new ArrayList<MenuItem>();
	private List<MenuCate> cateList = new ArrayList<MenuCate>();
	
	public MenuManager() {
	}
	
	public void createMenuItem(String name, String description, 
			float price, long itemID, MenuCate itemCate) {
		MenuItem temp = new MenuItem(name, description, price, itemID, itemCate);
		
		menuList.add(temp);
        
    }

    public void viewAllMenuItems() {
    	//Printing names based on creation time
    	for(int i=0; i < menuList.size(); i++) {
			System.out.println(menuList.get(i).getName());
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
    	
    	if(found) System.out.println(foundItem.getItemID() + " removed.");
    	else System.out.println(foundItem.getItemID() + " not found.");
    	
    	foundItem = null; //removing the pointer, the item will be deleted by garbage collector
    }
    
    public void removeMenuItem(String itemName){
    	boolean found = false;
    	MenuItem foundItem = null;
    	
    	for(int i=0; i < menuList.size(); i++) {
			if(menuList.get(i).getName() == itemName) {
				found = true; //found item ID
				foundItem = menuList.get(i);
				menuList.remove(i); //remove from menuList
				break; //exit for loop
			}
		}
    	
    	if(found) System.out.println(foundItem.getItemID() + " removed.");
    	else System.out.println(foundItem.getItemID() + " not found.");
    	
    	foundItem = null; //removing the pointer, the item will be deleted by garbage collector
    }

    public void createMenuCate(String catName, long catID) {
    	
    	MenuCate temp = new MenuCate(catName, catID);
    	
    	cateList.add(temp); //add this category to the list
    	
    }

    public boolean checkCateExists(String catName) {
    	
    	for(int i=0; i < cateList.size(); i++) {
    		if(cateList.get(i).getCatName().equals(catName)) {
    			return true; //exists
    		}
    	}

    	return false; //does not exist
    	
    }
    
    public boolean checkCateExists(long catID) {
    	
    	for(int i=0; i < cateList.size(); i++) {
    		if(cateList.get(i).getCatID() == catID) {
    			return true; //exists
    		}
    	}

    	return false; //does not exist
    	
    }
    
    public MenuCate returnMenuCate(String catName) {
    	
    	for(int i=0; i < cateList.size(); i++) {
    		if(cateList.get(i).getCatName().equals(catName)) {
    			return cateList.get(i); //exists
    		}
    	}

    	return null; //does not exist
    	
    }
    
    public MenuCate returnMenuCate(long catID) {
    	
    	for(int i=0; i < cateList.size(); i++) {
    		if(cateList.get(i).getCatID() == catID) {
    			return cateList.get(i); //exists
    		}
    	}

    	return null; //does not exist
    	
    }

    public void viewAllMenuCate() {
    	//Printing names based on creation time
    	System.out.println(cateList.size());
    	for(int i=0; i < cateList.size(); i++) {
			System.out.println(cateList.get(i).getCatName());
		}
    }

}
