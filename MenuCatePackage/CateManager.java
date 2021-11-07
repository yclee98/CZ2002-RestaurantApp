package MenuCatePackage;

import java.util.ArrayList;
import java.util.Scanner;

import FlatFile.FlatFileAdapter;
import Utility.Manager;

public class CateManager extends Manager{
	
	private Scanner cateInput = new Scanner(System.in);
	
	private ArrayList<MenuCate> cateList = new ArrayList<MenuCate>();
	
	public CateManager() {
		
	}
	
	public ArrayList<MenuCate> getCateList() {
		return cateList;
	}
	
	public void createMenuCate(long catID, String catName) {
	    	
	    	MenuCate temp = new MenuCate(catID, catName);
	    	
	    	cateList.add(temp); //add this category to the list
	    	
	}
	
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
            cateInput.nextLine(); //clear \n buffer
            System.out.println();
            
            switch(option) {
            case 1: //Name
            	System.out.println("Enter new Name:");
            	String name = cateInput.nextLine();
            	
            	if(checkCateExists(name)) { //already has an category named this
            		System.out.println("Category called " + name + " already exists!");
            		System.out.println("Name not updated.");
                	System.out.println();
            	}
            	else {
            		foundCate.setCatName(name);
                	
                	System.out.println("Name updated to " + name + ".");
                	System.out.println();
            	}
            	break;
            case 2: //ID
            	System.out.println("Enter new Category ID:");
            	long cateId = cateInput.nextLong();
            	cateInput.nextLine(); //clear \n buffer
            	
            	if(checkCateExists(cateId)) { //already has a category with this ID
            		System.out.println("Category with ID " + cateId + " already exists!");
            		System.out.println("Category ID not updated.");
                	System.out.println();
            	}
            	else {
            		foundCate.setCatID(cateId);
                	
                	System.out.println("Category ID updated to " + cateId + ".");
                	System.out.println();
            	}
            	break;
            default:
            	break;
            }
    	} while(option > 0 && option < 3);
	}
	
	public void removeMenuCate(String catName) {
    	
		boolean found = false;
    	MenuCate foundCate = null;
    	
    	for(int i=0; i < cateList.size(); i++) {
			if(cateList.get(i).getCatName().equals(catName)){
				found = true; //found item name
				foundCate = cateList.get(i);
				cateList.remove(i); //remove from menuList
				break; //exit for loop
			}
		}
    	
    	if(found) System.out.println(catName + " removed.");
    	else System.out.println(catName + " not found.");
    	
    	foundCate = null; //removing the pointer, the item will be deleted by garbage collector
    	
	}
	
	public void removeMenuCate(long catID) {
    	
		boolean found = false;
    	MenuCate foundCate = null;
    	
    	for(int i=0; i < cateList.size(); i++) {
			if(cateList.get(i).getCatID() == catID){
				found = true; //found item name
				foundCate = cateList.get(i);
				cateList.remove(i); //remove from menuList
				break; //exit for loop
			}
		}
    	
    	if(found) System.out.println("Category ID " + catID + " removed.");
    	else System.out.println("Category ID " + catID + " not found.");
    	
    	foundCate = null; //removing the pointer, the item will be deleted by garbage collector
    	
    	System.out.println();
	}

	public void viewAllMenuCate() {
	    	//Printing names based on creation time
	    	for(int i=0; i < cateList.size(); i++) {
	    		System.out.println("--------------------");
				System.out.println(cateList.get(i).getCatName() + " | ID: " + cateList.get(i).getCatID());
				System.out.println("--------------------");
			}
	    	
	    	System.out.println();
	}
	
	public void viewIndividualMenuCate(String catName) {
    	
    	if(!checkCateExists(catName)) {
    		System.out.println(catName + " not found.");
    		System.out.println();
    		return;
    	}
    	
    	MenuCate foundCate = returnMenuCate(catName);
    	
    	System.out.println("--------------------");
		System.out.println(foundCate.getCatName() + " | ID: " + foundCate.getCatID());
		System.out.println("--------------------");
    }

	public MenuCate returnMenuCate(String catName) {
    	
    	for(int i=0; i < cateList.size(); i++) {
    		if(cateList.get(i).getCatName().equals(catName)) {
    			return cateList.get(i); //exists
    		}
    	}

    	return null; //does not exist
    	
    }
    
    public MenuCate returnMenuCate(long catID) {
    	
    	for(int i=0; i < cateList.size(); i++) {
    		if(cateList.get(i).getCatID() == catID) {
    			return cateList.get(i); //exists
    		}
    	}

    	return null; //does not exist
    	
    }
    
    public boolean checkCateExists(String catName) {
    	
    	for(int i=0; i < cateList.size(); i++) {
    		if(cateList.get(i).getCatName().equals(catName)) {
    			return true; //exists
    		}
    	}

    	return false; //does not exist
    	
    }
    
    public boolean checkCateExists(long catID) {
    	
    	for(int i=0; i < cateList.size(); i++) {
    		if(cateList.get(i).getCatID() == catID) {
    			return true; //exists
    		}
    	}

    	return false; //does not exist
    	
    }
    
    @Override
    public void createFlatFileAdapter() {
        super.addFlatFileAdapter(new FlatFileAdapter(){
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
                try{
                    return cateList.get(index).toCSVFormat();
                }catch (Exception e){
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
