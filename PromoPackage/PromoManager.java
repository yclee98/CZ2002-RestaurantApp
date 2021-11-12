package PromoPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utility.Manager;
import FlatFile.FlatFileAdapter;

/**
 * This is a controller class that manages all the entities of PromoSetMeal
 */
public class PromoManager extends Manager {

	/**
	 * The list of all the PromoSetMeals
	 */
	private ArrayList<PromoSetMeal> promoList = new ArrayList<PromoSetMeal>();

	/**
	 * Default constructor to create a new PromoManager
	 */
	public PromoManager() {

	}

	/**
	 * Gets the list of all PromoSetMeals
	 * 
	 * @return List of all PromoSetMeals
	 */
	public ArrayList<PromoSetMeal> getPromoList() {
		return promoList;
	}

	/**
	 * Sets the list of all PromoSetMeals
	 * 
	 * @param promoItemList The new list of all PromoSetMeals
	 */
	public void setPromoList(ArrayList<PromoSetMeal> promoItemList) {
		// effectively replaces the old ArrayList with the newly input one
		promoList.clear(); // clear all items
		promoList.addAll(promoItemList); // add all items from the given list
	}

	/**
	 * Method to create a new PromoSetMeal without MenuItems and add it to the list
	 * of all PromoSetMeals
	 * 
	 * @param name  Name of the PromoSetMeal
	 * @param price Price of the PromoSetMeal
	 */
	public void createPromoSetMeal(String name, float price) {

		PromoSetMeal temp = new PromoSetMeal(name, price);

		promoList.add(temp); // add this promotion set to the list
	}

	/**
	 * Method to create a new PromoSetMeal (with a list of names of its MenuItems)
	 * and add it to the list of all PromoSetMeals
	 * 
	 * @param name          Name of the PromoSetMeal
	 * @param price         Price of the PromoSetMeal
	 * @param promoItemList List of names of MenuItems included in the PromoSetMeal
	 */
	public void createPromoSetMeal(String name, float price, ArrayList<String> promoItemList) {

		PromoSetMeal temp = new PromoSetMeal(name, price);

		promoList.add(temp); // add this promotion set to the list

		for (int i = 0; i < promoItemList.size(); i++) {
			temp.getItemNameList().add(promoItemList.get(i));
		}
	}

	/**
	 * Method to delete a PromoSetMeal by name
	 * 
	 * @param promoName The name of the PromoSetMeal object to delete
	 */
	public void removePromo(String promoName) {
		boolean found = false;
		PromoSetMeal foundPromo = null;

		for (int i = 0; i < promoList.size(); i++) {
			if (promoList.get(i).getName().equals(promoName)) {
				found = true; // found promo name
				foundPromo = promoList.get(i);
				promoList.remove(i); // remove from promoList
				break; // exit for loop
			}
		}

		if (found)
			System.out.println(promoName + " removed.");
		else
			System.out.println(promoName + " not found.");

		foundPromo = null; // removing the pointer, the promo will be deleted by garbage collector

		System.out.println();
	}

	/**
	 * Method to print out all the PromoSetMeals in the system
	 */
	public void viewAllPromo() {
		// Printing names based on creation time
		for (int i = 0; i < promoList.size(); i++) {
			System.out.println("--------------------");
			System.out.printf(promoList.get(i).getName() + " | $%.2f\n", promoList.get(i).getPrice());

			for (int j = 0; j < promoList.get(i).getMealItems().size(); j++) {
				System.out.println((j + 1) + ". " + promoList.get(i).getMealItems().get(j).getName());
			}
			System.out.println("--------------------");
		}

		System.out.println();
	}

	/**
	 * Method to print out a single PromoSetMeal by name
	 * 
	 * @param promoName The name of the PromoSetMeal to print
	 */
	public void viewIndividualPromo(String promoName) {

		if (!checkPromoExists(promoName)) {
			System.out.println(promoName + " not found.");
			System.out.println();
			return;
		}

		PromoSetMeal foundPromo = returnPromo(promoName);

		System.out.println("--------------------");
		System.out.printf(foundPromo.getName() + " | $%.2f\n", foundPromo.getPrice());

		for (int j = 0; j < foundPromo.getMealItems().size(); j++) {
			System.out.println((j + 1) + ". " + foundPromo.getMealItems().get(j).getName());
		}
		System.out.println("--------------------");

		System.out.println();

	}

	/**
	 * Method to return the PromoSetMeal object by supplying its name
	 * 
	 * @param promoName The name of the PromoSetMeal object to return
	 * @return PromoSetMeal object found from the supplied name
	 */
	public PromoSetMeal returnPromo(String promoName) {

		for (int i = 0; i < promoList.size(); i++) {
			if (promoList.get(i).getName().equals(promoName)) {
				return promoList.get(i); // exists
			}
		}

		return null; // does not exist

	}

	/**
	 * Method to return the PromoSetMeal object by supplying its index in the list
	 * of all PromoSetMeals
	 * 
	 * @param index The index of the PromoSetMeal object to return
	 * @return PromoSetMeal object found from the supplied index
	 */
	public PromoSetMeal returnPromo(int index) {
		if (index > promoList.size() || index <= 0) {
			return null;
		}
		return promoList.get(index - 1);
	}

	/**
	 * Method to check if a PromoSetMeal exists by supplying its name
	 * 
	 * @param promoName The name of the PromoSetMeal to look for
	 * @return The boolean value of whether the PromoSetMeal exists
	 */
	public boolean checkPromoExists(String promoName) {

		for (int i = 0; i < promoList.size(); i++) {
			if (promoList.get(i).getName().equals(promoName)) {
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
				return "promo.csv";
			}

			@Override
			public String getColumnsName() {
				return "Promotion Set Name,Promotion Price,Promotion Items";
			}

			@Override
			public String insertRow(int index) {
				try {
					return promoList.get(index).toCSVFormat();
				} catch (Exception e) {
					return null;
				}
			}

			@Override
			public void extractRow(String[] row) {

				String[] tempArray = row[2].split("\\|"); // Split by "."
				List<String> tempList = new ArrayList<String>(Arrays.asList(tempArray));
				ArrayList<String> promoItemList = new ArrayList<String>(tempList);

				createPromoSetMeal(row[0], Float.parseFloat(row[1]), promoItemList);

			}
		});

	}
}
