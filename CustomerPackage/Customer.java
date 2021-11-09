package CustomerPackage;
import FlatFile.CSVFormat;
/**
 * This is the entity class that stores information of Customer
 */
public class Customer implements CSVFormat;{
	/**
	 * Customer ID for the customer
	 */
	private long customerID;
	/**
	 * Name of the customer
	 */
	private String customerName;
	/**
	 * Membership status of the customer
	 */
	private boolean isMember;

	/**
	 * Constructor of the Customer class 
	 * @param custId represents the ID of each customer
	 * @param custName represents name of the customer
	 * @param mem customer membership status
	 */
	public Customer(long custId, String custName, boolean mem){
		customerID=custId;
		customerName=custName;
		isMember=mem;
	}
	/**
	 * 
	 * @return the customer ID
	 */
	public long getCustomerID() {
		return customerID;
	}
	/**
	 * Change the ID of Customer
	 * @param customerID
	 */
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	/**
	 * 
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * Change the name of customer
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 
	 * @return membership status
	 */
	public boolean isMember() {
		return isMember;
	}
	/**
	 * Change membership status
	 * @param isMember
	 */
	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
	/**
	 * 
	 * @return all the variables in the customer class as CSV format in string
	 */
	@Override
	public String toCSVFormat(){
		return customerID + "," +
				customerName + "," +
				isMember;
	}
}	

