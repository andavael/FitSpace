package utils.admin;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import utils.facilities.FacilityManager;
import utils.reservations.ReservationManager;
import utils.users.UserManager;

public class AdminManager {
    private static final List<Admin> admins = new ArrayList<>();

    static {
        admins.add(new Admin("ADMIN1", "FitSpace123"));
        admins.add(new Admin("ADMIN2", "FitSpace456"));
        admins.add(new Admin("ADMIN3", "FitSpace789"));
    }

    private static Admin getValidAdmin(String userID, String password) {
        for (Admin admin : admins) {
            if (admin.validateCredentials(userID, password)) {
                return admin;
            }
        }
        return null;
    }

    public static void loginAdmin(Scanner input) {
        System.out.println("\n*************************************************************************************");
        System.out.println("                               ADMIN DASHBOARD PORTAL                              ");
        System.out.println("*************************************************************************************");

        while (true) {
            System.out.print("Enter admin username: ");
            String userID = input.nextLine();

            System.out.print("Enter admin password: ");
            String password = input.nextLine();

            Admin loggedInAdmin = getValidAdmin(userID, password);

            if (loggedInAdmin != null) {
                System.out.println("\nAdmin login succesful.");
                displayAdminMenu(input, loggedInAdmin);
                break;
            } else {
                System.out.println("\nInvalid admin username or password. Please try again.");
            }
        }
    }

    private static void displayAdminMenu(Scanner input, Admin loggedInAdmin) {
    while (true) {
        System.out.println("\n*************************************************************************************");
        System.out.println("                               LOGGED IN AS " + loggedInAdmin.getAdminID());
        System.out.println("*************************************************************************************");
        System.out.println("1. Manage Users");
        System.out.println("2. Manage Reservations");
        System.out.println("3. Manage Facilities");
        System.out.println("4. Log-out");

        System.out.print("\nEnter your choice number: ");

        try {
            int choice = input.nextInt();
            input.nextLine(); 

            switch (choice) {
                case 1:
                    UserManager.reservationManagerMenu(input);
                    break;
                case 2:
                    ReservationManager.reservationManagerMenu(input);
                    break;
                case 3:
                    FacilityManager.facilityManagerMenu(input);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return; 
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break; 
            }
        } catch (InputMismatchException e) {
            System.out.println("\nInvalid input. Please enter a number between 1 and 4.");
            input.next();
        }
    }
}

}
