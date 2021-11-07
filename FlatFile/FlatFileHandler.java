package FlatFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * This class implement the handler use to handle saving/retrieving to/from flatfile
 * A singleton design is use as only one of this class is needed in the system 
 * getInstance() is use to get the only FlatFileHandler object in the system
 * Constructor is set to private to prevent other classes from creating an object of this class
 */
public class FlatFileHandler{
    /**
     * The single object FlatFileHandler in the system 
     */
    private static FlatFileHandler flatFileHandler=null;

    /**
     * Default constructor
     */
    private FlatFileHandler(){
    }

    /**
     * @return the only FlatFileHandler object in the system
     */
    public static FlatFileHandler getInstance(){
        if(flatFileHandler==null){
            flatFileHandler = new FlatFileHandler();
        }
        return flatFileHandler;
    }
    /**
     * Get the file object to perform saving/retrieving, if the file do not exists then it will create one 
     * @param fileName of the file  
     * @return the file itself 
     */
    public File getFile(String fileName) {
        File file = new File("CSVFiles/"+fileName);
        try{
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error creating file for "+fileName);
        }
        return file;
    }
    
    /**
     * Use to write data to the file
     * @param flatFileAdapter which is the interface that specify the action to perform for each classes that run this method
     */
    public void writeToFile(FlatFileAdapter flatFileAdapter) {
        File file = getFile(flatFileAdapter.getFileName());
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        PrintWriter printWriter;
        try{            
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            int i=0;
            String data;
            printWriter.println(flatFileAdapter.getColumnsName());
            while((data=flatFileAdapter.insertRow(i))!=null){
                printWriter.println(data);
                i++;
            }
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error writing to file");
        }
    }

    /**
     * Use to read data from the file 
     * @param flatFileAdapter which is the interface that specify the action to perform for each classes that run this method
     */
    public void readFromFile(FlatFileAdapter flatFileAdapter) {
        File file = getFile(flatFileAdapter.getFileName());
        FileReader fileReader;
        BufferedReader bufferedReader;
        String[] row;
        try{            
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            String inputLine = bufferedReader.readLine();
            while(inputLine != null){
                row = inputLine.split(",");
                flatFileAdapter.extractRow(row);
                inputLine=bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error reading from file");
        }
    }
    
}
