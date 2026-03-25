package EmployeeManagementSystem.Model;


public class EmployeeDashModel {
    private String empId;
    private String name;
    private String email;
    private String role; // Typically 'Staff' or 'Employee'
    private double salary;
    private String profilePicPath;

    public EmployeeDashModel(String empId, String name, String email, String role, double salary, String profilePicPath) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.salary = salary;
        this.profilePicPath = profilePicPath;
    }

    // Getters and Setters
    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getProfilePicPath() { return profilePicPath; }
    public void setProfilePicPath(String profilePicPath) { this.profilePicPath = profilePicPath; }
}
