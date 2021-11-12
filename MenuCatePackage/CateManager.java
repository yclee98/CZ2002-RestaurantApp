package MenuCatePackage;

import java.util.ArrayList;
import java.util.Scanner;

import FlatFile.FlatFileAdapter;
import Utility.Manager;

/**
 * This is a controller class that manages all the entities of MenuCate
 */
public class CateManager extends Manager {

	/**
	 * Scanner object to accept user input
	 */
	private Scanner cateInput = new Scanner(System.in);

	/**
	 * The list of all the MenuCates
	 */
	private ArrayList<MenuCate> cateList = new ArrayList<MenuCate>();

	/**
	 * Default constructor to create a new CateManager
	 */
	public CateManager() {

	}

	/**
	 * Gets the list of all MenuCates
	 * 
	 * @return List of all MenuCates
	 */
	public ArrayList<MenuCate> getCateList() {
		return cateList;
	}

	/**
	 * Method to create a new MenuCate and add it to the list of all MenuCates
	 * 
	 * @param catID   ID of the MenuCate
	 * @param catName Name of the MenuCate
	 */
	public void createMenuCate(long catID, String catName) {

		MenuCate temp = new MenuCate(catID, catName);

		cateList.add(temp); // add this category to the list

	}

	/**
	 * Method to update the information of a MenuCate object
	 * 
	 * @param foundCate The MenuCate object to edit
	 */
	public void updateMenuCate(MenuCate foundCate) {

		int option = 0;

		do {
			System.out.println("*****Update Category*****");
			viewIndividualMenuCate(foundCate.getCatName());
			System.out.println("Select an option to edit");
			System.out.println("1. Name");
			System.out.println("2. Category ID");
			System.out.println("3. Back");

			option = cateInput.nextInt();
			cateInput.nextLine(); // clear \n buffer
			System.out.println();

			switch (option) {
			case 1: // Name
				System.out.println("Enter new Name:");
				String name = cateInput.nextLine();

				if (checkCateExists(name)) { // already has an category named this
					System.out.println("Category called " + name + " already exists!");
					System.out.println("Name not updated.");
					System.out.println();
				} else {
					foundCate.setCatName(name);

					System.out.println("Name updated to " + name + ".");
					System.out.println();
				}
				break;
			case 2: // ID
				System.out.println("Enter new Category ID:");
				long cateId = cateInput.nextLong();
				cateInput.nextLine(); // clear \n buffer

				if (checkCateExists(cateId)) { // already has a category with this ID
					System.out.println("Category with ID " + cateId + " already exists!");
					System.out.println("Category ID not updated.");
					System.out.println();
				} else {
					foundCate.setCatID(cateId);

					System.out.println("Category ID updated to " + cateId + ".");
					System.out.println();
				}
				break;
			default:
				break;
			}
		} while (option > 0 && option < 3);
	}

	/**
	 * Method to delete a MenuCate by name
	 * 
	 * @param catName The name of the MenuCate object to delete
	 */
	public void removeMenuCate(String catName) {

		boolean found = false;
		MenuCate foundCate = null;

		for (int i = 0; i < cateList.size(); i++) {
			if (cateList.get(i).getCatName().equals(catName)) {
				found = true; // found item name
				foundCate = cateList.get(i);
				cateList.remove(i); // remove from menuList
				break; // exit for loop
			}
		}

		if (found)
			System.out.println(catName + " removed.");
		else
			System.out.println(catName + " not found.");

		foundCate = null; // removing the pointer, the item will be deleted by garbage collector

	}

	/**
	 * Method to delete a MenuCate by ID
	 * 
	 * @param catID The ID of the MenuCate object to delete
	 */
	public void removeMenuCate(long catID) {

		boolean found = false;
		MenuCate foundCate = null;

		for (int i = 0; i < cateList.size(); i++) {
			if (cateList.get(i).getCatID() == catID) {
				found = true; // found item name
				foundCate = cateList.get(i);
				cateList.remove(i); // remove from menuList
				break; // exit for loop
			}
		}

		if (found)
			System.out.println("Category ID " + catID + " removed.");
		else
			System.out.println("Category ID " + catID + " not found.");

		foundCate = null; // removing the pointer, the item will be deleted by garbage collector

		System.out.println();
	}

	/**
	 * Method to print out all the MenuCates in the system
	 */
	public void viewAllMenuCate() {
		// Printing names based on creation time
		for (int i = 0; i < cateList.size(); i++) {
			System.out.println("--------------------");
			System.out.println(cateList.get(i).getCatName() + " | ID: " + cateList.get(i).getCatID());
			System.out.println("--------------------");
		}

		System.out.println();
	}

	/**
	 * Method to print out a single MenuCate by name
	 * 
	 * @param catName The name of the MenuCate to print
	 */
	public void viewIndividualMenuCate(String catName) {

		if (!checkCateExists(catName)) {
			System.out.println(catName + " not found.");
			System.out.println();
			return;
		}

		MenuCate foundCate = returnMenuCate(catName);

		System.out.println("--------------------");
		System.out.println(foundCate.getCatName() + " | ID: " + foundCate.getCatID());
		System.out.println("--------------------");
	}

	/**
	 * Method to return the MenuCate object by supplying its name
	 * 
	 * @param catName The name of the MenuCate object to return
	 * @return MenuCate object found from the supplied name
	 */
	public MenuCate returnMenuCate(String catName) {

		for (int i = 0; i < cateList.size(); i++) {
			if (cateList.get(i).getCatName().equals(catName)) {
				return cateList.get(i); // exists
			}
		}

		return null; // does not exist

	}

	/**
	 * Method to return the MenuCate object by supplying its ID
	 * 
	 * @param catID The ID of the MenuCate object to return
	 * @return MenuCate object found from the supplied ID
	 */
	public MenuCate returnMenuCate(long catID) {

		for (int i = 0; i < cateList.size(); i++) {
			if (cateList.get(i).getCatID() == catID) {
				return cateList.get(i); // exists
			}
		}

		return null; // does not exist

	}

	/**
	 * Method to check if a MenuCate exists by supplying its name
	 * 
	 * @param catName The name of the MenuCate to look for
	 * @return The boolean value of whether the MenuCate exists
	 */
	public boolean checkCateExists(String catName) {

		for (int i = 0; i < cateList.size(); i++) {
			if (cateList.get(i).getCatName().equals(catName)) {
				return true; // exists
			}
		}

		return false; // does not exist

	}

	/**
	 * Method to check if a MenuCate exists by supplying its ID
	 * 
	 * @param catID The ID of the MenuCate to look for
	 * @return The boolean value of whether the MenuCate exists
	 */
	public boolean checkCateExists(long catID) {

		for (int i = 0; i < cateList.size(); i++) {
			if (cateList.get(i).getCatID() == catID) {
				return true; // exists
			}
		}

		return false; // does not exist

	}

	/**
	 * Override the parent abstract method to create FlatFileAdapter use for
	 * saving/retrieving of data from the csv file
	 */
	@Override
	public void createFlatFileAdapter() {
		super.addFlatFileAdapter(new FlatFileAdapter() {
			@Override
			public String getFileName() {
				return "cate.csv";
			}

			@Override
			public String getColumnsName() {
				return "Category ID,Category Name";
			}

			@Override
			public String insertRow(int index) {
				try {
					return cateList.get(index).toCSVFormat();
				} catch (Exception e) {
					return null;
				}
			}

			@Override
			public void extractRow(String[] row) {

				createMenuCate(Long.parseLong(row[0]), row[1]);
			}
		});

	}
}
