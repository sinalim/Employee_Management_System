package EmployeeManagementSystem.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminDashView {
    public JPanel mainPanel;
    private JLabel lblWelcome, lblAdminName, lblRole, lblProfilePic;
    private JTable tblManagers;
    private DefaultTableModel tableModel;
    private JButton btnAddManager, btnDelete, btnLogout, btnReport;

    public AdminDashView() {
        mainPanel = new JPanel(new BorderLayout(25, 0));
        mainPanel.setBackground(new Color(245, 247, 251));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));


        JPanel leftCard = new JPanel(new BorderLayout(0, 15));
        leftCard.setBackground(Color.WHITE);
        leftCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel tblTitle = new JLabel("Manager Directory");
        tblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        leftCard.add(tblTitle, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Full Name", "Email"}, 0);
        tblManagers = new JTable(tableModel);
        tblManagers.setRowHeight(35);
        leftCard.add(new JScrollPane(tblManagers), BorderLayout.CENTER);


        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        btnPanel.setOpaque(false);

        btnDelete = new JButton("Delete Manager");
        btnAddManager = new JButton("+ Add Manager");
        btnReport = new JButton("Generate Report");

        styleButton(btnDelete, false);
        styleButton(btnAddManager, true);
        styleButton(btnReport, true);

        // Buttons stay in one row and be visible
        btnPanel.add(btnReport);
        btnPanel.add(btnDelete);
        btnPanel.add(btnAddManager);
        leftCard.add(btnPanel, BorderLayout.SOUTH);

        // admin profile
        JPanel rightCard = new JPanel();
        rightCard.setLayout(new BoxLayout(rightCard, BoxLayout.Y_AXIS));
        rightCard.setPreferredSize(new Dimension(280, 0));
        rightCard.setBackground(Color.WHITE);
        rightCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(40, 25, 40, 25)
        ));

        lblWelcome = new JLabel("Welcome,");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblWelcome.setForeground(Color.GRAY);
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblAdminName = new JLabel("Administrator");
        lblAdminName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblAdminName.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblProfilePic = new JLabel("Upload Photo");
        lblProfilePic.setPreferredSize(new Dimension(130, 130));
        lblProfilePic.setMaximumSize(new Dimension(130, 130));
        lblProfilePic.setBorder(BorderFactory.createDashedBorder(Color.LIGHT_GRAY, 5, 5));
        lblProfilePic.setHorizontalAlignment(JLabel.CENTER);
        lblProfilePic.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblRole = new JLabel("System Admin");
        lblRole.setForeground(Color.GRAY);
        lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnLogout = new JButton("LOG OUT");
        btnLogout.setMaximumSize(new Dimension(220, 50));
        btnLogout.setBackground(new Color(239, 68, 68));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setFocusPainted(false);
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightCard.add(lblWelcome);
        rightCard.add(lblAdminName);
        rightCard.add(Box.createRigidArea(new Dimension(0, 20)));
        rightCard.add(lblProfilePic);
        rightCard.add(Box.createRigidArea(new Dimension(0, 15)));
        rightCard.add(lblRole);
        rightCard.add(Box.createVerticalGlue());
        rightCard.add(btnLogout);

        mainPanel.add(leftCard, BorderLayout.CENTER);
        mainPanel.add(rightCard, BorderLayout.EAST);
    }

    private void styleButton(JButton b, boolean primary) {
        b.setPreferredSize(new Dimension(150, 40));
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setFocusPainted(false);
        if(primary) { b.setBackground(new Color(37, 99, 235)); b.setForeground(Color.WHITE); }
        else { b.setBackground(new Color(243, 244, 246)); b.setForeground(new Color(55, 65, 81)); }
    }

    // Getters
    public JLabel getLblAdminName() { return lblAdminName; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAddManager() { return btnAddManager; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnLogout() { return btnLogout; }
    public JButton getBtnReport() { return btnReport; }
    public JLabel getLblProfilePic() { return lblProfilePic; }
}

