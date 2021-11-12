package StaffPackage;
import java.util.ArrayList;
import java.util.Scanner;


import FlatFile.FlatFileAdapter;
import Utility.Manager;

/**
 * This control class manages all the staff member entities
 * It store all the staff entities inside an array list
 */
public class StaffManager extends Manager{
    /**
     * holds staff object of all staff members
     */
    private ArrayList<Staff> staffList = new ArrayList<>();
    /**
     * Scanner to accept user input
     */
    private Scanner userInput = new Scanner(System.in);

    /**
     * StaffManager Constructor, retrieves staff information from the staff.csv to populate staffList
     */
    public StaffManager(){
        this.retrieveData();
    }
    /**
     * Override the parent abstract method to create FlatFileAdapter use for saving/retrieving of data from the csv file
     */
    @Override
    public void createFlatFileAdapter() {
        super.addFlatFileAdapter(new FlatFileAdapter() {
            @Override
            public String getFileName() {
                return "staff.csv";
            }

            @Override
            public String getColumnsName() {
                return "Staff ID,Staff Name,Gender,Job Title";
            }

            @Override
            public String insertRow(int index) {
                try{
                    return staffList.get(index).toCSVFormat();
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            public void extractRow(String[] row) {
                staffList.add(new Staff(Long.parseLong(row[0]), row[1], row[2].charAt(0), row[3]));
            }
            
        });
    }
    
    /**
     * Create new staff memeber
     * It does user input prompting for information before creating the staff 
     */
    public void createStaff(){
        String staffName, jobTitle;
        char gender;
        long id;
        try{
            id = staffList.get(staffList.size()-1).getStaffID() + 1;
            System.out.println(id);
        }catch(Exception e) {id=1;}

        System.out.println("Enter Staff Name");
        staffName = userInput.nextLine();

        System.out.println("Enter gender (M/F)");
        gender = userInput.nextLine().charAt(0);

        System.out.println("Enter Job Title");
        jobTitle = userInput.nextLine();

        staffList.add(new Staff(id, staffName, gender, jobTitle));
    }

    /**
     * Remove the staff by the id the user input 
     */
    public void removeStaffById(){
        long id;
        int index;
        System.out.println("Enter the staff ID to remove");
        id = userInput.nextLong();
        if((index=findStaffById(id))!=-1){
            System.out.println("Removed staff "+ staffList.get(index).getStaffName());
            staffList.remove(index);
        }
    }

    /**
     * Print all the staff id and name
     */
    public void printAllStaff(){
        System.out.println("*********** Staff ***********");
        System.out.printf("%-20s\t%-20s\n","Staff", "ID");
        for(int i=0; i<staffList.size(); i++){
            System.out.printf("%-20s\t%-20s\n", staffList.get(i).getStaffName(), staffList.get(i).getStaffID());
        }
    }
    
    /**
     * Print individual staff information which includes the name, gender and title 
     */
    public void viewStaffByID(){
        System.out.println("Enter the staff id to view");
        long id = userInput.nextLong();
        int index;
        if((index=findStaffById(id))!=-1){
            System.out.println("Name: " + staffList.get(index).getStaffName());
            System.out.println("Gender: " + staffList.get(index).getGender());
            System.out.println("Job Title: " + staffList.get(index).getJobTitle());
            return;
        }
    }

    /**
     * Find the staff by the given id
     * @param id of the staff to find
     * @return the index position where the staff with the id is located at 
     */
    public int findStaffById(long id){
        for(int i=0; i<staffList.size(); i++){
            if(staffList.get(i).getStaffID() == id){
                return i; 
            }
        }
        System.out.println("Staff not found");
        return -1;
    }
}
