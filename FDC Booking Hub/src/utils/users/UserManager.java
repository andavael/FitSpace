package utils.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import utils.database.DBConnection;

public class UserManager {

    public static void userManagerMenu(Scanner scanner) {
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
        
        String userQuery = "SELECT user_id, first_name, last_name, department, user_type, gsuite_account FROM users WHERE unique_id = ?";
        
        String reservationQuery = "SELECT r.reservation_id, f.name AS facility_name, r.reservation_date, ts.start_time, ts.end_time, r.status " +
                                  "FROM reservations r " +
                                  "JOIN facilities f ON r.facility_id = f.facility_id " +
                                  "JOIN time_slots ts ON r.slot_id = ts.slot_id " +
                                  "WHERE r.user_id = (SELECT user_id FROM users WHERE unique_id = ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement userStmt = conn.prepareStatement(userQuery);
             PreparedStatement reservationStmt = conn.prepareStatement(reservationQuery)) {
        
            userStmt.setString(1, uniqueId);
            reservationStmt.setString(1, uniqueId);

            try (ResultSet userRs = userStmt.executeQuery()) {
                if (userRs.next()) {
                    String firstName = userRs.getString("first_name");
                    String lastName = userRs.getString("last_name");
                    String department = userRs.getString("department");
                    String userType = userRs.getString("user_type");
                    String gsuiteAccount = userRs.getString("gsuite_account");
        
                    System.out.println("\nUser Details:");
                    System.out.println("Name: " + firstName + " " + lastName);
                    System.out.println("Unique ID: " + uniqueId);
                    System.out.println("Department: " + department);
                    System.out.println("Role: " + userType);
                    System.out.println("Gsuite Account: " + gsuiteAccount);
                } else {
                    System.out.println("No user found with the given Unique ID.");
                    return;
                }
            }
        
            try (ResultSet reservationRs = reservationStmt.executeQuery()) {
                System.out.println("\n*************************************************************************************");
                System.out.println("                               USER RESERVATION HISTORY                            ");
                System.out.println("*************************************************************************************");
                System.out.printf("%-15s %-20s %-15s %-10s %-10s %-10s%n", 
                                  "Reservation ID", "Facility Name", "Reservation Date", "Start Time", "End Time", "Status");
                System.out.println("-------------------------------------------------------------------------------------");
        
                boolean hasReservations = false;
                while (reservationRs.next()) {
                    int reservationId = reservationRs.getInt("reservation_id");
                    String facilityName = reservationRs.getString("facility_name");
                    String reservationDate = reservationRs.getString("reservation_date");
                    String startTime = reservationRs.getString("start_time");
                    String endTime = reservationRs.getString("end_time");
                    String status = reservationRs.getString("status");
        
                    System.out.printf("%-15d %-20s %-15s %-10s %-10s %-10s%n", 
                                      reservationId, facilityName, reservationDate, startTime, endTime, status);
                    hasReservations = true;
                }
        
                if (!hasReservations) {
                    System.out.println("No reservation history found for this user.");
                }
        
            }
        
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
    
    
}
