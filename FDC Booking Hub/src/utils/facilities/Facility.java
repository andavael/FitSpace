package utils.facilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.database.DBConnection;

public class Facility {
    private final int facilityID;
    private final String name;
    private final String status;

    public Facility(int facilityID, String name, String status) {
        this.facilityID = facilityID;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return facilityID;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static List<Facility> getAllFacilities() {
        List<Facility> facilities = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT facility_id, name, status FROM facilities";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    int facilityId = rs.getInt("facility_id");
                    String name = rs.getString("name");
                    String status = rs.getString("status");
                    facilities.add(new Facility(facilityId, name, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilities;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private List<String> getAllTimeSlots() {
        List<String> timeSlots = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT start_time, end_time FROM time_slots";
            try (PreparedStatement pstmt = conn.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String startTime = rs.getTime("start_time").toString();
                    String endTime = rs.getTime("end_time").toString();
                    timeSlots.add(startTime + " - " + endTime);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeSlots;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private List<String> getReservedTimes(String date) {
        List<String> reservedTimes = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT ts.start_time, ts.end_time FROM reservations r " +
                         "JOIN time_slots ts ON r.slot_id = ts.slot_id " +
                         "WHERE r.facility_id = ? AND r.reservation_date = ? AND r.status = 'confirmed'";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, this.facilityID);
                pstmt.setString(2, date);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String startTime = rs.getTime("start_time").toString();
                        String endTime = rs.getTime("end_time").toString();
                        reservedTimes.add(startTime + " - " + endTime);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservedTimes;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static int getSlotIdFromTime(String time) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT slot_id FROM time_slots WHERE start_time = ? AND end_time = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, time.split(" - ")[0]);
                pstmt.setString(2, time.split(" - ")[1]);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("slot_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> displayAvailableSlots(String date) {
        List<String> reservedTimes = getReservedTimes(date);
        List<String> allTimeSlots = getAllTimeSlots();
        List<String> availableSlots = new ArrayList<>();
    
        for (String timeSlot : allTimeSlots) {
            if (!reservedTimes.contains(timeSlot)) {
                availableSlots.add(timeSlot);
            }
        }
    
        if (availableSlots.isEmpty()) {
            return availableSlots;
        }
    
        System.out.println("\n***********************************************************************************");
        System.out.println("  Available time slots for " + name + " on " + date + ":");
        System.out.println("***********************************************************************************");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }
        return availableSlots;
    }
}
