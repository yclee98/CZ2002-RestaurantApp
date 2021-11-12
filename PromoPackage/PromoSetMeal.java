package PromoPackage;

import java.util.ArrayList;

import MenuItemPackage.MenuItem;
import FlatFile.CSVFormat;

/**
 * This is an entity class that holds information about each promotional set in
 * the menu
 */
public class PromoSetMeal implements CSVFormat {

	/**
	 * Holds the name of the PromoSetMeal
	 */
	private String name;
	/**
	 * List of the MenuItems included in the PromoSetMeal
	 */
	private ArrayList<MenuItem> mealItems = new ArrayList<MenuItem>();
	/**
	 * List of the names of the MenuItems included in the PromoSetMeal
	 */
	private ArrayList<String> itemNameList = new ArrayList<String>();
	/**
	 * Holds the price of the PromoSetMeal
	 */
	private float price;

	/**
	 * Constructor to create a PromoSetMeal
	 * 
	 * @param name  Name of the PromoSetMeal
	 * @param price Price of the PromoSetMeal
	 */
	public PromoSetMeal(String name, float price) {

		this.name = name;
		this.price = price;

	}

	/**
	 * Gets the name of the PromoSetMeal
	 * 
	 * @return Name of this PromoSetMeal
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of the PromoSetMeal
	 * 
	 * @param name The new name of this MenuItem
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the price of the PromoSetMeal
	 * 
	 * @return Price of this PromoSetMeal
	 */
	public float getPrice() {
		return this.price;
	}

	/**
	 * Sets the price of the PromoSetMeal
	 * 
	 * @param price The new price of this MenuItem
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Gets the List of the MenuItems included in the PromoSetMeal
	 * 
	 * @return The list of the MenuItems included in this PromoSetMeal
	 */
	public ArrayList<MenuItem> getMealItems() {
		return mealItems;
	}

	/**
	 * Sets the List of the MenuItems included in the PromoSetMeal
	 * 
	 * @param mealItemList The List of the MenuItems to be included in this
	 *                     PromoSetMeal
	 */
	public void setMealItems(ArrayList<MenuItem> mealItemList) {
		// effectively replaces the old ArrayList with the newly input one
		mealItems.clear(); // clear all items
		mealItems.addAll(mealItemList); // add all items from the given list
	}

	/**
	 * Gets the List of names of the MenuItems included in the PromoSetMeal
	 * 
	 * @return List of names of the MenuItems included in this PromoSetMeal
	 */
	public ArrayList<String> getItemNameList() {
		return itemNameList;
	}

	/**
	 * Sets the List of names of the MenuItems included in the PromoSetMeal
	 * 
	 * @param itemList The List of names of the MenuItems to be included in this
	 *                 PromoSetMeal
	 */
	public void setItemNameList(ArrayList<String> itemList) {
		// effectively replaces the old ArrayList with the newly input one
		itemNameList.clear(); // clear all items
		itemNameList.addAll(itemList); // add all items from the given list
	}

	/**
	 * Add a MenuItem to the PromoSetMeal
	 * 
	 * @param newItem MenuItem object to be added to this PromoSetMeal
	 */
	public void addItem(MenuItem newItem) {

		mealItems.add(newItem);
	}

	/**
	 * Change from an old MenuItem to a new MenuItem
	 * 
	 * @param newItem MenuItem object to be added to this PromoSetMeal
	 * @param oldItem MenuItem object to be replaced
	 * @return Integer value to indicate success or failure
	 */
	public int changeItem(MenuItem newItem, MenuItem oldItem) { // must pass in MenuItem objects

		if (!mealItems.contains(oldItem)) {

			System.out.println(oldItem.getName() + " not found.");

			return 0; // return 0 to indicate failure
		}

		for (int i = 0; i < mealItems.size(); i++) {

			if (mealItems.get(i).getName().equals(oldItem.getName())) {

				mealItems.set(i, newItem);

			}
		}

		System.out.println(oldItem.getName() + " successfully changed to " + newItem.getName() + ".");

		return 1; // return 1 to indicate success

	}

	/**
	 * Remove a MenuItem from the PromoSetMeal
	 * 
	 * @param itemName The name of the MenuItem object to be removed from this
	 *                 PromoSetMeal
	 */
	public void removeItem(String itemName) {

		int i;

		for (i = 0; i < mealItems.size(); i++) {

			if (mealItems.get(i).getName().equals(itemName)) {
				mealItems.remove(i);
			}
		}

	}

	/**
	 * Method to check by name if this PromoSetMeal contains a MenuItem
	 * 
	 * @param itemName The name of the MenuItem to look for
	 * @return The boolean value of whether the MenuItem exists
	 */
	public boolean doesIncludeItem(String itemName) {

		for (int i = 0; i < mealItems.size(); i++) {
			if (mealItems.get(i).getName().equals(itemName)) {
				return true; // found
			}
		}

		return false; // not found
	}

	/**
	 * Method to check by ID if this PromoSetMeal contains a MenuItem
	 * 
	 * @param itemID The ID of the MenuItem to look for
	 * @return The boolean value of whether the MenuItem exists
	 */
	public boolean doesIncludeItem(long itemID) {

		for (int i = 0; i < mealItems.size(); i++) {
			if (mealItems.get(i).getItemID() == itemID) {
				return true; // found
			}
		}

		return false; // not found
	}

	/**
	 * Method to convert the list of MenuItems in this PromoSetMeal into the list of
	 * names of the MenuItems in this PromoSetMeal
	 */
	public void mealItemstoNameList() {

		itemNameList.clear();

		for (int i = 0; i < mealItems.size(); i++) {
			itemNameList.add(mealItems.get(i).getName());
		}
	}

	/**
	 * Method to return the details of this PromoSetMeal in a csv format
	 * 
	 * @return Details of this PromoSetMeal in a csv format
	 */
	@Override
	public String toCSVFormat() {

		mealItemstoNameList();

		String itemNameListtoString = String.join("|", itemNameList);

		return name + "," + price + "," + itemNameListtoString;
	}

}
