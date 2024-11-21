package utils.facilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import utils.database.DBConnection;

public class FacilityManager {
    public static void facilityManagerMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n***********************************************************************************");
            System.out.println("                                FACILITY MANAGER                                   ");
            System.out.println("***********************************************************************************");
            System.out.println("1. Add a new Facility");
            System.out.println("2. Change Status of Existing Facilities");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice number: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addFacility(scanner); 
                        break;
                    case 2:
                        changeFacilityStatus(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting Facility Manager...");
                        return; 
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                        facilityManagerMenu(scanner); 
                        return; 
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input. Please enter a number between 1 and 3.");
                scanner.next();
            }
        }
    }


    public static void displayFacilities() {
        System.out.println("\n*************************************************************************************");
        System.out.println("                                 FITSPACE FACILITIES                                 ");
        System.out.println("*************************************************************************************");
        List<Facility> facilities = Facility.getAllFacilities();
        for (int i = 0; i < facilities.size(); i++) {
            Facility facility = facilities.get(i);
            System.out.println((i + 1) + ". " + facility.getName() + " (Status: " + facility.getStatus() + ")");
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void addFacility(Scanner scanner) {
        String name;
    
        while (true) {
            System.out.print("\nEnter the facility name: ");
            name = scanner.nextLine().trim();
    
            String checkSql = "SELECT facility_id FROM facilities WHERE name = ?";
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
    
                checkStmt.setString(1, name);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println(name + " already exists. Please add a different facility.");
                    } else {
                        break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        }
    
        String status;
        while (true) {
            System.out.print("Enter the facility status (active/inactive): ");
            status = scanner.nextLine().trim().toLowerCase();
            if (status.equals("active") || status.equals("inactive")) {
                break;
            } else {
                System.out.println("Invalid status. Please enter 'active' or 'inactive'.");
            }
        }
    
        String insertSql = "INSERT INTO facilities (name, status) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
    
            insertStmt.setString(1, name);
            insertStmt.setString(2, status);
    
            int rowsInserted = insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\n" + name + " added successfully!");
                displayFacilities();
            } else {
                System.out.println("Failed to add the facility.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    @SuppressWarnings("CallToPrintStackTrace")
    public static void changeFacilityStatus(Scanner scanner) {
        List<Facility> facilities = Facility.getAllFacilities();
    
        if (facilities.isEmpty()) {
            System.out.println("No facilities available to change status.");
            return;
        }
    
        displayFacilities();
    
        int choice;
        while (true) {
            System.out.print("\nEnter the number of the facility you want to change the status: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 
                if (choice >= 1 && choice <= facilities.size()) {
                    break;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("Invalid choice. Please enter a number between 1 and " + facilities.size() + ".");
        }
    
        Facility selectedFacility = facilities.get(choice - 1);
    
        String newStatus;
        while (true) {
            System.out.print("Enter the new status (active/inactive): ");
            newStatus = scanner.nextLine().trim().toLowerCase();
            if (newStatus.equals("active") || newStatus.equals("inactive")) {
                break;
            } else {
                System.out.println("Invalid status. Please enter 'active' or 'inactive'.");
            }
        }
    
        String updateSql = "UPDATE facilities SET status = ? WHERE facility_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
    
            updateStmt.setString(1, newStatus);
            updateStmt.setInt(2, selectedFacility.getId());
    
            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("\nThe status of " + selectedFacility.getName() + " has been updated to " + newStatus + ".");
                displayFacilities();
            } else {
                System.out.println("Failed to update the status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}

