public class Table {
	private int tableNum;
	private long customerID;
	private int capacity;
	private boolean assigned;
	
	public Table(int tableNum, int capacity) {
		//check if the table size is valid
		if(capacity%2 == 1 ) {
			System.out.println("Number of seats should be even");
		}
		
		else if(capacity<2 || capacity>10) {
			System.out.println("Number of seats should be between 2 to 10");
		}
		else {
			this.capacity = capacity;
			this.tableNum = tableNum;
		}
		
	}
	
	
	public int getTableNum() {
		return this.tableNum;
	}
	
	public long getCustomerID() {
		return this.customerID;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public boolean getAssigned() {
		return this.assigned;
	}
	
	public void setAssign(boolean assign) {
		this.assigned = assign;
	}
	
	public void Assign(int customerID) {
		this.customerID = customerID;
		this.assigned = true;
	}
	
	public void unAssign(int customerID) {
		this.customerID = customerID;
		this.assigned = false;
	}

}
