package ReservationPackage;

import java.util.ArrayList;
import java.time.LocalDateTime;
import Utility.*;
import TablePackage.*;
import FlatFile.*;
import TableResPackage.*;

/**
 * A control class that is used to interact with and mange the reservation entities
 */
public class ReservationManager extends Manager {
	/**
	 * Date Time of reservation in Epoch format
	 */
	private long dateTimeEpoch;

	
	/**
	 * List of all the Reservation in the system
	 */
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

	/**
	 * Creates a new reservationList manager.
     * During creation, application will read all existing Table stored inside the Reservation.csv and
     * recreate the Reservation objects to store within the reservationList.
	 * Creates thread for auto deletion of resrvation after x amt of time
	 */
	public ReservationManager(){
		createThread();
		retrieveData();
	}

	/**
	 *  Creates Reservation object from attributes from the table that is to be converted
	 * @param name name of the customer
	 * @param contact  contact number of the customer
	 * @param numPax party size of the customer
	 * @param dateTimeEpoch date and time of reservation
	 * @param tableNum table number that the customer is assigned to
	 */
	public void addReservation(String name, int contact, int numPax, long dateTimeEpoch, int tableNum){
		Reservation newRes  = new Reservation(name, contact, numPax, dateTimeEpoch, tableNum);
		//adding to the reservationList
		reservationList.add(newRes);
	}


	/**
	 * Method to get the user input time 
	 * @param day day of the reservation
	 * @param month month of the reservation
	 * @param year year of the reservation
	 * @param hourOfDay hourOfDay of the reservation
	 * @param minute minute of of the reservation
	 */
	public void getEpochDateTime(int day, int month, int year, int hourOfDay, int minute) {
		this.dateTimeEpoch = DateTime.dateTimeToEpoch(day, month, year, hourOfDay, minute);
	}

	/**
	 * compare current time to Reservation Time
	 * @param dateTimeEpoch
	 * @return
	 */
	public boolean isReservationInAdvance(long dateTimeEpoch) {
		//Set for test 
		if((dateTimeEpoch) > (DateTime.getEpochNow() )) {
			//Can add any time to set a constrain
			//if((dateTimeEpoch) > (DateTime.getEpochNow() + 10000L)) {
			return true;
		}
		
		else 
			return false;
	}
	
	
	/**
	 * Remove reservation based of contact number
	 * @param contact enter contact number of customer
	 */
	public void removeReservation(int contact) {
		//iterate through the list and then remove
		for(int i =0; i<reservationList.size(); i++){
			if(reservationList.get(i).getContact() == contact){
				reservationList.remove(i);
				System.out.printf("Reservation by contact number %d has been successfully removed. \n", contact);
				return;
			}
		}
		System.out.printf("Reservation by contact number %d not found. \n", contact);
	}
	
	
	/**
	 * Check reservation details based of contact number 
	 * @param contact enter contact number of customer
	 */
	public void checkReservation(int contact) {
		//check based off contact then return relevant data
		for(int i =0; i<reservationList.size(); i++){
			if(reservationList.get(i).getContact() == contact){
				System.out.println("A reservation has been made on " + Utility.DateTime.epochToDate((reservationList.get(i).getEpochDateTime()), true) +
									" by " + reservationList.get(i).getName() + " " + reservationList.get(i).getContact() + " for " + reservationList.get(i).getNumPax() + " on table number "
									+ reservationList.get(i).getTableNum());
				return;
			}
		}

		System.out.println("No reservation has been made by contact number " + contact);
		return;
	}

	
	/**
	 * Check Reservation Exist
	 * @param contact
	 * @return 1 if Reservation exist and 0 if it does not
	 */
	public int ifexist(int contact) {
		//check based off contact then return relevant data
		for(int i =0; i<reservationList.size(); i++){
			if(reservationList.get(i).getContact() == contact){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * used to check if the reservation for a contact number exists or not. If exist return Reservation
	 * obj, else return null
	 * @param contact contact of the customer to check reservation for
	 * @return customer's reservation obj
	 */
	public Reservation returnReservation(int contact) {
		//check based off contact then return relevant data
		for(int i =0; i<reservationList.size(); i++){
			if(reservationList.get(i).getContact() == contact){
				return reservationList.get(i);
			}
		}
		return null;
	}


	/**
	 * @return reservation list
	 */
	public ArrayList<Reservation> getReservationList(){
		return reservationList;
    }

   private ReservationThread reservationThread; //create a reservationthread variable
   /**
     * This method is used to create thread that will be running in the background 
     * to remove reservation that has past the current time.
     */
    public void createThread(){//run this method insidde ur constructor 
        reservationThread = new ReservationThread(this.reservationList); //pass in ur reservation array
        reservationThread.start();
		System.out.println("Removing expired reservation automatically in the background");
		System.out.println();
    }
    
    /**
     * This method is used to kill the thread.
     */
    public void endThread(){
        //must use this function at applicaiton exit to kill the thread. 
        //call this function inside restuarant ui when user select exit option
        reservationThread.killThread();
    }


	/**
	 * Save to CSV 
	 */
	@Override
	public void createFlatFileAdapter(){
		super.addFlatFileAdapter(new FlatFileAdapter(){
            @Override
            public String getFileName() {
                return "Reservation.csv";
            }

			//columns of the csv
			@Override
            public String getColumnsName() {
                return "name,contact,numPax,dateTimeEpoch,TableNum";
            }

			@Override
            public String insertRow(int index) {
                try{
                    return reservationList.get(index).toCSVFormat();
                }catch (Exception e){
                    return null;
                }
            }

			@Override
            public void extractRow(String[] row) {
            	//addTableCSV(Integer.parseInt(row[0]), Integer.parseInt(row[1]), (row[2].equals("True")));
				addReservation(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]), 
				Long.parseLong(row[3]), Integer.parseInt(row[4]));
            }
        });  
	}
}
