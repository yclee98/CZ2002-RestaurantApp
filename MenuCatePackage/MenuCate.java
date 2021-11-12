package MenuCatePackage;

import FlatFile.CSVFormat;

/**
 * This is an entity class that holds information about each category that
 * MenuItems can be classified as
 */
public class MenuCate implements CSVFormat {

	/**
	 * Holds the name of the MenuCate
	 */
	private String catName;
	/**
	 * Holds the ID of the MenuCate
	 */
	private long catID;

	/**
	 * Constructor to create a MenuCate
	 * 
	 * @param catID   ID of the MenuCate
	 * @param catName Name of the MenuCate
	 */
	public MenuCate(long catID, String catName) {
		this.catName = catName;
		this.catID = catID;

	}

	/**
	 * Gets the name of the MenuCate
	 * 
	 * @return Name of this MenuCate
	 */
	public String getCatName() {
		return this.catName;
	}

	/**
	 * Sets the name of the MenuCate
	 * 
	 * @param catName The new name of this MenuCate
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/**
	 * Gets the ID of the MenuCate
	 * 
	 * @return ID of this MenuCate
	 */
	public long getCatID() {
		return this.catID;
	}

	/**
	 * Sets the ID of the MenuCate
	 * 
	 * @param catID The new ID of this MenuCate
	 */
	public void setCatID(long catID) {
		this.catID = catID;
	}

	/**
	 * Method to return the details of this MenuCate in a csv format
	 * 
	 * @return Details of this MenuCate in a csv format
	 */
	@Override
	public String toCSVFormat() {
		return catID + "," + catName;
	}

}
