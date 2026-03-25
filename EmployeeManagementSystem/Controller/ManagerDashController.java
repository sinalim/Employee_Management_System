package EmployeeManagementSystem.Controller;

import EmployeeManagementSystem.View.*;
import EmployeeManagementSystemDB.EmpDB;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class ManagerDashController {
    private ManagerDashView view;
    private String managerName;

    public ManagerDashController(ManagerDashView view, String managerName) {
        this.view = view;
        this.managerName = managerName;

        // Set UI Data
        this.view.getLblManagerName().setText(managerName);
        loadTableData();
        loadSalaryInfo();

        // 1. profile pic upload
        this.view.getLblProfilePic().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(view.mainPanel) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIcon icon = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
                        int d = 130; // Matches View size
                        BufferedImage mask = new BufferedImage(d, d, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2 = mask.createGraphics();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.fill(new Ellipse2D.Double(0, 0, d, d));
                        g2.setComposite(AlphaComposite.SrcIn);
                        g2.drawImage(icon.getImage(), 0, 0, d, d, null);
                        g2.dispose();

                        view.getLblProfilePic().setIcon(new ImageIcon(mask));
                        view.getLblProfilePic().setText("");
                        view.getLblProfilePic().setBorder(null);
                    } catch (Exception ex) { ex.printStackTrace(); }
                }
            }
        });

        this.view.getBtnDelete().addActionListener(e -> {
            String id = JOptionPane.showInputDialog(view.mainPanel, "Enter Employee ID to delete:");
            if (id != null && !id.trim().isEmpty()) {
                int confirm = JOptionPane.showConfirmDialog(view.mainPanel, "Confirm Delete ID: " + id + "?", "Warning", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteFromDB(id);
                }
            }
        });

        // logout
        this.view.getBtnLogout().addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(view.mainPanel, "Are you sure you want to log out?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                SwingUtilities.getWindowAncestor(view.mainPanel).dispose();
                // Optional: Re-open LoginView here if needed
            }
        });
    }

    private void deleteFromDB(String id) {
        try (Connection conn = EmpDB.getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM Employee WHERE Emp_ID = ?")) {
            pst.setString(1, id);
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(view.mainPanel, "Employee Deleted!");
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(view.mainPanel, "ID not found.");
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    private void loadTableData() {
        DefaultTableModel model = view.getTableModel();
        model.setRowCount(0);
        try (Connection conn = EmpDB.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT e.Emp_ID, e.Emp_Name, e.Emp_Email, p.Amount FROM Employee e JOIN Payroll p ON e.P_Id = p.P_Id")) {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)});
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    private void loadSalaryInfo() {
        try (Connection conn = EmpDB.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT p.Amount FROM Employee e JOIN Payroll p ON e.P_Id = p.P_Id WHERE e.Emp_Name = ?")) {
            pst.setString(1, managerName);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                view.getLblSalary().setText("Salary: Rs. " + String.format("%,.2f", rs.getDouble(1)));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}
