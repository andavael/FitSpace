package utils.users;

import java.sql.*;
import java.util.Scanner;
import utils.database.DBConnection;

public abstract class User {
    protected int userId;
    protected String uniqueId, password, firstName, middleName, lastName, department, gsuiteAccount;

    public User(int userId, String uniqueId, String password, String firstName, String middleName, String lastName, String department, String gsuiteAccount) {
        this.userId = userId;
        this.uniqueId = uniqueId;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.department = department;
        this.gsuiteAccount = gsuiteAccount;  
    }

    protected boolean isUserExists(String uniqueId, String userType) {
        String sql = "SELECT COUNT(*) FROM users WHERE unique_id = ? AND user_type = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uniqueId);
            stmt.setString(2, userType);
            ResultSet resultSet = stmt.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println("Error checking user existence: " + e.getMessage());
        }
        return false;
    }

    protected boolean insertUserData(String uniqueId, String password, String firstName, String middleName, String lastName, String department, String userType, String gsuiteAccount) {
        String sql = "INSERT INTO users (unique_id, password, first_name, middle_name, last_name, department, gsuite_account, user_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uniqueId);
            stmt.setString(2, password);
            stmt.setString(3, firstName);
            stmt.setString(4, middleName);
            stmt.setString(5, lastName);
            stmt.setString(6, department);
            stmt.setString(7, gsuiteAccount);  
            stmt.setString(8, userType);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting data: " + e.getMessage());
        }
        return false;
    }

    public void commonRegistration(Scanner input, String userType) {
        System.out.print("Enter your first name: ");
        this.firstName = input.nextLine();
        System.out.print("Enter your middle name(Optional): ");
        this.middleName = input.nextLine();
        System.out.print("Enter your last name: ");
        this.lastName = input.nextLine();
        System.out.print("Enter your department: ");
        this.department = input.nextLine();
        System.out.print("Enter your GSuite account: ");
        this.gsuiteAccount = input.nextLine(); 

        while (true) {
            System.out.print("Enter your password (at least 8 characters): ");
            this.password = input.nextLine(); 
            if (this.password.length() >= 8) {
                break;
            } else {
                System.out.println("\nPassword must be at least 8 characters long.");
            }
        }

        boolean isRegistered = insertUserData(this.uniqueId, this.password, this.firstName, this.middleName, this.lastName, this.department, userType, this.gsuiteAccount);
        if (isRegistered) {
            System.out.println("\nRegistration successful.");
        } else {
            System.out.println("\nRegistration failed. Please try again.");
        }
    }

   
    public boolean commonLogin(Scanner input, String userType) {
        System.out.print("Enter your password: ");
        String enteredPassword = input.nextLine().trim();

        String query = "SELECT user_id, first_name, last_name, password FROM users WHERE unique_id = ? AND user_type = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.uniqueId);
            stmt.setString(2, userType);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");

                this.firstName = rs.getString("first_name");

                if (enteredPassword.equalsIgnoreCase(storedPassword.trim())) {
                    this.userId = rs.getInt("user_id");
                    this.password = storedPassword; 
                    return true;
                } else {
                    System.out.println("\nIncorrect password.");
                    return false;
                }
            } else {
                System.out.println("\nUser not found. Please register first.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            return false;
        }
    }
    
public void displayMenuHeader() {
    System.out.println("\n*************************************************************************************");
    System.out.println("LOGGED IN AS " + firstName.toUpperCase());
    System.out.println("*************************************************************************************");
}
    
    public abstract void displayUserMenu(Scanner input, int userId);
    public abstract void registerUser(Scanner input); 
    public abstract void loginUser(Scanner input);   
}
