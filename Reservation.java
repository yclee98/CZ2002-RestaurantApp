//import java.time.*;

public class Reservation {
	private long reservationID;
	private String name;
	private int contact;
	private int numPax;
	//private LocalDate date;
	//private LocalTime time;
	private int tableNum;
	
	
	public Reservation(long reservationID, String name, int contact, int numPax, String date, int time, int tableNum) {
		//LocalDate date = LocalDate.parse( "2017-04-14" );
		 this.reservationID = reservationID;
		 this.name = name;
		 this.contact = contact;
		 this.numPax=numPax;
		 this.tableNum = tableNum;
	}
	
	public long getReservationID() {
		return this.reservationID;
	}
	
	public int getTableNum() {
		return this.tableNum;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getContact() {
		return this.contact;
	}
	
	public int getNumPax() {
		return this.numPax;
	}
	

	
	
	

}
