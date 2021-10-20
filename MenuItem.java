public class MenuItem {
	
	private String name;
	private String description;
	private float price;
	private long itemID;
	private MenuCate itemCate;
	
	public MenuItem(String name, String description, float price, 
					long itemID, MenuCate itemCate) {
		
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
	
	public MenuCate getItemCate() {
		return this.itemCate;
	}
	
	public void setItemCate(MenuCate itemCate) {
		this.itemCate = itemCate;
	} 
	

}
