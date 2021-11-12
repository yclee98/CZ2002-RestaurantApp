package ReservationPackage;
import Utility.*;
import java.util.ArrayList;
/**
 *  This class implement the functionailty for automatically removing the reservation if it has past the current time.
 *  It check the array of reservation at a certain interval, if there is any in the array which past the current time 
    it will remove it from the reservation. 
 */
public class ReservationThread extends Thread{
    /**
     * List of all the Reservation in the system
     */
    private ArrayList<Reservation> allReservation;
    /**
     * boolean variable on if the program is running true for running
     */
    private boolean isRunning;
    

    /**
     * this arraylist is same as the one in manager
     * anything updated in the manager array will auto update this
     * @param allReservation List of all the Reservation in the system
     */
    public ReservationThread(ArrayList<Reservation> allReservation){
        this.allReservation = allReservation; 
        this.isRunning = true;
    }

    /**
     * This method will be running in the background indefinetly as long as isRunning is true.
     * It will call removeExpiredReservation to remove any reservation that past the current time.
     * Sleep for a certain interval before waking up again
     * When sleeping if a interrupt occur, it will break the loop
     */
    @Override
    public void run() {
        while(this.isRunning){
            removeExpiredReservation();
            try {
                sleep(5000); //in milisecond, this is 5second
                //sleep for a certain period of time
                //can make it sleep longer before waking up to check removeExpiredReservation
            } catch (InterruptedException e) {
                System.out.println("Ending background thread");
                break;
            }
        }   
    }
    /**
     * This method check through the list of reservation and remove those that past the current time 
     */
    public void removeExpiredReservation(){
        long dateTimeNow = DateTime.getEpochNow();

        //remove this 2 print after u test, just for testing it work 
        // System.out.println("\nWaking up current time ="+DateTime.epochToDate(dateTimeNow, true));
        // System.out.println("Size of array = "+allReservation.size());

        for(int i=0; i<allReservation.size(); i++){

            //change this method allReservation.get(i).getEndDateTime() if needed to the other naming u use to get the end date time
            if(dateTimeNow > allReservation.get(i).getEndDateTime()){

                //can give more information like removing reservation for John, currently i displaying the end date time of reservation
                System.out.println("Removing reservation for "+ " "+ allReservation.get(i).getName() + " " +allReservation.get(i).getContact());
                allReservation.remove(i);
            }
        }
    }
    /**
     * This method will be used to kill the thread
     * set isRunning to false 
     * create an interrupt to interrupt the sleep
     */
    public void killThread(){
        this.isRunning = false;
        this.interrupt();
    }
}