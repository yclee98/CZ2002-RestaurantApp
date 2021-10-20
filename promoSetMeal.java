import java.util.ArrayList;
import java.util.List;

public class promoSetMeal {
	
	private String name;
	private List<MenuItem> mealItems = new ArrayList<MenuItem>();
	private float price;
	
	public promoSetMeal(String name, float price){
		
		this.name = name;
		this.price = price;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void addItem(MenuItem newItem) {
		
		mealItems.add(newItem);
	}
	
	public boolean doesIncludeItem(String itemName) {
		
		for(int i=0; i < mealItems.size(); i++) {
    		if(mealItems.get(i).getName().equals(itemName)) {
    			return true; //found
    		}
    	}

    	return false; //not found
	}
	
	public boolean doesIncludeItem(long itemID) {
		
		for(int i=0; i < mealItems.size(); i++) {
    		if(mealItems.get(i).getItemID() == itemID) {
    			return true; //found
    		}
    	}

    	return false; //not found
	}
	
	public int changeItem(MenuItem newItem, MenuItem oldItem) { //must pass in MenuItem objects
		
		if(!mealItems.contains(oldItem)) {
			
			System.out.println(
					oldItem.getName() + 
					" not found.");
			
			return 0; //return 0 to indicate failure
		}
		
		for(int i=0; i< mealItems.size(); i++) {
			
			if(mealItems.get(i).getName().equals(oldItem.getName())) {
				
				mealItems.set(i, newItem);
				
			}
		}
		
		System.out.println(
				oldItem.getName() + 
				" successfully changed to " + 
				newItem.getName() + ".");
		
		return 1; //return 1 to indicate success
		
		
		
	}
	
	public void printDescriptions() {
		
		for(int i=0; i < mealItems.size(); i++) {
			System.out.println(mealItems.get(i).getDescription());
		}
	}
	
	public void printNames() {
		
		for(int i=0; i < mealItems.size(); i++) {
			System.out.println(mealItems.get(i).getName());
		}
	}

}
