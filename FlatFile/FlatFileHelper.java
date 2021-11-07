package FlatFile;

/**
 * This interface is a helper class which specifies the method needed to handle the array of FlatFileAdapter
 * and saving and retrieving data
 */
public interface FlatFileHelper {
    /**
     * Create the flat file adapter, it allow multiple creating of adapter and store them inside an array
     */
    public void createFlatFileAdapter();

    /**
     * Add the adapter into an array
     * Calling thie method insisde the createFlatFileAdapter()
     * @param flatFileAdapter the interface adpater which is to be store in the array
     */
    public void addFlatFileAdapter(FlatFileAdapter flatFileAdapter);

    /**
     * Save the data into csv, it should save the data for all the adapter in the array
     */
    public void saveData();

    /**
     * Retrieve the data from csv, it should retrieve the data for all the adapter in the array
     */
    public void retrieveData();
}