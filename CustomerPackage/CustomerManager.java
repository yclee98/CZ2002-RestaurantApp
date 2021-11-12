package CustomerPackage;
import java.util.ArrayList;
import java.util.Scanner;
import FlatFile.FlatFileAdapter;
import Utility.Manager;

/**
 * This is a controller class which manages all the entities of customer 
 * This is done with the help of an array list.
 */
public class CustomerManager extends Manager{
	/**
	 * holds customer objects for all customers
	 */
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	/**
	 * Scanner to accept user input
	 */
	private Scanner userInput = new Scanner(System.in);

	/**
	 * Constructor for the CustomerManager, retrieves Data from Customer.csv to populate customerList
	 */
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
                return "Customer ID,Customer Name,Contact,Membership";
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
                customerList.add(new Customer(Long.parseLong(row[0]), row[1], Integer.parseInt(row[2]), Boolean.parseBoolean(row[3])));
            }
            
        });
    }
	
	/**
	 * Prompt user to input customer details to create a new customer
	 */
	public void createCustomer(){
		String custName;
		int custContact;
		long custId;
		boolean mem;
        try{
            custId = customerList.get(customerList.size()-1).getCustomerID() + 1;
            System.out.println(custId);
        }catch(Exception e) {custId=1;}

        System.out.println("Enter Customer Name");
	    custName = userInput.next()+userInput.nextLine();

        System.out.println("Enter Customer Membership Status (true/false)");
        mem = userInput.nextBoolean();

		System.out.println("Enter Customer Contact Number: ");
		custContact = userInput.nextInt();

        customerList.add(new Customer(custId, custName, custContact, mem));
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
		System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\n","Customer", "ID", "Contact", "Membership Status");
		for(int i = 0; i < customerList.size(); i++){
			System.out.printf("%-20s\t%-20s\t%-20s\t%-20s\n", customerList.get(i).getCustomerName(),
			Long.toString(customerList.get(i).getCustomerID()), Integer.toString(customerList.get(i).getCustomerContact()),
					customerList.get(i).isMember());
		}
	}

	/**
	 * Method to allow customer to register for membership
	 * @param register indicate if registering (true) or de-registering (false)
	 */
	public void registerMembership(Boolean register){
		printCustomers();
		System.out.println("Please enter the customer ID to register/De-register membership");
		long memID = userInput.nextLong();
		Customer newMem = returnCustomer(memID);
		if(newMem == null){
			System.out.println("ERROR! Customer does not exist");
		}
		else{
			if(register) {
				newMem.setMember(true);
				System.out.println("SUCCESS! Customer membership registered");
			}
			else {
				if(!newMem.isMember()){
					System.out.println("ERROR! Customer is not a member");
				}
				else{
					newMem.setMember(false);
					System.out.println("SUCCESS! Customer membership De-registered");
				}
			}
		}
	}
}

