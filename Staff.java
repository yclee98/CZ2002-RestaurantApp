public class Staff {
    private long staffID;
    private String staffName;
    private char gender;
    private String jobTitle;

    public Staff(long sID, String Sname, char gen, String job){
        staffID = sID;
        staffName = Sname;
        gender = gen;
        jobTitle = job;
    }

    public long getStaffID() {
        return staffID;
    }

    public void setStaffID(long staffID) {
        this.staffID = staffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
