public class
RestaurantManager{
    public RestaurantManager(){

    }

    //Menu items
    public void createMenuItem(){
        //take in an input from resutaurantUI to detemine if it is to create a ala carte or promotion
    }

    public void viewAllMenuItems(){
        //i think view menu no need show full information for each item since this app is for staff not customer
        //display all MenuItem id and some other deem neccessary but should not show all details
        //show min detail to mainly show id so we know which id to key for the other function
        //sort by category??promotion??
    }

    public void viewIndividualMenuItem(long itemId){
        //can do polymorphism to view no need create seperately for ala carte and promotion as both inherit the same
        //display all information for this item
    }

    public void updateMenuItem(long itemId){
        //can do polymorphism to update no need create seperately for ala carte and promotion as both inherit the same
    }

    public void removeMenuItem(long itemId){
        //can do polymorphism to remove no need create seperately for ala carte and promotion as both inherit the same
    }


    //Order
    public void createOrder(){
        
    }

    public void viewAllOrders(){
        //display all order id and some other deem neccessary but should not show all details
        //show min detail to mainly show id so we know which id to key for the other function
    }

    public void viewIndividualOrder(long orderId){
        //display all order details and the menu items inside this order
    }

    public void addToOrder(long orderId){
        //do a loop for user to add multiple
    }

    public void removeFromOrder(long orderId){
        //do a loop for user to remove multiple
    }

    public void printOrderInvoice(long orderId){        
    }

    //Reservation
    public void createReservation(){

    }

    public void viewAllReservations(){
        //display all reservation Id and some other deem neccessary but should not show all details
        //show min detail to mainly show id so we know which id to key for the other function
    }

    public void checkIndividualReservation(long reservationId){
        //display individual reservation details
    }

    public void removeReservation(long reservationId){
    }

    //Table
    public void checkTableAvailability(){
        //display all available table that is not reserved 
    }

    //Sales Report
    public void printSaleReport(){

    }

}