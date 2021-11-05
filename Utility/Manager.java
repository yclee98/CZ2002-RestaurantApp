package Utility;
import java.util.ArrayList;

import FlatFile.FlatFileAdapter;
import FlatFile.FlatFileHandler;
import FlatFile.FlatFileHelper;

public abstract class Manager implements FlatFileHelper{
    private ArrayList<FlatFileAdapter> flatFileAdapters;

    public Manager(){
        flatFileAdapters = new ArrayList<FlatFileAdapter>();
        createFlatFileAdapter();
    }
    
    public abstract void createFlatFileAdapter();
    
    @Override
    public void addFlatFileAdapter(FlatFileAdapter flatFileAdapter){
        flatFileAdapters.add(flatFileAdapter);
    }

    @Override
    public void saveData(){
        FlatFileHandler flatFileHandler = FlatFileHandler.getInstance();
        for(int i=0;i<flatFileAdapters.size();i++){
            flatFileHandler.writeToFile(flatFileAdapters.get(i));
        }
    }

    @Override
    public void retrieveData(){
        FlatFileHandler flatFileHandler = FlatFileHandler.getInstance();
        for(int i=0;i<flatFileAdapters.size();i++){
            flatFileHandler.readFromFile(flatFileAdapters.get(i));
        }
    }
}
