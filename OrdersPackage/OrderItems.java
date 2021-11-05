package OrdersPackage;
import MenuItemPackage.MenuItem;

public class OrderItems {

    private MenuItem item;
    private long quantity;

    public OrderItems(MenuItem newItem, long quantity){
        this.item = newItem;
        this.quantity = quantity;
    }

    public long getQuantity(){
        return quantity;
    }

    public void setQuantity(long quantity){
        this.quantity = quantity;
    }

    public MenuItem getItem(){
        return item;
    }
}
