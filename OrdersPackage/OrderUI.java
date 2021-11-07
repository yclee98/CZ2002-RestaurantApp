package OrdersPackage;
import CustomerPackage.*;
import TablePackage.TableManager;
import MenuItemPackage.*;
import PromoPackage.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import StaffPackage.*;

/**
 * Main orderUI to recieve user input to do different order functions
 */
public class OrderUI {

    public OrderUI(){}

    /**
     * View all existing orders
     * @param order_Mngr manager responsible for managing orders
     */
    public static void viewAllOrders(OrderManager order_Mngr){
        order_Mngr.viewAllOrders();
    }

    /**
     * UI to create new orders
     * @param cust_Mngr manager responsible for checking customerID
     * @param staff_Mngr manager responsible for checking StaffID
     * @param table_Mngr manager responsible for finding available tables
     * @param order_Mngr manager responsible for storing the new order
     */
    public static void CreateOrders(CustomerManager cust_Mngr, StaffManager staff_Mngr, TableManager table_Mngr,
                                    OrderManager order_Mngr){

        Scanner userInput = new Scanner(System.in);
        long cID, sID;
        int tableNo = -1, inOut;

        cust_Mngr.printCustomers();
        do {
            System.out.println("Please enter the customerID ");
            try {
                cID = userInput.nextLong();
            }
            catch (InputMismatchException m) {
                System.out.println("ERROR! Please enter the ID of the customer!");
                userInput.nextLine();
                continue;
            }
            break;
        }while(true);

        Customer newCust = cust_Mngr.findCustomer(cID);
        if(newCust == null){
            System.out.println("No customer with that ID is found! ");
            return;
        }

        staff_Mngr.printAllStaff();
        do{
            System.out.println("Please enter the StaffID ");
            try{
                sID = userInput.nextLong();
                if(staff_Mngr.findStaffById(sID)!=-1){
                    break;
                }
            }
            catch(InputMismatchException m){
                System.out.println("ERROR! Please enter the ID of the Staff!");
                userInput.nextLine();
            }
            // break;
        }while(true);

        System.out.println("Please specify eating in or take away");
        System.out.println("1) Eating in");
        System.out.println("2) Take away");
        do {
            try {
                inOut = userInput.nextInt();
            }
            catch (InputMismatchException m) {
                System.out.println("ERROR! Enter 1 or 2");
                userInput.nextLine();
                continue;
            }
            if(inOut > 2 || inOut < 1){
                System.out.println("ERROR! Enter 1 or 2");
                continue;
            }
            break;
        }while(true);
        order_Mngr.createOrder(sID, newCust, inOut, table_Mngr);
        order_Mngr.printCustomerAndOrders();
    }

    /**
     * Method to recieve user input on what order to view
     * @param order_Mngr manager responsible for finding and displaying the order
     */
    public static void viewIndividualOrders(OrderManager order_Mngr){
        long orderID;
        Scanner userInput = new Scanner(System.in);
        order_Mngr.printCustomerAndOrders();
        System.out.println("Please enter the desired order ID to view");
        orderID = userInput.nextLong();
        order_Mngr.viewOrder(orderID);
    }

    /**
     * UI to recieve user input on MenuItems/PromoSetMeal to add
     * @param order_Mngr manager responsible to add the item
     * @param item_Mngr manager responsible to find the MenuItem to add
     * @param promo_Mngr manager responsible to find the PromoItem to add
     */
    public static void AddItemsToOrder(OrderManager order_Mngr, ItemManager item_Mngr, PromoManager promo_Mngr){
        Scanner userInput = new Scanner(System.in);
        long orderID;
        MenuItem cusItem;
        PromoSetMeal cusMeal;
        order_Mngr.printCustomerAndOrders();
        System.out.println("Please enter the orderID to add");
        orderID = userInput.nextLong();
        if(!order_Mngr.checkOrderExists(orderID)){
            System.out.println("ERROR! Order does not exist!");
            return;
        }
        do{
            System.out.println("Please select the following meal options");
            System.out.println("1) Ala Carte");
            System.out.println("2) Promotional Meals");
            System.out.println("3) Done");
            int mealOp = userInput.nextInt();
            if(mealOp == 1){
                System.out.println("=============== MENU ================");
                System.out.println("--- Ala Carte ---");
                item_Mngr.viewAllMenuItems();
                do {
                    System.out.println("Specify item index to add (Enter -1 to finish)");
                    int item_idx = userInput.nextInt();
                    if (item_idx == -1) {
                        break;
                    }
                    cusItem = item_Mngr.returnIndividualMenuItem(item_idx);
                    if(cusItem == null){
                        System.out.println("No item with index " + item_idx);
                        continue;
                    }
                    System.out.println("Specify quantity to add ");
                    int qty = userInput.nextInt();
                    order_Mngr.addItemToOrder(orderID, cusItem, qty);
                }while(true);
            }
            else if(mealOp == 2){
                System.out.println("=============== MENU ================");
                System.out.println("--- Promotional Meals ---");
                promo_Mngr.viewAllPromo();
                do {
                    System.out.println("Specify meal index to add (Enter -1 to finish)");
                    int item_idx = userInput.nextInt();
                    if (item_idx == -1) {
                        break;
                    }
                    cusMeal = promo_Mngr.returnPromo(item_idx);
                    if(cusMeal == null){
                        System.out.println("No item with index " + item_idx);
                        continue;
                    }
                    System.out.println("Specify quantity to add (Enter -1 to finish)");
                    int qty = userInput.nextInt();
                    order_Mngr.addItemToOrder(orderID, cusMeal, qty);
                }while(true);
            }
            else{break;}
        }while(true);
    }

    /**
     * UI to recieve user input on which order to remove items from based on orderID
     * @param order_Mngr manager responsible in removing items from orders
     */
    public static void removeItemsFromOrder(OrderManager order_Mngr){
        Scanner userInput = new Scanner(System.in);
        long orderID;
        order_Mngr.printCustomerAndOrders();
        System.out.println("Please enter the desired order ID to remove items from");
        orderID = userInput.nextLong();
        if(!order_Mngr.checkOrderExists(orderID)){
            System.out.println("ERROR! OrdersPackage.Order does not exist!");
            return;
        }
        order_Mngr.removeItemFromOrder(orderID);
    }

    /**
     * UI to recieve user input on which order to pay
     * @param order_Mngr manager responsible for order payment
     * @param invoice_Mngr manager responsible for converting the paid order to invoice
     * @param table_Mngr manager responsible for unassigning table if applicable
     */
    public static void settlePayment(OrderManager order_Mngr, InvoiceManager invoice_Mngr, TableManager table_Mngr){
        Scanner userInput = new Scanner(System.in);
        order_Mngr.printCustomerAndOrders();
        System.out.println("Please enter the orderID to pay");
        long orderID = userInput.nextLong();
        order_Mngr.settlePayment(orderID, invoice_Mngr, table_Mngr);
    }

    /**
     * UI to recieve user input on which invoice to add based on invoiceID
     * @param invoice_Mngr
     */
    public static void printOrderInvoice(InvoiceManager invoice_Mngr){
        Scanner userInput = new Scanner(System.in);
        invoice_Mngr.printInvoiceIDAndDate();
        System.out.println("Please enter the desired invoice ID to print");
        long invoiceID = userInput.nextLong();
        invoice_Mngr.printInvoice(invoiceID);
    }

    /**
     * Invokes invoice_Mngr to print all existing invoices stored in the orders.csv
     * @param invoice_Mngr manager responsible for retrieving and printing all existing invoices
     */
    public static void printAllInvoices(InvoiceManager invoice_Mngr){
        invoice_Mngr.printAllInvoices();
    }

    public static void main(String[] args){
        Scanner userInput = new Scanner(System.in);
        OrderManager order_Mngr = new OrderManager();
        InvoiceManager invoice_Mngr = new InvoiceManager();
        PromoManager promo_Mngr = new PromoManager();
        ItemManager item_Mngr = new ItemManager();
        TableManager table_Mngr = new TableManager();
        StaffManager staff_Mngr = new StaffManager();
        CustomerManager cust_Mngr = new CustomerManager();

        int option;
        do{
            System.out.println("***** Order *****");
            System.out.println("Select an option");
            System.out.println("1. View All Orders");
            System.out.println("2. Create Order");
            System.out.println("3. View Individual Order");
            System.out.println("4. Add Items to Order");
            System.out.println("5. Remove items from Order");
            System.out.println("6. Pay for items from Order");
            System.out.println("7. Print Order Invoice");
            System.out.println("8. Print All Invoice");
            System.out.println("9. Back");
            option = userInput.nextInt();
            System.out.println();

            switch(option){

                case 1: // view all orders
                    OrderUI.viewAllOrders(order_Mngr);
                    break;

                case 2: // Create orders
                    OrderUI.CreateOrders(cust_Mngr, staff_Mngr, table_Mngr, order_Mngr);
                    break;

                case 3: //View Individual order
                    OrderUI.viewIndividualOrders(order_Mngr);
                    break;

                case 4: // add items to order
                    // print all the customer names and the associated orderIDs
                    OrderUI.AddItemsToOrder(order_Mngr, item_Mngr, promo_Mngr);
                    break;

                case 5: // Remove items from order
                    OrderUI.removeItemsFromOrder(order_Mngr);
                    break;

                case 6: //
                    OrderUI.settlePayment(order_Mngr, invoice_Mngr, table_Mngr);
                    break;

                case 7: // print order invoice
                    OrderUI.printOrderInvoice(invoice_Mngr);
                    break;

                case 8: // print all invoice
                    OrderUI.printAllInvoices(invoice_Mngr);
                    break;
                default:
            }
        }while(option>0 && option<9);
    }
}
