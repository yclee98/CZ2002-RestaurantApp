package MenuItemPackage;

import java.util.ArrayList;

import MenuCatePackage.MenuCate;

/**
 * This is a controller class that manages all the entities of MenuItem
 */
public class ItemManager {

	/**
	 * The list of all the MenuItems
	 */
	private ArrayList<MenuItem> itemList = new ArrayList<MenuItem>();

	/**
	 * Default constructor to create a new ItemManager
	 */
	public ItemManager() {

	}

	/**
	 * Gets the list of all MenuItems
	 * 
	 * @return List of all MenuItems
	 */
	public ArrayList<MenuItem> getItemList() {
		return itemList;
	}

	/**
	 * Method to create a new MenuItem and add it to the list of all MenuItems
	 * 
	 * @param name        Name of the MenuItem
	 * @param description Description of the MenuItem
	 * @param price       Price of the MenuItem
	 * @param itemID      ID of the MenuItem
	 * @param itemCate    Category of the MenuItem
	 */
	public void createMenuItem(String name, String description, float price, long itemID, MenuCate itemCate) {
		MenuItem temp = new MenuItem(name, description, price, itemID, itemCate);

		itemList.add(temp);

	}

	// Viewing Items from List

	/**
	 * Method to print out all the MenuItems in the system
	 */
	public void viewAllMenuItems() {
		// Printing names based on creation time
		for (int i = 0; i < itemList.size(); i++) {
			System.out.println("--------------------");
			System.out.printf((i + 1) + ". " + itemList.get(i).getName() + " | $%.2f\n", itemList.get(i).getPrice());
			System.out.printf("ID: %.0f\n", itemList.get(i).getItemID());
			System.out.println(itemList.get(i).getItemCate().getCatName());
			System.out.println(itemList.get(i).getDescription());
			System.out.println("--------------------");
		}

		System.out.println();
	}

	/**
	 * Method to print out a single MenuItem by ID
	 * 
	 * @param itemId The ID of the MenuItem to print
	 */
	public void viewIndividualMenuItem(long itemId) {

		if (!checkItemExists(itemId)) {
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

	/**
	 * Method to print out a single MenuItem by name
	 * 
	 * @param itemName The name of the MenuItem to print
	 */
	public void viewIndividualMenuItem(String itemName) {

		if (!checkItemExists(itemName)) {
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

	/**
	 * Method to return the MenuItem object by supplying its ID
	 * 
	 * @param itemId The ID of the MenuItem object to return
	 * @return MenuItem object found from the supplied ID
	 */
	public MenuItem returnIndividualMenuItem(long itemId) {

		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemID() == itemId) {
				return itemList.get(i); // found
			}
		}

		return null; // returns null if not found
	}

	/**
	 * Method to return the MenuItem object by supplying its name
	 * 
	 * @param itemName The name of the MenuItem object to return
	 * @return MenuItem object found from the supplied name
	 */
	public MenuItem returnIndividualMenuItem(String itemName) {

		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getName().equals(itemName)) {
				return itemList.get(i); // found
			}
		}

		return null; // returns null if not found
	}

	/**
	 * Method to return the MenuItem object by supplying its index in the list of
	 * all MenuItems
	 * 
	 * @param index The index of the MenuItem object to return
	 * @return MenuItem object found from the supplied index
	 */
	public MenuItem returnIndividualMenuItem(int index) {
		if (index > itemList.size() || index <= 0) {
			return null;
		}
		return itemList.get(index - 1);
	}

	/**
	 * Method to check if a MenuItem exists by supplying its name
	 * 
	 * @param itemName The name of the MenuItem to look for
	 * @return The boolean value of whether the MenuItem exists
	 */
	public boolean checkItemExists(String itemName) {

		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getName().equals(itemName)) {
				return true; // exists
			}
		}

		return false; // does not exist

	}

	/**
	 * Method to check if a MenuItem exists by supplying its ID
	 * 
	 * @param itemID The ID of the MenuItem to look for
	 * @return The boolean value of whether the MenuItem exists
	 */
	public boolean checkItemExists(long itemID) {

		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemID() == itemID) {
				return true; // exists
			}
		}

		return false; // does not exist

	}

}
