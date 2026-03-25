package EmployeeManagementSystem.View;

import javax.swing.*;
import java.awt.*;

public class WelcomeView {

    public JPanel mainPanel;
    private JLabel lblWelcome;
    private JLabel lblLogo; // Added for Logo
    private JButton btnGetStarted;
    private JButton btnRegister;

    public WelcomeView() {
        // 1. Initialize mainPanel with Wallpaper
        mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon img = new ImageIcon(getClass().getResource("/EmployeeManagementSystem/bg.jpg"));
                    g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    g.setColor(new Color(44, 62, 80));
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        // 2. Add Logo (Top)
        try {
            ImageIcon logoIcon = new ImageIcon(getClass().getResource("/EmployeeManagementSystem/logo1.jpg"));
            // Scaling the logo to 120x120 pixels (Appropriate size)
            Image scaledLogo = logoIcon.getImage().getScaledInstance(180, 90, Image.SCALE_SMOOTH);
            lblLogo = new JLabel(new ImageIcon(scaledLogo));

            gbc.gridy = 0;
            gbc.insets = new Insets(0, 0, 20, 0); // Space below logo
            mainPanel.add(lblLogo, gbc);
        } catch (Exception e) {
            System.out.println("Logo file not found!");
        }

        // 3. Title Label (Middle)
        lblWelcome = new JLabel("Welcome to StaffSync");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 42));
        lblWelcome.setForeground(Color.WHITE);

        gbc.gridy = 1;
        gbc.insets = new Insets(15, 10, 30, 10); // Spacing
        mainPanel.add(lblWelcome, gbc);

        // 4. Initialize and Style Buttons
        btnGetStarted = new JButton("Get Started");
        btnRegister = new JButton("Create Account");
        styleButton(btnGetStarted);
        styleButton(btnRegister);

        // 5. Buttons Panel (Bottom)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setOpaque(false);
        btnPanel.add(btnGetStarted);
        btnPanel.add(btnRegister);

        gbc.gridy = 2;
        gbc.insets = new Insets(15, 10, 15, 10);
        mainPanel.add(btnPanel, gbc);
    }

    private void styleButton(JButton btn) {
        btn.setPreferredSize(new Dimension(180, 45));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusable(false);
        btn.setBackground(new Color(255, 255, 255, 220));
        btn.setForeground(new Color(44, 62, 80));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    public JButton getBtnGetStarted() { return btnGetStarted; }
    public JButton getBtnRegister() { return btnRegister; }
}
