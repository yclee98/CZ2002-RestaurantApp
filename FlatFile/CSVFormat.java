package FlatFile;
/**
 * This interface is used to conveniently return the entity information to a csv format 
 */
public interface CSVFormat{
    /**
     * @return all the class variabe in this format
     * e.g. staffID + "," + staffName + "," + gender + "," + jobTitle;
     */
    public String toCSVFormat();
}