package EmployeeManagementSystem.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EmployeeDashView {
    public JPanel mainPanel;
    private JLabel lblWelcome, lblEmpName, lblRole, lblEmail, lblSalary, lblEmpID, lblProfilePic;
    private JButton btnEditProfile, btnLogout;

    public EmployeeDashView() {
        // Main Container setup
        mainPanel = new JPanel(new BorderLayout(25, 0));
        mainPanel.setBackground(new Color(245, 247, 251));
        mainPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        // --- LEFT SIDE: PERSONAL INFO CARD ---
        JPanel leftCard = new JPanel();
        leftCard.setLayout(new BoxLayout(leftCard, BoxLayout.Y_AXIS));
        leftCard.setBackground(Color.WHITE);
        leftCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        JLabel title = new JLabel("My Profile Details");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        lblEmpID = new JLabel("Employee ID: ");
        lblEmail = new JLabel("Email: ");
        lblSalary = new JLabel("Current Salary: Rs. 0.00");

        // Styling labels
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        lblEmpID.setFont(labelFont);
        lblEmail.setFont(labelFont);
        lblSalary.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSalary.setForeground(new Color(34, 197, 94)); // Green color for salary

        btnEditProfile = new JButton("Edit My Details");
        styleButton(btnEditProfile, true);

        leftCard.add(title);
        leftCard.add(Box.createRigidArea(new Dimension(0, 30)));
        leftCard.add(lblEmpID);
        leftCard.add(Box.createRigidArea(new Dimension(0, 15)));
        leftCard.add(lblEmail);
        leftCard.add(Box.createRigidArea(new Dimension(0, 15)));
        leftCard.add(lblSalary);
        leftCard.add(Box.createVerticalGlue());
        leftCard.add(btnEditProfile);

        JPanel rightCard = new JPanel();
        rightCard.setLayout(new BoxLayout(rightCard, BoxLayout.Y_AXIS));
        rightCard.setPreferredSize(new Dimension(300, 0));
        rightCard.setBackground(Color.WHITE);
        rightCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(40, 25, 40, 25)
        ));

        lblWelcome = new JLabel("Logged in as:");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblWelcome.setForeground(Color.GRAY);
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblEmpName = new JLabel("Employee Name");
        lblEmpName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblEmpName.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblProfilePic = new JLabel("Upload Photo");
        lblProfilePic.setPreferredSize(new Dimension(150, 150));
        lblProfilePic.setMaximumSize(new Dimension(150, 150));
        lblProfilePic.setBorder(BorderFactory.createDashedBorder(Color.LIGHT_GRAY, 5, 5));
        lblProfilePic.setHorizontalAlignment(JLabel.CENTER);
        lblProfilePic.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblRole = new JLabel("Staff Member");
        lblRole.setForeground(Color.GRAY);
        lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnLogout = new JButton("LOG OUT");
        btnLogout.setMaximumSize(new Dimension(220, 45));
        btnLogout.setBackground(new Color(239, 68, 68));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setFocusPainted(false);
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightCard.add(lblWelcome);
        rightCard.add(lblEmpName);
        rightCard.add(Box.createRigidArea(new Dimension(0, 25)));
        rightCard.add(lblProfilePic);
        rightCard.add(Box.createRigidArea(new Dimension(0, 15)));
        rightCard.add(lblRole);
        rightCard.add(Box.createVerticalGlue());
        rightCard.add(btnLogout);

        mainPanel.add(leftCard, BorderLayout.CENTER);
        mainPanel.add(rightCard, BorderLayout.EAST);
    }

    private void styleButton(JButton b, boolean primary) {
        b.setPreferredSize(new Dimension(200, 45));
        b.setMaximumSize(new Dimension(200, 45));
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        if(primary) {
            b.setBackground(new Color(37, 99, 235));
            b.setForeground(Color.WHITE);
        }
    }

    // Getters for the Controller
    public JLabel getLblEmpName() { return lblEmpName; }
    public JLabel getLblEmail() { return lblEmail; }
    public JLabel getLblSalary() { return lblSalary; }
    public JLabel getLblEmpID() { return lblEmpID; }
    public JLabel getLblProfilePic() { return lblProfilePic; }
    public JButton getBtnEditProfile() { return btnEditProfile; }
    public JButton getBtnLogout() { return btnLogout; }
}


