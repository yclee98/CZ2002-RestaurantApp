public class Customer
{
	private long customerID;
	private String customerName;
	private boolean isMember;

	public Customer(long custId, String custName, boolean mem)
	{
		customerID=custId;
		customerName=custName;
		isMember=mem;
	}
	
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