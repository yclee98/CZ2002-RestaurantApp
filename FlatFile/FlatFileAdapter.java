package FlatFile;
/**
 * This interface is an adapter which is use by the FlatFileHandler
 * It allow the different classes that require saving and retrieving to/from flatfile to change the body according to their needs
 */
public interface FlatFileAdapter {
    /**
     * @return filename in .csv where data should be stored
     * e.g. return "test.csv";
     */
    public String getFileName();

    /**
     * Get the columns/variables names which will be store in the first row of the csv file 
     * @return a string of columns/variables names that will be displayed as the columns for the csv file
     * e.g. return "col1, col2, col3";
     */
    public String getColumnsName(); 

    /**
     * To insert a row into the csv file
     * When storing infomation of an entity, it will be easier to implement CSVFormat on the entity and call toCSVFormat to 
     * get the entity information in csv format and store as a row
     * e.g. return allstaff.get(index).toCSVFormat();
     * @param index is use to indicate the index position of the csv file, starting from index 0
     * @return a row in string to be inserted or return null if it is end of the insert
     */
    public String insertRow(int index);
        
    /**
     * To specify, after extracting a row of the csv what are the action to perform 
     * e.g. storing the extracted row into an array of entity
     * e.g. allStaff.add(new Staff(Long.parseLong(row[0]), row[1], row[2].charAt(0), row[3]));
     * @param row is a row of the csv file in a string array
     */
    public void extractRow(String[] row);
        
}
