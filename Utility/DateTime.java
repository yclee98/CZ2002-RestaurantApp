package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
/**
 * This class implement 3 static function of date time and epoch time
 * Epoch is the number of miliseconds that have elapsed since January 1, 1970 and 
 * it is stored as a long variable
 * In epoch:
 * 1 day = 86400000 miliseconds
 * 1 month = 2678400000 miliseconds
 */
public class DateTime {
    /**
     * This method get the current time in epoch
     * @return long epoch time
     */
    public static long getEpochNow(){
        return new Date().getTime();
    }

    /**
     * This method take in day month year to convert it to epoch 
     * @param day
     * @param month
     * @param year
     * @return long epoch time
     */
    public static long dateToEpoch(int day, int month, int year){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month-1, day);
        Date toEpoch = cal.getTime();
        return toEpoch.getTime(); 
    }
    /**
     * This method take in day month year hour and minute to convert into epoch
     * @param day
     * @param month
     * @param year
     * @param hourOfDay
     * @param minute
     * @return long epoch time
     */
    public static long dateTimeToEpoch(int day, int month, int year, int hourOfDay, int minute){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month-1, day, hourOfDay, minute);
        Date toEpoch = cal.getTime();
        return toEpoch.getTime(); 
    }

    /**
     * This method convert a long epoch time to a human readable string date time
     * @param epochTime, the epoch time in long
     * @param time, a boolean to indicate if time is to be included 
     * @return a string of human readable date time
     */
    public static String epochToDate(Long epochTime, boolean time){
        SimpleDateFormat formatter;
        if(time == true){
            formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        }
        else{
            formatter = new SimpleDateFormat("dd-MM-yyyy");
        }
        
        Date dateTime = new Date(epochTime);
        return formatter.format(dateTime);
    }    
}
