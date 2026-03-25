package EmployeeManagementSystem.View;

import javax.swing.*;
import java.awt.*;

public class RegisterView {
    public JPanel mainPanel;
    private JTextField txtEmpId, txtName, txtEmail;
    private JPasswordField txtPassword;
    private JComboBox<String> comboDept, comboRole; // Added comboRole
    private JButton btnRegister, btnBack;

    public RegisterView() {
        mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon img = new ImageIcon(getClass().getResource("/EmployeeManagementSystem/bg.jpg"));
                    g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    g.setColor(new Color(28, 35, 65));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setOpaque(true);
        card.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel lblTitle = new JLabel("Employee Registration", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(44, 62, 80));
        gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        card.add(lblTitle, gbc);

        // Init Components
        txtEmpId = (JTextField) styleField(new JTextField());
        txtName = (JTextField) styleField(new JTextField());
        txtEmail = (JTextField) styleField(new JTextField());
        txtPassword = (JPasswordField) styleField(new JPasswordField());
        comboDept = (JComboBox<String>) styleField(new JComboBox<>(new String[]{"IT", "HR", "Sales", "Finance"}));
        comboRole = (JComboBox<String>) styleField(new JComboBox<>(new String[]{"Admin", "Manager", "Employee"})); // New Field

        // Add Rows
        int row = 1;
        addFormRow("Employee ID", txtEmpId, row++, gbc, card);
        addFormRow("Full Name", txtName, row++, gbc, card);
        addFormRow("Email Address", txtEmail, row++, gbc, card);
        addFormRow("Password", txtPassword, row++, gbc, card);
        addFormRow("Department", comboDept, row++, gbc, card);
        addFormRow("User Role", comboRole, row++, gbc, card); // Add Role Row

        // Buttons
        btnBack = new JButton("Back");
        btnRegister = new JButton("Create Account");
        styleButton(btnBack, false);
        styleButton(btnRegister, true);

        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        btnPanel.setOpaque(false);
        btnPanel.add(btnBack);
        btnPanel.add(btnRegister);

        gbc.gridy = row * 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        card.add(btnPanel, gbc);

        mainPanel.add(card);
    }

    private JComponent styleField(JComponent c) {
        c.setPreferredSize(new Dimension(320, 35));
        c.setBackground(Color.WHITE);
        c.setForeground(new Color(120, 120, 120));
        c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return c;
    }

    private void addFormRow(String labelText, JComponent field, int row, GridBagConstraints gbc, JPanel card) {
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
        lbl.setForeground( Color.BLACK);
        gbc.gridy = row * 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 2, 0);
        card.add(lbl, gbc);
        gbc.gridy = (row * 2) + 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        card.add(field, gbc);
    }

    private void styleButton(JButton b, boolean isPrimary) {
        b.setPreferredSize(new Dimension(150, 40));
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        if (isPrimary) {
            b.setBackground(new Color(52, 116, 225));
            b.setForeground(Color.WHITE);
        } else {
            b.setBackground(new Color(235, 237, 240));
            b.setForeground(new Color(100, 100, 100));
        }
    }

    // Updated Getters
    public JTextField getTxtEmpId() { return txtEmpId; }
    public JTextField getTxtName() { return txtName; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JComboBox<String> getComboRole() { return comboRole; } // Needed for Controller
    public JButton getBtnRegister() { return btnRegister; }
    public JButton getBtnBack() { return btnBack; }
}
