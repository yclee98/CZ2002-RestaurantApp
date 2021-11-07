package StaffPackage;

import FlatFile.CSVFormat;
/**
 * This entity class represent the Staff member 
 */
public class Staff implements CSVFormat{
    /**
     * A unique staff ID of this staff
     */
    private long staffID;
    /**
     * The name of this staff
     */
    private String staffName;
    /**
     * The gender of this staff
     */
    private char gender;
    /**
     * The job title of this staff
     */
    private String jobTitle;

    /**
     * Constructor of the staff class to create a new staff 
     * @param sID a unique ID that represent each staff 
     * @param Sname the name of the staff
     * @param gender the gender of the staff 
     * @param job the job title of the staff
     */
    public Staff(long sID, String Sname, char gender, String job){
        this.staffID = sID;
        this.staffName = Sname;
        this.gender = gender;
        this.jobTitle = job;
    }
    /**
     * @return the staff ID
     */
    public long getStaffID() {
        return staffID;
    }
    /**
     * Changes the ID of this staff
     * @param staffID
     */
    public void setStaffID(long staffID) {
        this.staffID = staffID;
    }
    /**
     * @return the staff name
     */
    public String getStaffName() {
        return staffName;
    }
    /**
     * Changes the name of this staff
     * @param staffName
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    /**
     * @return the gender of the staff
     */
    public char getGender() {
        return gender;
    }
    /**
     * Changes the gender of this staff
     * @param gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }
    /**
     * @return the job title of the staff
     */
    public String getJobTitle() {
        return jobTitle;
    }
    /**
     * Changes the job title of this staff
     * @param jobTitle
     */
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    /**
     * @return all the variables in this class as an csv format in string 
     */
    @Override
    public String toCSVFormat() {
        return staffID + "," +
            staffName + "," +
            gender + "," +
            jobTitle; 
    }
}
