package oopProject;

public class MenuCate {

	private String catName;
	private long catID;
	
	public MenuCate(String catName, long catID) {
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
}
