package EmployeeManagementSystem.Model;

import java.util.ArrayList;
import java.util.List;

public class ManagerDashModel {
    private List<Employee> employeeList;

    public ManagerDashModel() {
        this.employeeList = new ArrayList<>();
    }

    public List<Employee> getEmployeeList() { return employeeList; }
    public void setEmployeeList(List<Employee> list) { this.employeeList = list; }

    // Inner class for Employee Data
    public static class Employee {
        private String id, name;
        private double salary;

        public Employee(String id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getSalaryFormatted() { return String.format("Rs. %,.2f", salary); }
    }
}
