package RestaurantProject;

import java.util.ArrayList;

import RestaurantProject.FlatFile.CSVFormat;

public class PromoSetMeal implements CSVFormat{
	
	private String name;
	private ArrayList<MenuItem> mealItems = new ArrayList<MenuItem>();
	private ArrayList<String> itemNameList = new ArrayList<String>();
	private float price;
	
	public PromoSetMeal(String name, float price){
		
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
	
	public ArrayList<MenuItem> getMealItems(){
		return mealItems;
	}
	
	public void setMealItems(ArrayList<MenuItem> mealItemList) {
		//effectively replaces the old ArrayList with the newly input one
		mealItems.clear(); //clear all items
		mealItems.addAll(mealItemList); //add all items from the given list
	}
	
	public ArrayList<String> getItemNameList(){
		return itemNameList;
	}
	
	public void setItemNameList(ArrayList<String> itemList) {
		//effectively replaces the old ArrayList with the newly input one
		itemNameList.clear(); //clear all items
		itemNameList.addAll(itemList); //add all items from the given list
	}
	
	public void addItem(MenuItem newItem) {
		
		mealItems.add(newItem);
		//itemNameList.add(newItem.getName());
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
	
	public void mealItemstoNameList() {
		
		itemNameList.clear();
		
		for(int i=0; i < mealItems.size(); i++) {
			itemNameList.add(mealItems.get(i).getName());
		}
	}
	
	
	@Override
    public String toCSVFormat() {
		
		mealItemstoNameList();
		
		String itemNameListtoString = String.join("|", itemNameList);
		
        return name + "," +
        		price + "," +
        		itemNameListtoString; 
    }

}
