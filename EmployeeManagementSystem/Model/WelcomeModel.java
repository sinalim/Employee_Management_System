package EmployeeManagementSystem.Model;

public class WelcomeModel {

    private String systemName;
    private String version;


    public WelcomeModel() {
        this.systemName = "Employee Management System";
        this.version = "v1.0.0";
    }

    public String getSystemName() {
        return systemName;
    }

    public String getVersion() {
        return version;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}

