package utils.reservations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import utils.database.DBConnection;
import utils.facilities.Facility;
import utils.facilities.FacilityManager;

public class ReservationManager {
    public static void reservationManagerMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n*************************************************************************************");
            System.out.println("                               RESERVATION MANAGER                                  ");
            System.out.println("*************************************************************************************");
            System.out.println("1. View all reservations");
            System.out.println("2. Change Status of Existing Reservations");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice number: ");
    
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
    
                switch (choice) {
                    case 1:
                        viewAllReservations(scanner);;
                        break;
                    case 2:
                        modifyReservationStatus(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting Reservation Manager...");
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

    @SuppressWarnings("CallToPrintStackTrace")
    public static void saveReservation(int userId, Facility selectedFacility, String date, String timeSlot, int slotId) {
        try (Connection conn = DBConnection.getConnection()) {
    
            String sql = "INSERT INTO reservations (user_id, facility_id, slot_id, reservation_date, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, userId);                  
                pstmt.setInt(2, selectedFacility.getId());  
                pstmt.setInt(3, slotId);                  
                pstmt.setString(4, date);                  
                pstmt.setString(5, "confirmed");         
    
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int reservationId = generatedKeys.getInt(1);
                            System.out.println("\nYour reservation has been successfully saved!");
                            System.out.println("-------------------------------------------------------------------------------------");
                            System.out.println("                             FITSPACE RESERVATION RECEIPT                            ");
                            System.out.println("-------------------------------------------------------------------------------------");
                            System.out.println("                RESERVATION ID      :   " + reservationId);   
                            System.out.println("                FITSAPCE USER ID    :   " + userId);   
                            System.out.println("                FACILITY            :   " + selectedFacility.getName());
                            System.out.println("                DATE                :   " + date);  
                            System.out.println("                TIME                :   " + timeSlot);
                            System.out.println("-------------------------------------------------------------------------------------");
                        }
                    }
                } else {
                    System.out.println("Failed to save reservation. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
    
    @SuppressWarnings("CallToPrintStackTrace")
    public static void viewMyReservation(int userId, Scanner input) {
        System.out.println("\n*************************************************************************************");
        System.out.println("                                  MY RESERVATIONS                                    ");
        System.out.println("*************************************************************************************");
    
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT r.reservation_id, f.name, r.reservation_date, ts.start_time, ts.end_time " +
                         "FROM reservations r " +
                         "JOIN facilities f ON r.facility_id = f.facility_id " +
                         "JOIN time_slots ts ON r.slot_id = ts.slot_id " +
                         "WHERE r.user_id = ? AND r.status = 'confirmed'"; 
            
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                ResultSet rs = stmt.executeQuery();
    
                boolean hasReservations = false;
                System.out.printf("%-15s %-25s %-15s %-15s %-15s%n", 
                                  "Reservation ID", "Facility Name", "Date", "Start Time", "End Time");
                System.out.println("-------------------------------------------------------------------------------------");
    
                while (rs.next()) {
                    hasReservations = true;
                    int reservationId = rs.getInt("reservation_id");
                    String facilityName = rs.getString("name");
                    String date = rs.getString("reservation_date");
                    String startTime = rs.getString("start_time");
                    String endTime = rs.getString("end_time");

                    System.out.printf("%-15d %-25s %-15s %-15s %-15s%n", 
                                      reservationId, facilityName, date, startTime, endTime);
                }
                System.out.println("-------------------------------------------------------------------------------------");
    
                if (!hasReservations) {
                    System.out.println("No active reservations found.");
                    return;
                }
            }

            boolean validChoice = false; 
    
            while (!validChoice) {
                System.out.println("\n1. Cancel a Reservation");
                System.out.println("2. Go back to Menu");
                System.out.print("\nEnter your choice number: ");
                
                try {
                    int choice = input.nextInt();
                    input.nextLine(); 
                    switch (choice) {
                        case 1:
                            Cancellation.cancelReservation(userId, input); 
                            validChoice = true;  
                            break;
                        case 2:
                            validChoice = true; 
                            break;
                        default:
                            System.out.println("\nInvalid choice. Please try again.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input. Please enter a valid number.");
                    input.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("CallToPrintStackTrace")
    public static void viewAllReservations(Scanner scanner) {
        FacilityManager.displayFacilities();
    
        System.out.print("\nSelect a facility to view reservations (0 to go back): ");
        List<Facility> facilities = Facility.getAllFacilities();
    
        int facilityChoice = -1;
        boolean validInput = false;
    
        while (!validInput) {
            try {
                facilityChoice = scanner.nextInt();
                scanner.nextLine();  
    
                if (facilityChoice == 0) {
                    return; 
                } else if (facilityChoice < 1 || facilityChoice > facilities.size()) {
                    System.out.print("\nInvalid input. Please enter a number between 1 and " + facilities.size() + ", or 0 to go back: ");
                } else {
                    validInput = true;  
                }
            } catch (InputMismatchException e) {
                System.out.print("\nInvalid input. Please enter a number between 1 and " + facilities.size() + ", or 0 to go back: ");
                scanner.nextLine();  
            }
        }
    
        Facility selectedFacility = facilities.get(facilityChoice - 1);
    
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT r.reservation_id, u.unique_id, r.reservation_date, r.status, ts.start_time, ts.end_time " +
                         "FROM reservations r " +
                         "JOIN users u ON r.user_id = u.user_id " +
                         "JOIN time_slots ts ON r.slot_id = ts.slot_id " +
                         "WHERE r.facility_id = ? " +
                         "ORDER BY r.reservation_id ASC";
    
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, selectedFacility.getId());
                ResultSet rs = stmt.executeQuery();
    
                boolean hasReservations = false;
    
                System.out.println("\n*************************************************************************************");
                System.out.println("RESERVATION FOR " + selectedFacility.getName().toUpperCase());
                System.out.println("*************************************************************************************");
                System.out.printf("%-10s %-13s %-20s %-10s %-15s %-15s\n", "ID", "Reserver", "Reservation Date", "Status", "Start Time", "End Time");
                System.out.println("-------------------------------------------------------------------------------------");
    
                while (rs.next()) {
                    hasReservations = true;
                    int reservationId = rs.getInt("reservation_id");
                    String uniqueId = rs.getString("unique_id");
                    String reservationDate = rs.getString("reservation_date");
                    String status = rs.getString("status");
                    String startTime = rs.getString("start_time");
                    String endTime = rs.getString("end_time");
    
                    System.out.printf("%-10d %-15s %-15s %-15s %-15s %-15s\n", reservationId, uniqueId, reservationDate, status, startTime, endTime);
                }
                System.out.println("-------------------------------------------------------------------------------------");
    
                if (!hasReservations) {
                    System.out.println("No reservations found for this facility.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
    
        System.out.println("\nPress any key to go back to the facility list or type 'menu' to return to the user menu...");
        String input = scanner.nextLine();
    
        if (input.equalsIgnoreCase("menu")) {
            return;
        }
        viewAllReservations(scanner);
    }
        
    @SuppressWarnings("CallToPrintStackTrace")
    public static void modifyReservationStatus(Scanner scanner) {
        System.out.println("\n*************************************************************************************");
        System.out.println("                              MODIFY RESERVATION STATUS                              ");
        System.out.println("*************************************************************************************");
    
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter the Reservation ID to modify: ");
            int reservationId = scanner.nextInt();
            scanner.nextLine();
    
            String checkSql = "SELECT reservation_id, status, reservation_date FROM reservations WHERE reservation_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, reservationId);
                ResultSet rs = checkStmt.executeQuery();
    
                if (!rs.next()) {
                    System.out.println("\nReservation ID not found. Please try again with a valid ID.");
                    return;
                }
    
                String currentStatus = rs.getString("status");
                String reservationDate = rs.getString("reservation_date");
    
                System.out.println("Current Status: " + currentStatus);
    
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date reservationDateObj = sdf.parse(reservationDate);
                Date currentDate = new Date();
    
                if (reservationDateObj.before(currentDate)) {
                    System.out.println("\nThis reservation's status cannot be modified because it has already passed.");
                    return;
                }
    
                String newStatus;
                while (true) {
                    System.out.print("\nEnter new status ('confirmed' or 'cancelled'): ");
                    newStatus = scanner.nextLine().trim().toLowerCase();
                    if (newStatus.equals("confirmed") || newStatus.equals("cancelled")) {
                        break;
                    }
                    System.out.println("\nInvalid status. Please enter 'confirmed' or 'cancelled'.");
                }
    
                String updateSql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setString(1, newStatus);
                    updateStmt.setInt(2, reservationId);
    
                    int rowsUpdated = updateStmt.executeUpdate();
    
                    if (rowsUpdated > 0) {
                        System.out.println("\nReservation status updated successfully.");
                    } else {
                        System.out.println("\nFailed to update reservation status. Please try again.");
                    }
                }
            }
    
        } catch (SQLException | java.text.ParseException e) {
            e.printStackTrace();
            System.out.println("An error occurred while modifying the reservation status.");
        }
    }  
}