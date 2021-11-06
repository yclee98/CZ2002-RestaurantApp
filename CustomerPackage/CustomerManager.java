package CustomerPackage;

import java.util.ArrayList;

public class CustomerManager
{
	private ArrayList<Customer> customerList; 
	
	public CustomerManager()
	{
		this.customerList= new ArrayList<Customer>();

	}
	
	public void createcustomer(long custId, String custName, boolean mem)
	{
		Customer newcustomer = new Customer(custId,custName, mem);
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

	public Customer findCustomer(long custId){
		for(int i = 0; i < customerList.size(); i++){
			if(customerList.get(i).getCustomerID() == custId){
				return customerList.get(i);
			}
		}
		return null;
	}

	public void printCustomers(){
		System.out.println("*********** Customers ***********");
		System.out.printf("%-20s\t\t%-20s\n","Customer", "ID");
		for(int i = 0; i < customerList.size(); i++){
			System.out.printf("%-20s\t\t%-20s\n", customerList.get(i).getCustomerName(), Long.toString(customerList.get(i).getCustomerID()));
		}
	}
}