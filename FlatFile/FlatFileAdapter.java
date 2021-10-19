package FlatFile;

public interface FlatFileAdapter {
    public String getFileName();
        //return the filename

    public String getColumnsName();
        //return the columns/variables name for the entity to store 
        //basically the first row in the table
        //e.g
        //return "Staff ID, Staff Name, Gender, Job Title";  

    public String insertRow(int index);
        //to insert a row entry for the csv which contain the information of a single entity of the array
        //can implement CSVFormat in the entity and override the method so that we can easily get the row information from that entity
        //should return null if out of range of the array else return string which contain information of the entity
        //can do a if else to check or do try catch which does not need to do any comparison
        //basically how u going to insert one entry to the csv 
        //e.g
        // try{
        //     return allstaff.get(index).toCSVFormat();
        // }catch(Exception e){
        //     return null;
        // }
        

    public void extractRow(String[] row);
        //extract a single row from the csv and then
        //create an object from the string array row and store inside your array
        //basically what u need to do after getting a single row from the csv
        //e.g.
        // allStaff.add(new Staff(Long.parseLong(row[0]), row[1], row[2].charAt(0), row[3]));
        
}
