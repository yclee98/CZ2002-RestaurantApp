package OrdersPackage;
import MenuItemPackage.*;

/**
 * This is an entity class that combines the MenuItem object and the quantity specified by the customer.
 */
public class OrderItems {

    /**
     * The MenuItem that is selected by the customer.
     */
    private MenuItem item;

    /**
     * The quantity of the MenuItem the customer wants to buy.
     */
    private long quantity;

    /**
     * Creates the orderItem object by amalgamation of the MenuItem and quantity.
     * @param newItem customer selected MenuItem.
     * @param quantity quantity of the selected MenuItem.
     */
    public OrderItems(MenuItem newItem, long quantity){
        this.item = newItem;
        this.quantity = quantity;
    }

    /**
     * Gets how many of the MenuItem the customer has ordered.
     * @return Number of the MenuItem ordered.
     */
    public long getQuantity(){
        return quantity;
    }

    /**
     * Gets the specific MenuItem the customer ordered.
     * @return MenuItem the customer ordered.
     */
    public MenuItem getItem(){
        return item;
    }

    /**
     * Sets the quantity of the MenuItem requested by the customer.
     * @param quantity quantity set by the customer.
     */
    public void setQuantity(long quantity){
        this.quantity = quantity;
    }
}
