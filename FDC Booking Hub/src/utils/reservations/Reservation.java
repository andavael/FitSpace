package utils.reservations;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import utils.facilities.Facility;
import utils.facilities.FacilityManager;

public class Reservation {
    public static void makeReservation(int userId, Scanner scanner) {
        FacilityManager.displayFacilities(); 
        System.out.print("\nSelect a facility to reserve: ");
    
        
        List<Facility> facilities = Facility.getAllFacilities();
    
        int facilityChoice = -1; 
        while (facilityChoice < 1 || facilityChoice > facilities.size()) {
            if (scanner.hasNextInt()) {
                facilityChoice = scanner.nextInt();
                scanner.nextLine(); 
                
                if (facilityChoice < 1 || facilityChoice > facilities.size()) {
                    System.out.print("\nInvalid choice. Please select a valid facility number between 1 and " + facilities.size() + ": ");
                }
            } else {
                System.out.println("\nInvalid input. Please enter a number between 1 and " + facilities.size() + ": ");
                scanner.nextLine(); 
            }
        }

        Facility selectedFacility = facilities.get(facilityChoice - 1); 
    
        if (!selectedFacility.getStatus().equalsIgnoreCase("active")) {
            System.out.println("This facility is not active. You cannot make a reservation for it.");
            return;  
        }
    
        String date;
        while (true) {
            System.out.print("Enter the desired date (YYYY-MM-DD): ");
            date = scanner.nextLine();
    
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                try {
                    LocalDate inputDate = LocalDate.parse(date); 
    
                    LocalDate currentDate = LocalDate.now();
                    if (!inputDate.isBefore(currentDate)) {
                        break; 
                    } else {
                        System.out.println("The selected date has already passed. Please enter a future date.\n");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date. Please ensure the date is valid.\n");
                }
            } else {
                System.out.println("Invalid date format. Please enter the date in the format YYYY-MM-DD.\n");
            }
        }
    
        List<String> availableSlots = selectedFacility.displayAvailableSlots(date);
        if (availableSlots.isEmpty()) {
            System.out.println("No available slots for this facility on the selected date.");
            return; 
        }
    
        int slotChoice = -1;
    
        while (true) {
            System.out.print("\nSelect a time slot (enter the number): ");
    
            if (scanner.hasNextInt()) {
                slotChoice = scanner.nextInt();
                scanner.nextLine();
    
                if (slotChoice >= 1 && slotChoice <= availableSlots.size()) {
                    break; 
                } else {
                    System.out.println("Invalid choice. Please select a slot number between 1 and " + availableSlots.size() + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid slot number.");
                scanner.nextLine(); 
            }
        }
    
        String timeSlot = availableSlots.get(slotChoice - 1);
    
        int slotId = Facility.getSlotIdFromTime(timeSlot);
    
        ReservationManager.saveReservation(userId, selectedFacility, date, timeSlot, slotId);
    }    
}
