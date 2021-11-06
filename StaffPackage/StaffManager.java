package StaffPackage;
import java.util.ArrayList;
import java.util.Scanner;


import FlatFile.FlatFileAdapter;
import Utility.Manager;

public class StaffManager extends Manager{
    private ArrayList<Staff> staffList = new ArrayList<>();
    private Scanner userInput = new Scanner(System.in);

    public StaffManager(){
        this.retrieveData();
    }

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

    public void printAllStaff(){
        System.out.println("Listing all the Staffs");
        for(int i=0; i<staffList.size(); i++){
            System.out.println(staffList.get(i).getStaffID()+ " " + staffList.get(i).getStaffName());
        }
    }
    
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

    public int findStaffById(long id){
        for(int i=0; i<staffList.size(); i++){
            if(staffList.get(i).getStaffID() == id){
                return i; 
            }
        }
        System.out.println("Staff not found");
        return -1;
    }

    public void getting(){
        System.out.println(staffList.get(0).getStaffName());
        System.out.println(staffList.get(4).getStaffName());
    }
    
}
