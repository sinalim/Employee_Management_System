package EmployeeManagementSystem.Controller;

import EmployeeManagementSystem.Model.*;
import EmployeeManagementSystem.View.*;
import EmployeeManagementSystemDB.EmpDB;
import java.sql.*;
import javax.swing.*;

public class LoginController {
    private LoginView view;

    public LoginController(LoginView view) {
        this.view = view;
        this.view.getBtnLogin().addActionListener(e -> handleLogin());

        this.view.getBtnBack().addActionListener(e -> {
            JOptionPane.showMessageDialog(view.mainPanel, "Back button clicked");
        });
    }

    private void handleLogin() {
        String email = view.getTxtUsername().getText().trim();
        String pass = new String(view.getTxtPassword().getPassword());
        String selectedRole = view.getComboRole().getSelectedItem().toString();

        if (email.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(view.mainPanel, "Please enter all credentials");
            return;
        }

        // query to fetch employee and admin details
        String sql = "SELECT e.Emp_ID AS ID, e.Emp_Name AS Name, e.Emp_Email AS Email, e.Role, p.Amount AS Salary " +
                "FROM Employee e LEFT JOIN Payroll p ON e.P_Id = p.P_Id " +
                "WHERE e.Emp_Email = ? AND e.Password = ? AND e.Role = ? " +
                "UNION " +
                "SELECT Admin_ID AS ID, Admin_Name AS Name, Admin_Email AS Email, 'Admin' AS Role, 0.0 AS Salary " +
                "FROM Admin " +
                "WHERE Admin_Email = ? AND Password = ? AND 'Admin' = ?";

        try (Connection conn = EmpDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, pass);
            pstmt.setString(3, selectedRole);

            pstmt.setString(4, email);
            pstmt.setString(5, pass);
            pstmt.setString(6, selectedRole);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String id = rs.getString("ID");
                String name = rs.getString("Name");
                String role = rs.getString("Role");
                String mail = rs.getString("Email");
                double salary = rs.getDouble("Salary");

                // Check Role and Redirect
                if (role.equalsIgnoreCase("Admin")) {
                    openAdminDashboard(name);
                } else if (role.equalsIgnoreCase("Manager")) {
                    openManagerDashboard(name);
                } else if (role.equalsIgnoreCase("Staff") || role.equalsIgnoreCase("Employee")) {
                    // Initialize EmployeeDashModel and open the Dashboard
                    EmployeeDashModel empModel = new EmployeeDashModel(id, name, mail, role, salary, "");
                    openEmployeeDashboard(empModel);
                }
            } else {
                JOptionPane.showMessageDialog(view.mainPanel, "Invalid Credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view.mainPanel, "Database Error: " + ex.getMessage());
        }
    }

    private void openEmployeeDashboard(EmployeeDashModel model) {
        EmployeeDashView empView = new EmployeeDashView();
        new EmployeeDashController(empView, model);
        switchScreen(empView.mainPanel);
    }

    private void openManagerDashboard(String managerName) {
        ManagerDashView dashView = new ManagerDashView();
        new ManagerDashController(dashView, managerName);
        switchScreen(dashView.mainPanel);
    }

    private void openAdminDashboard(String adminName) {
        AdminDashView adminView = new AdminDashView();
        new AdminDashController(adminView, adminName);
        switchScreen(adminView.mainPanel);
    }

    private void switchScreen(JPanel panel) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view.mainPanel);
        if (frame != null) {
            frame.setContentPane(panel);
            frame.revalidate();
            frame.repaint();
        }
    }
}
