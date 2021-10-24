package RestaurantProject;

import java.util.ArrayList;

import RestaurantProject.FlatFile.FlatFileAdapter;

public class CateManager extends Manager{
	
	public ArrayList<MenuCate> cateList = new ArrayList<MenuCate>();
	
	public CateManager() {
		
	}
	
	public void createMenuCate(long catID, String catName) {
	    	
	    	MenuCate temp = new MenuCate(catID, catName);
	    	
	    	cateList.add(temp); //add this category to the list
	    	
	}
	 
	public ArrayList<MenuCate> getCateList() {
			return cateList;
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

    public void viewAllMenuCate() {
    	//Printing names based on creation time
    	for(int i=0; i < cateList.size(); i++) {
    		System.out.println("--------------------");
			System.out.println(cateList.get(i).getCatName() + " | ID: " + cateList.get(i).getCatID());
			System.out.println("--------------------");
		}
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
