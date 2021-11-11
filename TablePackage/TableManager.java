package TablePackage;
import java.util.ArrayList;
import java.util.Comparator;
import FlatFile.*;
import Utility.Manager;

/**
 * A control class that is used to interact with and mange the table entities
 */
public class TableManager extends Manager {
	/**
	 * List of all the tables in the system
	 */
	private ArrayList<Table> tableList = new ArrayList<Table>();

	/**
     * Creates a new Table manager.
     * During creation, application will read all existing Table stored inside the Table.csv and
     * recreate the Table objects to store within the tableList.
     */
	public TableManager() {
		retrieveData();
	}
	
	//Method to add tables into the ArrayList
	/**
	 * Method that is used to create a table object
	 * @param tableNum Specifc Table Number that the table is assigned to
	 * @param capacity The capacity that the table object will have
	 */
	public void addTable(int tableNum, int capacity) {
		//add funtion to check if table is already in existance q
		Table newTable = new Table(tableNum, capacity);
		newTable.setAssign(false);
		//adding to the table manager list
		tableList.add(newTable);
	}


	/**
	 * Overloading addTable to create a method that is used to save into the CSV
	 * @param tableNum Specifc Table Number that the table is assigned to
	 * @param capacity The capacity that the table object will have
	 * @param assigned Indicates if the table is assigned
	 */
	public void addTableCSV(int tableNum, int capacity, boolean assigned) {
		Table newTable = new Table(tableNum, capacity);
		newTable.setAssign(assigned);
		tableList.add(newTable);
	}

	
	/**
	 * Method to check if the table exists in the system
	 * @param tableNum Specifc Table Number to look for
	 * @return 1 if found, 0 if not 
	 */
	public int ifExist(int tableNum){
		for(int i=0; i<tableList.size(); i++) {
			if(tableList.get(i).getTableNum() == tableNum) {
				return 1;
			}
		}
		return 0;
	}


	/**
	 * Method to remove tables from the arraylist
	 * @param tableNum Specifc Table Number to remove
	 */
	public void removeTable(int tableNum) {
		for(int i=0; i<tableList.size(); i++) {
			if(tableList.get(i).getTableNum() == tableNum) {
				tableList.remove(i);
				System.out.printf("Table %d has been successfully removed. \n", tableNum);
				return;
			}
		}
		System.out.printf("Table %d not found.", tableNum);
	}

	
	/**
	 * Check if tableNum is available
	 * @param tableNum Specifc Table Number to check availability
	 * the result will be printed as a message 
	 */
	public void isTableNumAvailable(int tableNum) {
		for(int i=0; i<tableList.size(); i++) {
			if(tableList.get(i).getTableNum() == tableNum) {
				if(tableList.get(i).getAssigned() == false) {
					System.out.printf("Table %d is available. \n", tableNum);
					return;
				}
				
				else {
					System.out.printf("Table %d is not available. \n", tableNum);
					return;
				}
			}
		}
		System.out.printf("Table %d not found. \n", tableNum);
	}


	/**
	 * Prints the list of available tables
	 */
	public void showAvailableTables() {
        System.out.println("The available tables are as follows:\n");
        for(int i=0; i<tableList.size(); i++) {
        	if(tableList.get(i).getAssigned() == false) {
        		System.out.printf("Table Number %d, capacity of %d\n", tableList.get(i).getTableNum(), tableList.get(i).getCapacity());
        	}
        }
	}


	/**
	 * Method to sort by table num
	 */
	public void sortByNum(){
		tableList.sort(Comparator.nullsLast(Comparator.comparingInt(Table::getTableNum)));
	}


	/**
	 * Method to Set Assign a table based off table number
	 * @param tableNum Specifc table number to set assigment 
	 * @param x assign true if taken, false if not taken
	 */
	public void setAssign(int tableNum, boolean x){
		for(int i=0; i<tableList.size(); i++){
			if(tableList.get(i).getTableNum() == tableNum){
				tableList.get(i).setAssign(x);
				if(x == true){
					System.out.printf("Table Number %d, capacity of %d has been assigned\n", tableList.get(i).getTableNum(), tableList.get(i).getCapacity());
				}

				else
					System.out.printf("Table Number %d, capacity of %d has been unassigned\n", tableList.get(i).getTableNum(), tableList.get(i).getCapacity());
				return;
			}
		}
		System.out.printf("Table number does not exist");
	}

	
	/**
	 * Return an Empty table that fits the capactiy size
	 * @param capacity The capacity of the party size to accommodate for
	 * @param num An array list of already taken table numbers during the "timeslot" 
	 * @return Return the table number that the party will be assigned to, return -1 if no tables are found
	 */
	public int assignTable(int capacity, ArrayList<Integer> num){
		//Flag =  true when table is taken
		boolean flag;
		//sort based off capacity
		tableList.sort(Comparator.nullsLast(Comparator.comparingInt(Table::getCapacity)));		
		for(int i=0; i<tableList.size(); i++){
			//reset flag
			flag = false;
			//Iterating throught the entire list to find if there is an avil table
			if(tableList.get(i).getCapacity() >= capacity){
				//iterating through the list of taken tables
				for(int j=0; j<num.size(); j++){
					//The table already taken
					if(tableList.get(i).getTableNum() == num.get(j)) {
						flag = true;
						break;
					}
				}
				//If the table is not taken
				if(flag == false){
					return tableList.get(i).getTableNum();
				}
			}
		}
		return -1;
	}

	/**
	 * Method to find an available table
	 * @return the table numebr that is available, -1 if no tables are available
	 */
	public int findAvailableTable(){
		for(int i = 0; i < tableList.size(); i++){
			if(!tableList.get(i).getAssigned()){
				return tableList.get(i).getTableNum();
			}
		}
		return -1;
	}

	
	/**
	 * Used to save to csv
	 */
	@Override
	public void createFlatFileAdapter(){
		super.addFlatFileAdapter(new FlatFileAdapter(){
            @Override
            public String getFileName() {
                return "Table.csv";
            }

			//columns of the csv
			@Override
            public String getColumnsName() {
                return "tableNum,capacity,assigned";
            }

			@Override
            public String insertRow(int index) {
                try{
                    return tableList.get(index).toCSVFormat();
                }catch (Exception e){
                    return null;
                }
            }

			@Override
            public void extractRow(String[] row) {
            	addTableCSV(Integer.parseInt(row[0]), Integer.parseInt(row[1]), (row[2].equals("True")));
            }
        });  
	}
}
