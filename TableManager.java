import java.util.ArrayList;

public class TableManager {
	ArrayList<Table> tableList = new ArrayList<Table>();
	
	public TableManager() {
	}
	
	//Method to add tables into the ArrayList
	public void addTable(int tableNum, int capacity) {
		Table newTable = new Table(tableNum, capacity);
		newTable.setAssign(false);
		tableList.add(newTable); 
	}
	
	//Method to remove tables into the ArrayList
	public void removeTable(int tableNum) {
		for(int i=0; i<tableList.size(); i++) {
			if(tableList.get(i).getTableNum() == tableNum) {
				tableList.remove(i);
				System.out.printf("Table %d has been successfully removed.", tableNum);
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
					System.out.printf("Table %d is available.", tableNum);
					return;
				}
				
				else {
					System.out.printf("Table %d is not available.", tableNum);
					return;
				}
			}
		}
		System.out.printf("Table %d not found.", tableNum);
	}
	
	//Return a list of available tables
	public void showAvailableTables() {
        System.out.println("The available tables are as follows:\n");
        for(int i=0; i<tableList.size(); i++) {
        	if(tableList.get(i).getAssigned() == false) {
        		System.out.printf("SeatID %d\n", tableList.get(i).getTableNum());
        	}
        }
	}
	
	
	
}
