public class Customer
{

	private long customerID;
	private String customerName;
	private boolean isMember;
	
	public long getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public boolean isMember() {
		return isMember;
	}
	
	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
}	
