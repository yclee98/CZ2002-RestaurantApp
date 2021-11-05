package ReservationPackage;
import FlatFile.CSVFormat;


public class Reservation implements CSVFormat{
	private String name;
	private int contact;
	private int numPax;
	private long dateTimeEpoch; 
	private int tableNum;
	private long endDateTime;
		
		
	public Reservation(String name, int contact, int numPax, long dateTimeEpoch, int tableNum) {
		//increase the resesrvationID 
		this.name = name;
		this.contact = contact;
		this.numPax = numPax;
		this.dateTimeEpoch = dateTimeEpoch;
		this.tableNum = tableNum;
		//Assume that the reservation will expire after 15 minutes
		//Making a test atm 15s
		this.endDateTime = (dateTimeEpoch + 15000L);
	}

	public long getEndDateTime(){
        return this.endDateTime;
    }

	@Override
    public String toCSVFormat() {
        return
		name + "," +
		contact + "," +
		numPax + "," +
		dateTimeEpoch + "," +
		tableNum;
    }
	
	//Return Functions
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
	
	public long getEpochDateTime() {
		return this.dateTimeEpoch;
	}

	
}
