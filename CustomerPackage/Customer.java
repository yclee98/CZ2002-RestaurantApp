package CustomerPackage;
import FlatFile.CSVFormat;
/**
 * This is the entity class that stores information of Customer
 */
public class Customer implements CSVFormat{
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
	 * Contact number of the customer.
	 */
	private int customerContact;

	/**
	 * Constructor of the Customer class 
	 * @param custId represents the ID of each customer
	 * @param custName represents name of the customer
	 * @param mem customer membership status
	 * @param contactNo  Contact number of the customer
	 */
	public Customer(long custId, String custName, int contactNo, boolean mem){
		customerID=custId;
		customerName=custName;
		isMember=mem;
		customerContact = contactNo;
	}
	/**
	 * @return the customer ID
	 */
	public long getCustomerID() {
		return customerID;
	}
	/**
	 * Change the ID of Customer
	 * @param customerID the id of the customer
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
	 * @param customerName name of the customer
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 *
	 * @return the customer contact number
	 */
	public int getCustomerContact() {
		return customerContact;
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
	 * @param isMember membership status of the customer
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
				customerContact + "," +
				isMember;
	}
}	

