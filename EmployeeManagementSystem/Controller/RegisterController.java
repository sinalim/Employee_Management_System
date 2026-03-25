package EmployeeManagementSystem.Controller;

import EmployeeManagementSystem.Model.*;
import EmployeeManagementSystem.View.*;
import EmployeeManagementSystemDB.EmpDB;
import java.sql.*;
import javax.swing.*;

public class RegisterController {
    private RegisterView view;

    public RegisterController(RegisterView view) {
        this.view = view;

        // Register Button Listener
        this.view.getBtnRegister().addActionListener(e -> handleRegistration());

        // Back Button Listener
        this.view.getBtnBack().addActionListener(e -> {
            LoginView lv = new LoginView();
            new LoginController(lv); // FIXED: Removed LoginModel
            switchScreen(lv.mainPanel);
        });
    }

    private void handleRegistration() {
        String empId = view.getTxtEmpId().getText().trim();
        String name = view.getTxtName().getText().trim();
        String email = view.getTxtEmail().getText().trim();
        String pass = new String(view.getTxtPassword().getPassword());
        String role = view.getComboRole().getSelectedItem().toString();

        //  Validation
        if (empId.isEmpty() || name.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(view.mainPanel, "All fields are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Database Operation
        String sql = "INSERT INTO Employee (Emp_ID, Emp_Name, Emp_Email, `Password`, Role, Dep_ID, P_Id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = EmpDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empId);
            pstmt.setString(2, name);
            pstmt.setString(3, email);
            pstmt.setString(4, pass);
            pstmt.setString(5, role);

            // Map Dept ID
            int depId = 1; // Default to HR
            if(role.equals("Manager")) depId = 2;

            pstmt.setInt(6, depId);
            pstmt.setInt(7, 1); // Default Payroll ID (P_Id)

            if (pstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(view.mainPanel, "Registration Successful!");

                // Return to Login
                LoginView lv = new LoginView();
                new LoginController(lv); // FIXED: Removed LoginModel
                switchScreen(lv.mainPanel);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view.mainPanel, "SQL Error: " + ex.getMessage());
        }
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

