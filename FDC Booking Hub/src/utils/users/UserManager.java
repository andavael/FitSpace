package utils.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import utils.database.DBConnection;

public class UserManager {

    public static void reservationManagerMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n*************************************************************************************");
            System.out.println("                                    USER MANAGER                                     ");
            System.out.println("*************************************************************************************");
            System.out.println("1. View all users");
            System.out.println("2. Search for a user");
            System.out.println("3. Exit");

            System.out.print("\nEnter your choice number: ");
    
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
    
                switch (choice) {
                    case 1:
                        viewAllUser();
                        break;
                    case 2:
                        searchForUser(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting Facility Manager...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 3.");
                scanner.next(); 
            }
        }
    }
    
    private static User getUserType(Scanner input) {
        while (true) {
            System.out.println("\n*************************************************************************************");
            System.out.println("                                   SELECT USER TYPE                                  ");
            System.out.println("*************************************************************************************");
            System.out.println("1. Student");
            System.out.println("2. Employee");
            System.out.println("3. Exit");
            System.out.print("\nEnter the number of your corresponding role: ");

            try {
                int roleChoice = input.nextInt();
                input.nextLine(); 
    
                switch (roleChoice) {
                    case 1:
                        return new Student(0, "", "", "", "", "", "", "");
                    case 2:
                        return new Employee(0, "", "", "", "", "", "", "");
                    case 3:
                        return null; 
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 3.");
                input.next();
            }
        }
    }

    public static void registerUser(Scanner input) {
        User user = getUserType(input);  
        if (user != null) {
            user.registerUser(input);
        } else {
            System.out.println("Exiting registration...");
        }
    }

    public static void loginUser(Scanner input) {
        User user = getUserType(input); 
        if (user != null) {
            user.loginUser(input);
        } else {
            System.out.println("Exiting login...");
        }
    }

    public static void viewAllUser() {
        System.out.println("\n*************************************************************************************");
        System.out.println("                                  FITSPACE USERS                                   ");
        System.out.println("*************************************************************************************");

        String query = "SELECT user_id, unique_id, user_type FROM users";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            System.out.printf("%-10s %-15s %-10s%n", 
                              "User ID", "Unique ID", "Role");
            System.out.println("-------------------------------------------------------------------------------------");
    
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String uniqueId = rs.getString("unique_id");
                String userType = rs.getString("user_type");
    
                System.out.printf("%-10d %-15s %-10s%n", 
                                  userId, uniqueId, userType);
            }
            System.out.println("-------------------------------------------------------------------------------------");
    
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
    
    public static void searchForUser(Scanner input) {
        System.out.println("\n*************************************************************************************");
        System.out.println("                               SEARCH FOR A USER                                   ");
        System.out.println("*************************************************************************************");
        System.out.print("Enter the Unique ID (SR code/Employee ID) of the user: ");
        String uniqueId = input.nextLine().trim();
    
        String query = "SELECT * FROM users WHERE unique_id = ?";
    
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setString(1, uniqueId); 
    
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String middleName = rs.getString("middle_name");
                String lastName = rs.getString("last_name");
                String department = rs.getString("department");
                String userType = rs.getString("user_type");
                String gsuiteAccount = rs.getString("gsuite_account");

                System.out.println("\n-------------------------------------------------------------------------------------");
                System.out.printf("         FitSpace ID    :    %d%n", userId);
                System.out.printf("         Unique ID      :    %s%n", uniqueId);
                System.out.printf("         Name           :    %s %s %s%n", firstName, (middleName != null && !middleName.isEmpty()) ? middleName : "", lastName);
                System.out.printf("         Department     :    %s%n", (department != null && !department.isEmpty()) ? department : "N/A");
                System.out.printf("         Role           :    %s%n", userType);
                System.out.printf("         Gsuite Account :    %s%n", gsuiteAccount);
                System.out.println("-------------------------------------------------------------------------------------");
            } else {
                System.out.println("No user found with the provided Unique ID.");
            }
    
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
