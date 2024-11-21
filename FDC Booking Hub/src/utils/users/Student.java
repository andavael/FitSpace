package utils.users;

import java.util.InputMismatchException;
import java.util.Scanner;
import utils.reservations.Reservation;
import utils.reservations.ReservationManager;

public class Student extends User {
    public Student(int userId, String uniqueId, String password, String firstName, String middleName, String lastName, String department, String gsuiteAccount) {
        super(userId, uniqueId, password, firstName, middleName, lastName, department, gsuiteAccount); 
    }
    

    @Override
    public void registerUser(Scanner input) {
        System.out.println("\n*************************************************************************************");
        System.out.println("                              STUDENT REGISTRATION AREA                            ");
        System.out.println("*************************************************************************************");

        while (true) {
            System.out.print("Enter your SR code (XX-XXXXX): ");
            this.uniqueId = input.nextLine();
            if (this.uniqueId.matches("\\d{2}-\\d{5}")) {
                if (!isUserExists(this.uniqueId, "student")) {
                    break;
                } else {
                    System.out.println("\nSR Code is already registered. Please enter a different one.\n");
                }
            } else {
                System.out.println("\nInvalid SR code format. Please try again.");
            }
        }

        super.commonRegistration(input, "student");
    }


    @Override
    public void loginUser(Scanner input) {
        System.out.println("\n*************************************************************************************");
        System.out.println("                                STUDENT LOGIN AREA                                 ");
        System.out.println("*************************************************************************************");
        System.out.print("Enter your SR code: ");
        String enteredSRCode = input.nextLine().trim();

        this.uniqueId = enteredSRCode;

        if (!isUserExists(enteredSRCode, "student")) {
            System.out.println("\nStudent is unregistered. Please register first.");
            return;
        }

        boolean isLoggedIn = super.commonLogin(input, "student");
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
            System.out.println("3. Log-out");
            System.out.print("\nEnter your choice: ");

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
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 4.");
                input.next(); 
            }
        }
    }
}
