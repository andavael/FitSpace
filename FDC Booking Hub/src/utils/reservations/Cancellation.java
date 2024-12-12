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
    
        while (true) {
            System.out.print("\nEnter the Reservation ID you want to cancel: ");
            try {
                reservationId = scanner.nextInt();
    
                if (reservationId <= 0) {
                    System.out.println("Invalid Reservation ID. Please try again.");
                    continue;  
                }
    
                try (Connection conn = DBConnection.getConnection()) {
                    String sql = "SELECT reservation_date, status FROM reservations WHERE user_id = ? AND reservation_id = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setInt(1, userId);
                        stmt.setInt(2, reservationId);
                        ResultSet rs = stmt.executeQuery();
    
                        if (rs.next()) {
                            String reservationDate = rs.getString("reservation_date");
                            String currentStatus = rs.getString("status");
                            LocalDate reservationDateParsed = LocalDate.parse(reservationDate); 
                            LocalDate currentDate = LocalDate.now(); 
    
                            
                            if ((reservationDateParsed.isAfter(currentDate) || reservationDateParsed.isEqual(currentDate)) 
                                    && !currentStatus.equals("cancelled")) {
                                cancelReservationFromDatabase(userId, reservationId);
                            } else {
                            
                                System.out.println("This reservation has already passed and cannot be cancelled.");
                            }
                        } else {
                            System.out.println("Invalid Reservation ID. Please try again.");
                            continue;  
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
    
                break;  
    
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric Reservation ID.");
                scanner.next(); 
            }
        }
    }    

    
    private static void cancelReservationFromDatabase(int userId, int reservationId) {
        try (Connection conn = DBConnection.getConnection()) {
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

