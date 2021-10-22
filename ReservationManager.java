import java.util.ArrayList;
import java.time.LocalDateTime;

public class ReservationManager {
	ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	
	//Check if the reservation date is being made in advance
	//may not be working atm
	/*public boolean dateTimeCheck(int year, int month, int date, int hour, int minute) {
		//store in the format of year, month, date, hour, minute, second (set second to 00 on default)
        LocalDateTime reservationDateTime = LocalDateTime.of(year, month, date, hour, minute, 00);
        LocalDateTime date1 = LocalDateTime.now();
        if(reservationDateTime.isAfter(date1)) {
           return true;
        }
        
        else 
        	return false;
	}*/
	
	
	public ReservationManager() {
		
	}
	
	//Make a reservation
	public void createReservationList() {
		//call reservation and pass all the values needed
	}
	
	//remove reservation
	public void removeReservation() {
		//iterate through the list and then remove
		//same as table manager
	}
	
	//check reservation
	public void checkReservation() {
		//check based off reservationID then return relevant data
	}
	
}
