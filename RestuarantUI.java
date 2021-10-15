import java.util.Scanner;

public class RestuarantUI {
    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args){
        mainMenu();        
    }

    private static void mainMenu(){
        int option;
        do{
            System.out.println("*****Main Menu*****");
            System.out.println("Select an option");
            System.out.println("1. Menu Items/Promotion");
            System.out.println("2. Order");
            System.out.println("3. Reservation");
            System.out.println("4. Check Table Availability");
            System.out.println("5. Print Sale Revenue Report");
            System.out.println("6. Exit");
            option = userInput.nextInt();
            System.out.println();

            switch(option){
                case 1: 
                    menu();
                    break;
                case 2:
                    order();
                    break;
                case 3:
                    reservation();
                    break;
                case 4:
                    //go to check table availability
                    break;
                case 5:
                    //go to print sales revenue report
                    break;
                default:
            }
        }while(option>0 && option<6);
    }

    private static void menu(){
        int menuOption;
        do{
            System.out.println("*****Menu Items/Promotion*****");
            System.out.println("Select an option");
            System.out.println("1. View Menu"); 
            System.out.println("2. Create Menu Item");
            System.out.println("3. Update Menu Item");
            System.out.println("4. Remove Menu Item");
            System.out.println("5. Create Promotion");
            System.out.println("6. Update Promotion");
            System.out.println("7. Remove Promotion");
            System.out.println("8. Back");
            menuOption = userInput.nextInt();
            System.out.println();
            
            switch(menuOption){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
            }

        }while(menuOption>0 && menuOption<8);
    }

    private static void order(){
        int orderOption;
        do{
            System.out.println("*****Order*****");
            System.out.println("Select an option");
            System.out.println("1. View All Orders");
            System.out.println("2. Create Order");
            System.out.println("3. View Individual Order");
            System.out.println("4. Add Items to Order"); 
            System.out.println("5. Remove items from Order");
            System.out.println("6. Print Order Invoice");
            System.out.println("7. Back");
            orderOption = userInput.nextInt();
            System.out.println();

            switch(orderOption){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
            }
        }while(orderOption>0 && orderOption<7);
    }

    private static void reservation(){
        int reservationOption;
        do{
            System.out.println("*****Reservation*****");
            System.out.println("Select an option");
            System.out.println("1. View All Reservations");
            System.out.println("2. Create Reservation Booking");
            System.out.println("3. View Individual Reservation Booking");
            System.out.println("4. Remove Reservation BOoking");
            System.out.println("5. Back");
            reservationOption = userInput.nextInt();
            System.out.println();

            switch(reservationOption){
                case 1:
                    break;
                case 2:
                    break;
                default:
            }
        }while(reservationOption>0 && reservationOption<5);
    }
}
