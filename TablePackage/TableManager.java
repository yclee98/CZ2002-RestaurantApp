package TablePackage;
import java.util.ArrayList;
import java.util.Comparator;
import FlatFile.*;
import Utility.Manager;

public class TableManager extends Manager {
	private ArrayList<Table> tableList = new ArrayList<Table>();

	public TableManager() {
		retrieveData();
	}

	//Method to add tables into the ArrayList
	public void addTable(int tableNum, int capacity) {
		//add funtion to check if table is already in existance q
		Table newTable = new Table(tableNum, capacity);
		newTable.setAssign(false);
		//adding to the table manager list
		tableList.add(newTable);
	}

	public void addTableCSV(int tableNum, int capacity, boolean assigned) {
		Table newTable = new Table(tableNum, capacity);
		newTable.setAssign(assigned);
		tableList.add(newTable);
	}

	//function to check if the table exist
	public int ifExist(int tableNum){
		for(int i=0; i<tableList.size(); i++) {
			if(tableList.get(i).getTableNum() == tableNum) {
				return 1;
			}
		}
		return 0;
	}

	//Method to remove tables into the ArrayList
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

	//Check if tableNum is available
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

	//Return a list of available tables
	public void showAvailableTables() {
		System.out.println("The available tables are as follows:");
		for(int i=0; i<tableList.size(); i++) {
			if(!tableList.get(i).getAssigned()) {
				System.out.printf("Table Number %d, capacity of %d\n", tableList.get(i).getTableNum(), tableList.get(i).getCapacity());
			}
		}
	}

	//function to sort by table num
	public void sortByNum(){
		tableList.sort(Comparator.nullsLast(Comparator.comparingInt(Table::getTableNum)));
	}

	//Method to Set Assign a table based off table number
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

	//Return an Empty table that fits the capactiy size
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

	public int findAvailableTable(){
		for(int i = 0; i < tableList.size(); i++){
			if(!tableList.get(i).getAssigned()){
				return tableList.get(i).getTableNum();
			}
		}
		return -1;
	}



	//save function
	public void toSave(){
		saveData();
	}

	//load data


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
