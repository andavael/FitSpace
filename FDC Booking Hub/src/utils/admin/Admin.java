package utils.admin;

public class Admin {
    private final String adminID;
    private final String adminPass;
    
    public Admin(String adminID, String adminPass) {
        this.adminID = adminID;
        this.adminPass = adminPass;
    }

    public String getAdminID() {
        return adminID;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public boolean validateCredentials(String userID, String password) {
        return this.adminID.equals(userID) && this.adminPass.equals(password);
    }
}
