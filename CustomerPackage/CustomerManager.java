package CustomerPackage;
import java.util.ArrayList;
import java.util.Scanner;
import FlatFile.FlatFileAdapter;
import Utility.Manager;

/**
 * 
 * This is a controller class which manages all the entities of customer 
 * This is done with the help of an array list
 */
public class CustomerManager extends Manager{
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private Scanner userInput = new Scanner(System.in);
	
	public CustomerManager(){
		this.retrieveData();

	}
	/**
	 * Override the abstract method in order to create FlatfileAdapter to save/retrieve data from csv file
	 */
	@Override
	public void createFlatFileAdapter() 
	{
        super.addFlatFileAdapter(new FlatFileAdapter() {
            public String getFileName() {
                return "customer.csv";
            }

            public String getColumnsName() {
                return "Customer ID,Customer Name,Membership";
            }

            public String insertRow(int index) {
                try{
                    return customerList.get(index).toCSVFormat();
                }catch (Exception e){
                    return null;
                }
            }
            
            @Override
            public void extractRow(String[] row) {
                customerList.add(new Customer(Long.parseLong(row[0]), row[1], Boolean.parseBoolean(row[2])));
            }
            
        });
    }
	
	/**
	 * Prompt user to input customer details to create a new customer
	 */
	public void createCustomer(){
		String custName;
		long custId;
		boolean mem;
        try{
            custId = customerList.get(customerList.size()-1).getCustomerID() + 1;
            System.out.println(custId);
        }catch(Exception e) {custId=1;}

        System.out.println("Enter Customer Name");
       // custName = userInput.nextLine();
	   
	   custName = userInput.next()+userInput.nextLine();

        System.out.println("Enter Customer Membership Status (true/false)");
        mem = userInput.nextBoolean(); 

        customerList.add(new Customer(custId, custName, mem));
	}
	
	/**
	 * Finding Customer with the ID given by user
	 * @param custId the customer ID that is to be found
	 * @return the index at which the customer with that particular ID is located
	 */
	public int findCustomer(long custId){
		for(int i=0; i<customerList.size(); i++){
            if(customerList.get(i).getCustomerID() == custId){
                return i; 
            }  
        }
        System.out.println("Customer not found");
        return -1; 
	}
	
	/**
	 * removing the customer with the particular ID provided by user
	 */
	public void removeCustomer(){
		long custId;
		int index;
		System.out.println("Enter the ID of the Customer to be removed");
		custId = userInput.nextLong();
		if((index=findCustomer(custId))!=-1){
            System.out.println("Removed customer "+ customerList.get(index).getCustomerName());
            customerList.remove(index);
        }
		
	}
	
	/**
	 * Finding the customer with given input ID and returning it
	 * @param custId the customer ID 
	 * @return with that particular ID is located
	 */
	public Customer returnCustomer(long custId){
		for(int i = 0; i < customerList.size(); i++){
			if(customerList.get(i).getCustomerID() == custId){
				return customerList.get(i);
			}
		}
		return null; 
	} 
	
	/**
	 * Printing information of a particular customer
	 */
	public void displayCustomerInfo(){
		int index;
		System.out.println("Enter the Customer ID");
		long custId = userInput.nextLong();
		if((index=findCustomer(custId))!=-1){
            System.out.println("Name: " + customerList.get(index).getCustomerName());
            System.out.println("Membership Status: " + customerList.get(index).isMember()); //is this right?
            return;
        }
	}
	
	/**
	 * Print all customers
	 */
	public void printCustomers(){
		System.out.println("*********** Customers ***********");
		System.out.printf("%-20s\t\t%-20s\t\t%-20s\n","Customer", "ID", "Membership Status");
		for(int i = 0; i < customerList.size(); i++){
			System.out.printf("%-20s\t\t%-20s\t\t%-20s\n", customerList.get(i).getCustomerName(), 
			Long.toString(customerList.get(i).getCustomerID()), customerList.get(i).isMember());
		}
	}
}

