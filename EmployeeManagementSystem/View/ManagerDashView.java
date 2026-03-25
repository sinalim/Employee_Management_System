package EmployeeManagementSystem.View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManagerDashView {
    public JPanel mainPanel;
    private JLabel lblWelcome, lblManagerName, lblRole, lblSalary, lblProfilePic;
    private JTable tblEmployees;
    private DefaultTableModel tableModel;
    private JButton btnAddEmployee, btnDelete, btnLogout;

    public ManagerDashView() {
        mainPanel = new JPanel(new BorderLayout(25, 0));
        mainPanel.setBackground(new Color(245, 247, 251));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));


        JPanel leftCard = new JPanel(new BorderLayout(0, 15));
        leftCard.setBackground(Color.WHITE);
        leftCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel tblTitle = new JLabel("Staff Directory");
        tblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        leftCard.add(tblTitle, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Salary"}, 0);
        tblEmployees = new JTable(tableModel);
        tblEmployees.setRowHeight(35);
        leftCard.add(new JScrollPane(tblEmployees), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnPanel.setOpaque(false);
        btnDelete = new JButton("Delete Staff");
        btnAddEmployee = new JButton("+ Add Employee");
        styleButton(btnDelete, false);
        styleButton(btnAddEmployee, true);
        btnPanel.add(btnDelete); btnPanel.add(btnAddEmployee);
        leftCard.add(btnPanel, BorderLayout.SOUTH);


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

        lblManagerName = new JLabel("Manager Name");
        lblManagerName.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblManagerName.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblProfilePic = new JLabel("Upload Photo");
        lblProfilePic.setPreferredSize(new Dimension(130, 130));
        lblProfilePic.setMaximumSize(new Dimension(130, 130));
        lblProfilePic.setBorder(BorderFactory.createDashedBorder(Color.LIGHT_GRAY, 5, 5));
        lblProfilePic.setHorizontalAlignment(JLabel.CENTER);
        lblProfilePic.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblRole = new JLabel("Department Manager");
        lblRole.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblSalary = new JLabel("Salary: Rs. 0.00");
        lblSalary.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblSalary.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnLogout = new JButton("LOG OUT");
        btnLogout.setMaximumSize(new Dimension(220, 50));
        btnLogout.setBackground(new Color(239, 68, 68));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLogout.setFocusPainted(false);
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);

        rightCard.add(lblWelcome);
        rightCard.add(lblManagerName);
        rightCard.add(Box.createRigidArea(new Dimension(0, 20)));
        rightCard.add(lblProfilePic);
        rightCard.add(Box.createRigidArea(new Dimension(0, 15)));
        rightCard.add(lblRole);
        rightCard.add(Box.createRigidArea(new Dimension(0, 20)));
        rightCard.add(new JSeparator());
        rightCard.add(Box.createRigidArea(new Dimension(0, 15)));
        rightCard.add(lblSalary);
        rightCard.add(Box.createVerticalGlue());
        rightCard.add(btnLogout);

        mainPanel.add(leftCard, BorderLayout.CENTER);
        mainPanel.add(rightCard, BorderLayout.EAST);
    }

    private void styleButton(JButton b, boolean primary) {
        b.setPreferredSize(new Dimension(150, 40));
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        if(primary) { b.setBackground(new Color(37, 99, 235)); b.setForeground(Color.WHITE); }
        else { b.setBackground(new Color(243, 244, 246)); b.setForeground(Color.DARK_GRAY); }
    }

    public JLabel getLblManagerName() { return lblManagerName; }
    public JLabel getLblSalary() { return lblSalary; }
    public JLabel getLblProfilePic() { return lblProfilePic; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnAddEmployee() { return btnAddEmployee; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnLogout() { return btnLogout; }
}
