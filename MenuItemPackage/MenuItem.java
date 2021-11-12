package MenuItemPackage;

import FlatFile.CSVFormat;

import MenuCatePackage.*;

/**
 * This is an entity class that holds information about each item/product in the
 * menu
 */
public class MenuItem implements CSVFormat {

	/**
	 * Holds the name of the MenuItem
	 */
	private String name;
	/**
	 * Holds the description of the MenuItem
	 */
	private String description;
	/**
	 * Holds the price of the MenuItem
	 */
	private float price;
	/**
	 * Holds the ID of the MenuItem
	 */
	private long itemID;
	/**
	 * Holds the category of the MenuItem
	 */
	private MenuCate itemCate;

	/**
	 * Constructor to create a MenuItem
	 * 
	 * @param name        Name of the MenuItem
	 * @param description Description of the MenuItem
	 * @param price       Price of the MenuItem
	 * @param itemID      ID of the MenuItem
	 * @param itemCate    Category of the MenuItem
	 */
	public MenuItem(String name, String description, float price, long itemID, MenuCate itemCate) {

		this.name = name;
		this.description = description;
		this.price = price;
		this.itemID = itemID;
		this.itemCate = itemCate;

	}

	/**
	 * Gets the name of the MenuItem
	 * 
	 * @return Name of this MenuItem
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the MenuItem
	 * 
	 * @param name The new name of this MenuItem
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the MenuItem
	 * 
	 * @return Description of this MenuItem
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of the MenuItem
	 * 
	 * @param description The new description of this MenuItem
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the price of the MenuItem
	 * 
	 * @return Price of this MenuItem
	 */
	public float getPrice() {
		return this.price;
	}

	/**
	 * Sets the price of the MenuItem
	 * 
	 * @param price The new price of this MenuItem
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Gets the ID of the MenuItem
	 * 
	 * @return ID of this MenuItem
	 */
	public float getItemID() {
		return this.itemID;
	}

	/**
	 * Sets the ID of the MenuItem
	 * 
	 * @param itemID The new ID of this MenuItem
	 */
	public void setItemID(long itemID) {
		this.itemID = itemID;
	}

	/**
	 * Gets the category of the MenuItem
	 * 
	 * @return MenuCate object assigned to this MenuItem
	 */
	public MenuCate getItemCate() {
		return this.itemCate;
	}

	/**
	 * Sets the category of the MenuItem
	 * 
	 * @param itemCate MenuCate object to assign to this MenuItem
	 */
	public void setItemCate(MenuCate itemCate) {
		this.itemCate = itemCate;
	}

	/**
	 * Method to return the details of this MenuItem in a csv format
	 * 
	 * @return Details of this MenuItem in a csv format
	 */
	@Override
	public String toCSVFormat() {
		return itemID + "," + name + "," + price + "," + itemCate.getCatName() + "," + description;
	}

}
