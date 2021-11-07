package Utility;
import java.util.ArrayList;

import FlatFile.FlatFileAdapter;
import FlatFile.FlatFileHandler;
import FlatFile.FlatFileHelper;

/**
 * This class implement an abstract class for the managers
 * Manager that required to perform saving/retrieving of data will require to inherit this Manager class 
 */
public abstract class Manager implements FlatFileHelper{
    /**
     * Store an array of FlatFileAdapter for which all to perform saving/retrieving of data
     */
    private ArrayList<FlatFileAdapter> flatFileAdapters;

    /**
     * The default constructor for the class
     * It initialise the flatFileAdapter array and run the createFlatFileAdapter
     */
    public Manager(){
        flatFileAdapters = new ArrayList<FlatFileAdapter>();
        createFlatFileAdapter();
    }
    
    /**
     * An abstract method which require the classes that inherit this class to implement 
     */
    public abstract void createFlatFileAdapter();
    
    /**
     * To add the FlatFileAdapter into the array
     */
    @Override
    public void addFlatFileAdapter(FlatFileAdapter flatFileAdapter){
        flatFileAdapters.add(flatFileAdapter);
    }

    /**
     * Loop through all the adapter in the array to perform saving of data
     */
    @Override
    public void saveData(){
        FlatFileHandler flatFileHandler = FlatFileHandler.getInstance();
        for(int i=0;i<flatFileAdapters.size();i++){
            flatFileHandler.writeToFile(flatFileAdapters.get(i));
        }
    }

    /**
     * Loop through all the adapter in the array to perform retrieving of data 
     */
    @Override
    public void retrieveData(){
        FlatFileHandler flatFileHandler = FlatFileHandler.getInstance();
        for(int i=0;i<flatFileAdapters.size();i++){
            flatFileHandler.readFromFile(flatFileAdapters.get(i));
        }
    }
}
