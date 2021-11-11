package ReservationPackage;

import java.util.ArrayList;
import java.time.LocalDateTime;
import Utility.*;
import TablePackage.*;
import FlatFile.*;
import TableResPackage.*;

public class ReservationManager extends Manager {
	private long dateTimeEpoch;
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

	public ReservationManager(){
		createThread();
		retrieveData();
	}

	//Method to add the reservation into the ArrayList
	public void addReservation(String name, int contact, int numPax, long dateTimeEpoch, int tableNum){
		Reservation newRes  = new Reservation(name, contact, numPax, dateTimeEpoch, tableNum);
		//adding to the reservationList
		reservationList.add(newRes);
	}

	public void addReservationCSV(String name, int contact, int numPax, long dateTimeEpoch, int tableNum){
		Reservation newRes  = new Reservation(name, contact, numPax, dateTimeEpoch, tableNum);
		//adding to the reservationList
		reservationList.add(newRes);
	}

	//Method to get the user input time 
	public void getEpochDateTime(int day, int month, int year, int hourOfDay, int minute) {
		this.dateTimeEpoch = DateTime.dateTimeToEpoch(day, month, year, hourOfDay, minute);
	}

	//compare current time to Reservation Time
	//Reservation must be done at least 4 hrs in advance
	public boolean isReservationInAdvance(long dateTimeEpoch) {
		// if((dateTimeEpoch) > (DateTime.getEpochNow() + 14400)) {
		   if((dateTimeEpoch) > (DateTime.getEpochNow() + 10000L)) {
			return true;
		}
		
		else 
			return false;
	}
	
	//remove reservation
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
	
	//check reservation details
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

	//Check Reservation Exist 
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


	//get reservationList
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
		System.out.println("Create Thread Test");
    }
    
    /**
     * This method is used to kill the thread.
     */
    public void endThread(){
        //must use this function at applicaiton exit to kill the thread. 
        //call this function inside restuarant ui when user select exit option
        reservationThread.killThread();
		System.out.println("Thread End Test");

    }


	


	//saving to CSV

	
	
	
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
