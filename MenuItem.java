package oopProject;

enum MenuCates {APPETIZER, MAIN_COURSE, SOUP, DRINK, DESSERT, SIDE}

public class MenuItem {
	
	private String name;
	private String description;
	private float price;
	private long itemID;
	private MenuCates itemCate;
	
	public MenuItem(String name, String description, float price, 
					long itemID, MenuCates itemCate) {
		
		this.name = name;
		this.description = description;
		this.price = price;
		this.itemID = itemID;
		this.itemCate = itemCate;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	} 
	
	public float getPrice() {
		return this.price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	} 
	
	public float getItemID() {
		return this.itemID;
	}
	
	public void setItemID(long itemID) {
		this.itemID = itemID;
	} 
	
	public MenuCates getItemCate() {
		return this.itemCate;
	}
	
	public void setItemCate(MenuCates itemCate) {
		this.itemCate = itemCate;
	} 
	

}
