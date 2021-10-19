package FlatFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FlatFileHandler{
    private static FlatFileHandler flatFileHandler=null;
    //private constructor, only can be created through getInstance
    private FlatFileHandler(){

    }

    //singleton implementation, only one FlatFileHandler can exists
    public static FlatFileHandler getInstance(){
        if(flatFileHandler==null){
            flatFileHandler = new FlatFileHandler();
        }
        return flatFileHandler;
    }

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
