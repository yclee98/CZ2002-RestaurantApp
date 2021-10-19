import java.util.ArrayList;

import FlatFile.FlatFileAdapter;

public class TestManager extends Manager {
    public ArrayList<Staff> allStaff = new ArrayList<Staff>();
    @Override
    public void createFlatFileAdapter() {
        super.addFlatFileAdapter(new FlatFileAdapter(){
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
                    return allStaff.get(index).toCSVFormat();
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            public void extractRow(String[] row) {
                allStaff.add(new Staff(Long.parseLong(row[0]), row[1], row[2].charAt(0), row[3]));                
            }
        });  
        super.addFlatFileAdapter(new FlatFileAdapter(){
            @Override
            public String getFileName() {
                return "test.csv";
            }

            @Override
            public String getColumnsName() {
                return "test1,test2,test3";
            }

            @Override
            public String insertRow(int index) {
                if(index==3){
                    return null;
                }
                else{
                    return "1,2,3,4";
                }
            }

            @Override
            public void extractRow(String[] row) {
                System.out.println(row[0]);
            }
            
        }); 
    }

    public void addToArray(Staff an){
        allStaff.add(an);
    }

    public static void main(String[] args) {
        TestManager tm = new TestManager();
        tm.addToArray(new Staff(11,"tim", 'm', "chief"));
        tm.addToArray(new Staff(22,"mary", 'f', "waitress"));
        tm.addToArray(new Staff(33,"john", 'm', "waiter"));
        tm.saveData();
        // tm.retrieveData();
        // System.out.println(tm.allStaff.get(0).getStaffName());
        // System.out.println(tm.allStaff.get(1).getStaffName());
    }
    
}
