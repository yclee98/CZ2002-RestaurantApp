import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
/**
 * This class implement 3 static function of date time and epoch time
 * Epoch is the number of miliseconds that have elapsed since January 1, 1970 and 
 * it is stored as a long variable
 */
public class DateTime {
    /**
     * Get the current time in epoch
     * @return
     */
    public static long getEpochNow(){
        return new Date().getTime();
    }

    /**
     * Take in day month year to convert it to epoch 
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static long dateToEpoch(int day, int month, int year){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month-1, day);
        Date test = cal.getTime();
        return test.getTime(); 
    }

    /**
     * Convert a long epoch time to a human readable string date time
     * @param l, the epoch time in long
     * @param time, a boolean to indicate if time is to be included 
     * @return
     */
    public static String epochToDate(Long l, boolean time){
        SimpleDateFormat formatter;
        if(time == true){
            formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        }
        else{
            formatter = new SimpleDateFormat("dd-MM-yyyy");
        }
        
        Date dateTime = new Date(l);
        return formatter.format(dateTime);
    }
}
