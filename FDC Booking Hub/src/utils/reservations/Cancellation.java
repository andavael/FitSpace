package utils.reservations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import utils.database.DBConnection;


public class Cancellation {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void cancelReservation(int userId, Scanner scanner) {
        int reservationId;
    
        // Loop until the user enters a valid reservation ID
        while (true) {
            System.out.print("\nEnter the Reservation ID you want to cancel: ");
            try {
                reservationId = scanner.nextInt();
    
                if (reservationId <= 0) {
                    System.out.println("Invalid Reservation ID. Please try again.");
                    continue;  // Continue asking for a valid ID
                }
    
                // Proceed with the reservation check
                try (Connection conn = DBConnection.getConnection()) {
                    String sql = "SELECT reservation_date, status FROM reservations WHERE user_id = ? AND reservation_id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setInt(1, userId);
                        stmt.setInt(2, reservationId);
                        ResultSet rs = stmt.executeQuery();
    
                        if (rs.next()) {
                            String reservationDate = rs.getString("reservation_date");
                            String currentStatus = rs.getString("status");
                            LocalDate reservationDateParsed = LocalDate.parse(reservationDate); // Parse the reservation date
                            LocalDate currentDate = LocalDate.now(); // Get the current date
    
                            // Check if the reservation date is in the future or today
                            if ((reservationDateParsed.isAfter(currentDate) || reservationDateParsed.isEqual(currentDate)) 
                                    && !currentStatus.equals("cancelled")) {
                                // Proceed to cancel the reservation if the date is valid (not in the past)
                                cancelReservationFromDatabase(userId, reservationId);
                            } else {
                                // If the reservation is in the past, notify the user
                                System.out.println("This reservation has already passed and cannot be cancelled.");
                            }
                        } else {
                            // If the reservation ID is invalid
                            System.out.println("Invalid Reservation ID. Please try again.");
                            continue;  // Continue asking for a valid Reservation ID
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    
                break;  // Exit the loop if the reservation ID is valid and the process completes
    
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric Reservation ID.");
                scanner.next(); // Clear the invalid input
            }
        }
    }    

    // The method to cancel the reservation in the database (change status to 'cancelled')
    @SuppressWarnings("CallToPrintStackTrace")
    private static void cancelReservationFromDatabase(int userId, int reservationId) {
        try (Connection conn = DBConnection.getConnection()) {
            // SQL to update the reservation status to 'cancelled'
            String sql = "UPDATE reservations SET status = 'cancelled' WHERE user_id = ? AND reservation_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, reservationId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Reservation ID " + reservationId + " has been cancelled.");
                } else {
                    System.out.println("No reservation found to cancel or the reservation has already been cancelled.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

