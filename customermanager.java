import java.util.ArrayList;

public class customermanager 
{
	private ArrayList<Customer> customerList; 
	
	public customermanager() 
	{
		this.customerList= new ArrayList<Customer>(); 
	}
	
	public void createcustomer(long custId, String custName, boolean mem)
	{
		Customer newcustomer = new Customer(custId,custName, mem)
		this.customerList.add(newcustomer);
	}
	
	public boolean findcustomer(long custId)
	{
		for(Customer cust: this.customerList)
		{
			if(custId== cust.getCustomerID()) 
			{
				return true;
			}
		}
		return false;
	}
	
	public void removecustomer(long custId)
	{
		for(int i=0; i<this.customerList.size(); i++)
		{
			
			if(customerList.get(i).getCustomerID()==custId)
			{
				this.customerList.remove(i);
			}
		}
	}
}
