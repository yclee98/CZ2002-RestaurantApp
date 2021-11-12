package ReservationPackage;
import FlatFile.CSVFormat;

/**
 * This is an entity class for that records the attributes of the Reservations.
 */
public class Reservation implements CSVFormat{
	/**
	 * Name of the customer
	 */
	private String name;
	/**
	 * Contact Number of the customer
	 */
	private int contact;
	/**
	 * Party size of the customer
	 */
	private int numPax;
	/**
	 * Date Time of reservation in Epoch format
	 */
	private long dateTimeEpoch; 
	/**
	 * Table number of the customer
	 */
	private int tableNum;
	/**
	 * Time when the reservation which will automatically be delted
	 */
	private long endDateTime;
		
	
	/**
	 * Creates Reservation object from attributes from the table that is to be converted
	 * @param name name of the customer
	 * @param contact contact number of the customer
	 * @param numPax party size of the customer
	 * @param dateTimeEpoch date and time of reservation
	 * @param tableNum table number that the customer is assigned to
	 */
	public Reservation(String name, int contact, int numPax, long dateTimeEpoch, int tableNum) {
		//increase the resesrvationID 
		this.name = name;
		this.contact = contact;
		this.numPax = numPax;
		this.dateTimeEpoch = dateTimeEpoch;
		this.tableNum = tableNum;
		//For demostration purpose the auto delete will be 15s var declared below is in ms
		//can set to a higher number for actual implementation
		this.endDateTime = (dateTimeEpoch + 300000L);
	}


	/**
	 * @return return the Time when the reservation which will automatically be delted 
	 */
	public long getEndDateTime(){
        return this.endDateTime;
    }

	/**
	 * Declaring the columns for the CSV file
	 */
	@Override
    public String toCSVFormat() {
        return
		name + "," +
		contact + "," +
		numPax + "," +
		dateTimeEpoch + "," +
		tableNum;
    }

	
	/**
	 * @return table number of the customer
	 */
	public int getTableNum() {
		return this.tableNum;
	}
	

	/**
	 * @return the name of the customer
	 */
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * @return the contact of the customer
	 */
	public int getContact() {
		return this.contact;
	}
	

	/**
	 * @return return the party size of the customer
	 */
	public int getNumPax() {
		return this.numPax;
	}
	
	
	/**
	 * @return date and time of reservartion in epoch format of the customer
	 */
	public long getEpochDateTime() {
		return this.dateTimeEpoch;
	}

	
}
