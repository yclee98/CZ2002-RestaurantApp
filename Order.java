import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {

    private long orderID;
    private double orderTotalPrice;
    private ArrayList<MenuItems> orderItems = new ArrayList<MenuItems>();
    private List<Integer> noIndividualItems = new ArrayList<>();
    private long staffID;
    private int tableNum;
    private LocalDateTime orderDateTime;
    private long custID;
    private int noOfItems;

    public Order(long sID, long cID, int tableNo){
        Random oIDgen = new Random();
        orderID = 10000000 + oIDgen.nextInt(90000000);
        staffID = sID;
        custID = cID;
        orderTotalPrice = 0;
    }

    public long getOrderID() {
        return orderID;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public long getStaffID() {
        return staffID;
    }

    public void setStaffID(long staffID) {
        this.staffID = staffID;
    }

    public void printOrder(long oID){

    }

    public void deAllocate(Table table){
        table.setAllocated(false);
    }

    public void calPrice(boolean isMember){
        orderTotalPrice = 0;
        for(int i = 0; i < noOfItems; i++){
            orderTotalPrice = orderTotalPrice + (orderItems.get(i).getPrice() * noIndividualItems.get(i));
        }
        // apply GST
        orderTotalPrice = orderTotalPrice * 1.07;
        if(isMember){
            orderTotalPrice = orderTotalPrice * 0.90; // 10% discount
        }
    }

    public void additems(MenuItems item, int qty){
        // check if item is already inside orderItems
        boolean found = false;
        for(int i = 0; i < noOfItems; i++){
            if(orderItems.get(i).getName() == item.getName()){
                noIndividualItems.set(i, (noIndividualItems.get(i) + qty));
                found = true;
                break;
            }
        }
        // if new menuitem to order, add new entry to order
        if(!found){
            orderItems.add(item);
            noIndividualItems.add(qty);
        }
    }

    public void remItems(MenuItems item, int qty){
        boolean found = false;
        for(int i = 0; i < noOfItems; i++) {
            if (orderItems.get(i).getName() == item.getName()) {
                noIndividualItems.set(i, (noIndividualItems.get(i) - qty));
                // if complete removal of menuitem -> don't want the item anymore
                if(noIndividualItems.get(i) == 0){
                    orderItems.remove(i);
                }
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Item not found in order!");
        }
    }

    public void viewOrder(long oID){

    }
}
