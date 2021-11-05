package MenuCatePackage;

import RestaurantProject.FlatFile.CSVFormat;

public class MenuCate implements CSVFormat{

	private String catName;
	private long catID;
	
	public MenuCate(long catID, String catName) {
		this.catName = catName;
		this.catID = catID;
		
	}
	
	public String getCatName() {
		return this.catName;
	}
	
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	public long getCatID() {
		return this.catID;
	}
	
	public void setCatID(long catID) {
		this.catID = catID;
	}
	
	@Override
    public String toCSVFormat() {
        return catID + "," +
        		catName; 
    }

}
