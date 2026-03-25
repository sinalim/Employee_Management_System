package EmployeeManagementSystem.Model;

public class LoginModel {
    private String username;
    private String password;
    private String role;
    private String department;
    private boolean rememberMe;

    public LoginModel() {}

    public LoginModel(String username, String password, String role, String department) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.department = department;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public boolean isRememberMe() { return rememberMe; }
    public void setRememberMe(boolean rememberMe) { this.rememberMe = rememberMe; }
}

