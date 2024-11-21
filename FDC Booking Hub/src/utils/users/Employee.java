package utils.users;

import java.util.InputMismatchException;
import java.util.Scanner;
import utils.reservations.Reservation;
import utils.reservations.ReservationManager;

public class Employee extends User {
    public Employee(int userId, String uniqueId, String firstName, String middleName, String lastName, String password, String department, String gsuiteAccount) {
        super(userId, uniqueId, password, firstName, middleName, lastName, department, gsuiteAccount); 
    }
    

    @Override
    public void registerUser(Scanner input) {
        System.out.println("\n*************************************************************************************");
        System.out.println("                             EMPLOYEE REGISTRATION AREA                            ");
        System.out.println("*************************************************************************************");
    
        while (true) {
            System.out.print("Enter your employee ID (XXXXX): ");
            this.uniqueId = input.nextLine();
            if (this.uniqueId.matches("\\d{5}")) {
                if (isUserExists(this.uniqueId, "employee")) {
                    System.out.println("\nEmployee ID is already registered. Please enter a different one.\n");
                } else {
                    break;
                }
            } else {
                System.out.println("\nInvalid Employee ID format. Please try again.");
            }
        }
    
        super.commonRegistration(input, "employee");
    }
    

    @Override
    public void loginUser(Scanner input) {
        System.out.println("\n*************************************************************************************");
        System.out.println("                                 EMPLOYEE LOGIN AREA                               ");
        System.out.println("*************************************************************************************");

        System.out.print("Enter your Employee ID: ");
        String enteredEmployeeID = input.nextLine().trim();

        this.uniqueId = enteredEmployeeID;

        if (!isUserExists(enteredEmployeeID, "employee")) {
            System.out.println("\nEmployee is unregistered. Please register first.");
            return;
        }

        boolean isLoggedIn = super.commonLogin(input, "employee");
        if (isLoggedIn) {
            System.out.println("\nLogin successful!");
            
            displayUserMenu(input, this.userId); 
        } else {
            System.out.println("\nLogin failed! Incorrect login credentials.");
        }
    }
    
    @Override
    public void displayUserMenu(Scanner input, int userId) {
        while (true) {
            super.displayMenuHeader(); 

            System.out.println("1. Make a reservation");
            System.out.println("2. View my confirmed reservations");
            System.out.println("3. View All reservations");
            System.out.println("4. Log-out");
            System.out.print("\nEnter your choice number: ");

            try {
                int choice = input.nextInt();
                input.nextLine(); 

                switch (choice) {
                    case 1:
                        Reservation.makeReservation(userId, input);
                        break;
                    case 2:
                        ReservationManager.viewMyReservation(this.userId, input);
                        break;
                    case 3:
                        ReservationManager.viewAllReservations(input);  
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 4.");
                input.next(); 
            }
        }
    }
}
