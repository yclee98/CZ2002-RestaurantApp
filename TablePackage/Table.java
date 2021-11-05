package TablePackage;
import FlatFile.CSVFormat;

public class Table implements CSVFormat {
	private int tableNum;
	private int capacity;
	private boolean assigned;
	
	public Table(int tableNum, int capacity) {
		//check if the table size is valid
			this.capacity = capacity;
			this.tableNum = tableNum;
	}

	@Override
    public String toCSVFormat() {
        return tableNum + "," +
            capacity + "," +
            assigned;
    }

	public int getTableNum() {
		return this.tableNum;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public boolean getAssigned() {
		return this.assigned;
	}
	
	public void setAssign(boolean assign) {
		//true when there is a customer assigned to a table
		this.assigned = assign;
	}
}
