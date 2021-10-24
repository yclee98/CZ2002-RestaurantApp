import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class DateTime {
    //epoch = the number of miliseconds that have elapsed since January 1, 1970
    public static long getEpochNow(){
        return new Date().getTime();
    }

    public static long dateToEpoch(int day, int month, int year){
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month-1, day);
        Date test = cal.getTime();
        return test.getTime(); 
    }

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
