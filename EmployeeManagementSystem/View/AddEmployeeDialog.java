package EmployeeManagementSystem.View;

import EmployeeManagementSystemDB.EmpDB; // Imports your Database Connection class
import javax.swing.*;
import java.awt.*;
import java.sql.*; // Required for SQL operations

public class AddEmployeeDialog extends JDialog {
    // UI Components for the popup
    private JTextField txtId, txtName, txtEmail;
    private JComboBox<String> cbRole, cbDept;
    private JButton btnSave, btnCancel;

    public AddEmployeeDialog(JFrame parent) {
        // 'true' makes this a Modal window (locks the background Dashboard)
        super(parent, "Add New Staff Member", true);

        // --- 1. UI LAYOUT SETUP ---
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initialize Form Fields
        txtId = new JTextField(15);
        txtName = new JTextField(15);
        txtEmail = new JTextField(15);
        cbDept = new JComboBox<>(new String[]{"Human Resources", "Information Technology", "Finance", "Marketing"});
        cbRole = new JComboBox<>(new String[]{"Employee", "Manager"});

        // Add Labels and Fields to the Popup
        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1; add(txtId, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; add(cbDept, gbc);

        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Role:"), gbc);
        gbc.gridx = 1; add(cbRole, gbc);

        //buttons
        btnSave = new JButton("Save Employee");
        btnSave.setBackground(new Color(52, 116, 225)); // Professional Blue
        btnSave.setForeground(Color.WHITE);
        btnCancel = new JButton("Cancel");

        JPanel pnlBtns = new JPanel();
        pnlBtns.add(btnSave);
        pnlBtns.add(btnCancel);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(pnlBtns, gbc);

        btnSave.addActionListener(e -> {
            if (saveToDatabase()) {
                dispose(); // Closes popup only if DB insert succeeds
            }
        });

        btnCancel.addActionListener(e -> dispose()); // Closes without saving

        pack(); // Auto-size window
        setLocationRelativeTo(parent); // Centers the popup on the Dashboard
    }

    // database insert
    private boolean saveToDatabase() {
        // Basic Validation (LO10)
        if(txtId.getText().trim().isEmpty() || txtName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ID and Name are required!");
            return false;
        }

        // SQL Query
        String sql = "INSERT INTO Employee (Emp_ID, Emp_Name, Emp_Email, `Password`, Role, Dep_ID, P_Id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = EmpDB.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, txtId.getText().trim());
            pst.setString(2, txtName.getText().trim());
            pst.setString(3, txtEmail.getText().trim());
            pst.setString(4, "12345"); // Set a default password for new staff
            pst.setString(5, cbRole.getSelectedItem().toString());

            // Map Dept selection (Index 0,1,2,3) to DB IDs (1,2,3,4)
            pst.setInt(6, cbDept.getSelectedIndex() + 1);
            pst.setInt(7, 1); // Default Payroll (Junior)

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "New Employee Saved to Database!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            return false;
        }
    }
}

