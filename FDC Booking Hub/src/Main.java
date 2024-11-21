import java.util.InputMismatchException;
import java.util.Scanner;
import utils.admin.AdminManager;
import utils.database.DBConnection;
import utils.users.UserManager;

public class Main {
    public static void main(String[] args) {
        if (DBConnection.getConnection() != null) {
            System.out.println("\n\nSuccessfully connected to the database!");
        }

        Scanner input = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n*************************************************************************************");
            System.out.println("                                     FITSPACE                                        ");
            System.out.println("                        Fitness Development Center Booking Hub                       ");
            System.out.println("*************************************************************************************");
            System.out.println("1. User Registration");
            System.out.println("2. User Log-in");
            System.out.println("3. Admin Dashboard Login");
            System.out.println("4. Exit");
            System.out.print("\nEnter your choice number: ");

            try {
                choice = input.nextInt();
                input.nextLine(); 

                switch (choice) {
                    case 1:
                        UserManager.registerUser(input); 
                        break;
                    case 2:
                        UserManager.loginUser(input);
                        break;
                    case 3:
                        AdminManager.loginAdmin(input); 
                        break;
                    case 4:
                        System.out.println("\nThank you for using FDC Booking Hub. Have a great day!\n\n");
                        input.close();
                        return;
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 4.");
                input.next(); 
            }
        }
    }
}
