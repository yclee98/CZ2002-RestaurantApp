package PromoPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Utility.Manager;
import RestaurantProject.FlatFile.FlatFileAdapter;

public class PromoManager extends Manager{
	
	private ArrayList<PromoSetMeal> promoList = new ArrayList<PromoSetMeal>();

	public PromoManager() {	
	}
	
	public ArrayList<PromoSetMeal> getPromoList(){
		return promoList;
	}
	
	public void setPromoList(ArrayList<PromoSetMeal> promoItemList) {
		//effectively replaces the old ArrayList with the newly input one
		promoList.clear(); //clear all items
		promoList.addAll(promoItemList); //add all items from the given list
	}
	
	public void createPromoSetMeal(String name, float price) {
		
		PromoSetMeal temp = new PromoSetMeal(name, price);
    	
		promoList.add(temp); //add this promotion set to the list
	}
	
	public void createPromoSetMeal(String name, float price, ArrayList<String> promoItemList) {
		
		PromoSetMeal temp = new PromoSetMeal(name, price);
    	
		promoList.add(temp); //add this promotion set to the list
		
		for(int i=0; i < promoItemList.size(); i++) {
			temp.getItemNameList().add(promoItemList.get(i));
		}
	}
	
	public void removePromo(String promoName){
    	boolean found = false;
    	PromoSetMeal foundPromo = null;
    	
    	for(int i=0; i < promoList.size(); i++) {
			if(promoList.get(i).getName().equals(promoName)) {
				found = true; //found promo name
				foundPromo = promoList.get(i);
				promoList.remove(i); //remove from promoList
				break; //exit for loop
			}
		}
    	
    	if(found) System.out.println(promoName + " removed.");
    	else System.out.println(promoName + " not found.");
    	
    	foundPromo = null; //removing the pointer, the promo will be deleted by garbage collector
    	
    	System.out.println();
    }
	
	public void viewAllPromo() {
    	//Printing names based on creation time
    	for(int i=0; i < promoList.size(); i++) {
    		System.out.println("--------------------");
			System.out.printf(promoList.get(i).getName() + " | $%.2f\n", promoList.get(i).getPrice());
			
			for(int j=0; j < promoList.get(i).getMealItems().size(); j++) {
				System.out.println((j+1) + ". " + promoList.get(i).getMealItems().get(j).getName());
			}
			System.out.println("--------------------");
		}
    	
    	System.out.println();
    }
	
	public void viewIndividualPromo(String promoName){
    	
    	if(!checkPromoExists(promoName)) {
    		System.out.println(promoName + " not found.");
    		System.out.println();
    		return;
    	}
    	
    	PromoSetMeal foundPromo = returnPromo(promoName);
    	
    	System.out.println("--------------------");
		System.out.printf(foundPromo.getName() + " | $%.2f\n", foundPromo.getPrice());
		
		for(int j=0; j < foundPromo.getMealItems().size(); j++) {
			System.out.println((j+1) + ". " + foundPromo.getMealItems().get(j).getName());
		}
		System.out.println("--------------------");
		
		System.out.println();
    	
    }
	
	public PromoSetMeal returnPromo(String promoName) {
    	
    	for(int i=0; i < promoList.size(); i++) {
    		if(promoList.get(i).getName().equals(promoName)) {
    			return promoList.get(i); //exists
    		}
    	}

    	return null; //does not exist
    	
    }
	
	public boolean checkPromoExists(String promoName) {
    	
    	for(int i=0; i < promoList.size(); i++) {
    		if(promoList.get(i).getName().equals(promoName)) {
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
                return "promo.csv";
            }

            @Override
            public String getColumnsName() {
                return "Promotion Set Name,Promotion Price,Promotion Items";
            }

            @Override
            public String insertRow(int index) {
                try{
                    return promoList.get(index).toCSVFormat();
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            public void extractRow(String[] row) {
            	
                String[] tempArray = row[2].split("\\|"); //Split by "."
                List<String> tempList = new ArrayList<String>(Arrays.asList(tempArray));
                ArrayList<String> promoItemList = new ArrayList<String>(tempList);
                
                createPromoSetMeal(row[0], Float.parseFloat(row[1]), promoItemList);

            }
        });  

    }
}
