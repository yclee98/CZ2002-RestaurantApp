package TablePackage;
import FlatFile.CSVFormat;

/**
 * This is an entity class for that records the attributes of the tables.
 */
public class Table implements CSVFormat {
	/**
	 * Specifc Table Number that each Table is assigned to
	 */
	private int tableNum;
	/**
	 * Capacity of each table
	 */
	private int capacity;
	/**
	 * To determin if the table is assigned to a customer, true if assigned
	 */
	private boolean assigned;


	/**
	 * Creates Table object from attributes from the table that is to be converted
	 * @param tableNum Specifc Table Number that the table is assigned to
	 * @param capacity The capacity that the table object will have
	 */
	public Table(int tableNum, int capacity) {
			this.capacity = capacity;
			this.tableNum = tableNum;
	}


	/**
	 * Declaring the columns for the CSV filr
	 */
	@Override
    public String toCSVFormat() {
        return tableNum + "," +
            capacity + "," +
            assigned;
    }

	/**
	 * @return the table number
	 */
	public int getTableNum() {
		return this.tableNum;
	}
	
	/**
	 * @return the table capactiy
	 */
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * @return a boolean value that denotes if the table is assigned, true mean assigned, false means unassigned
	 */
	public boolean getAssigned() {
		return this.assigned;
	}
	
	/**
	 * indicates if the table is assigned
	 * @param assign true when there is a customer assigned to a table
	 */
	public void setAssign(boolean assign) {
		this.assigned = assign;
	}
}
