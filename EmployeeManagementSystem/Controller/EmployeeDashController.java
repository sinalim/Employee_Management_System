package EmployeeManagementSystem.Controller;

import EmployeeManagementSystem.Model.*;
import EmployeeManagementSystem.View.*;
import EmployeeManagementSystemDB.EmpDB;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class EmployeeDashController {
    private EmployeeDashView view;
    private EmployeeDashModel model;

    public EmployeeDashController(EmployeeDashView view, EmployeeDashModel model) {
        this.view = view;
        this.model = model;

        // Initialize UI with Model data
        refreshUI();

        // 1. CIRCULAR IMAGE UPLOAD
        this.view.getLblProfilePic().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(view.mainPanel) == JFileChooser.APPROVE_OPTION) {
                    String path = chooser.getSelectedFile().getAbsolutePath();
                    updateProfilePicture(path);
                }
            }
        });

        // 2. EDIT PROFILE DIALOG
        this.view.getBtnEditProfile().addActionListener(e -> showEditDialog());

        // 3. LOGOUT
        this.view.getBtnLogout().addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(view.mainPanel, "Confirm Logout?", "Logout", JOptionPane.YES_NO_OPTION) == 0) {
                LoginView lv = new LoginView();
                new LoginController(lv);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view.mainPanel);
                frame.setContentPane(lv.mainPanel);
                frame.revalidate();
            }
        });
    }

    private void updateProfilePicture(String path) {
        try {
            ImageIcon icon = new ImageIcon(path);
            int d = 150; // Diameter matches View size
            BufferedImage mask = new BufferedImage(d, d, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = mask.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new Ellipse2D.Double(0, 0, d, d));
            g2.setComposite(AlphaComposite.SrcIn);
            g2.drawImage(icon.getImage(), 0, 0, d, d, null);
            g2.dispose();

            view.getLblProfilePic().setIcon(new ImageIcon(mask));
            view.getLblProfilePic().setText("");
            model.setProfilePicPath(path);
            // Optional: Save 'path' to database here
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private void showEditDialog() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(view.mainPanel), "Edit My Profile", true);
        dialog.setSize(350, 300);
        dialog.setLayout(new GridLayout(4, 1, 10, 10));
        ((JPanel)dialog.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField txtName = new JTextField(model.getName());
        JTextField txtEmail = new JTextField(model.getEmail());
        JButton btnSave = new JButton("Save Changes");

        dialog.add(new JLabel("Full Name:"));
        dialog.add(txtName);
        dialog.add(new JLabel("Email Address:"));
        dialog.add(txtEmail);
        dialog.add(new JLabel("")); // Spacer
        dialog.add(btnSave);

        btnSave.addActionListener(ev -> {
            String name = txtName.getText().trim();
            String email = txtEmail.getText().trim();

            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Fields cannot be empty.");
                return;
            }

            // SQL Update for personal details
            String sql = "UPDATE Employee SET Emp_Name = ?, Emp_Email = ? WHERE Emp_ID = ?";
            try (Connection conn = EmpDB.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, model.getEmpId());

                if (pstmt.executeUpdate() > 0) {
                    model.setName(name);
                    model.setEmail(email);
                    refreshUI();
                    JOptionPane.showMessageDialog(dialog, "Profile Updated!");
                    dialog.dispose();
                }
            } catch (SQLException ex) { ex.printStackTrace(); }
        });

        dialog.setLocationRelativeTo(view.mainPanel);
        dialog.setVisible(true);
    }

    private void refreshUI() {
        view.getLblEmpName().setText(model.getName());
        view.getLblEmpID().setText("Employee ID: " + model.getEmpId());
        view.getLblEmail().setText("Email: " + model.getEmail());
        view.getLblSalary().setText("Current Salary: Rs. " + String.format("%,.2f", model.getSalary()));
    }
}
