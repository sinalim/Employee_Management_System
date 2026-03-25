package EmployeeManagementSystem.View;

import javax.swing.*;
import java.awt.*;

public class LoginView {
    public JPanel mainPanel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> comboRole;
    private JButton btnLogin, btnBack;

    public LoginView() {
        // 1. Background Wallpaper
        mainPanel = new JPanel(new GridBagLayout()) {           //white card + bg
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon img = new ImageIcon(getClass().getResource("/EmployeeManagementSystem/bg.jpg"));
                    g.drawImage(img.getImage(), 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    g.setColor(new Color(28, 35, 65)); // Dark blue fallback
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        // 2. white card
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setOpaque(true); // Makes the card solid white
        card.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);

        // Header Title
        JLabel lblTitle = new JLabel("StaffSync Login", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(44, 62, 80));
        gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 25, 0);
        card.add(lblTitle, gbc);

        // 3. Initialize Components
        txtUsername = (JTextField) styleField(new JTextField());
        txtPassword = (JPasswordField) styleField(new JPasswordField());
        comboRole = (JComboBox<String>) styleField(new JComboBox<>(new String[]{"Admin", "Manager", "Employee"}));

        // 4. Add Rows (Label + Field)
        int row = 1;
        addFormRow("Email Address / Username", txtUsername, row++, gbc, card);
        addFormRow("Password", txtPassword, row++, gbc, card);
        addFormRow("User Role", comboRole, row++, gbc, card);

        // 5. Buttons Section
        btnBack = new JButton("Back");
        btnLogin = new JButton("Sign In");

        styleButton(btnBack, false);
        styleButton(btnLogin, true);

        // Grid for buttons (Side by Side)
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        btnPanel.setOpaque(false);
        btnPanel.add(btnBack);
        btnPanel.add(btnLogin);

        gbc.gridy = row * 2;
        gbc.insets = new Insets(25, 0, 0, 0);
        card.add(btnPanel, gbc);                   //add buttons inside card

        mainPanel.add(card); // Centers the card on wallpaper
    }

    private JComponent styleField(JComponent c) {
        c.setPreferredSize(new Dimension(320, 40));
        c.setBackground(Color.WHITE);
        c.setForeground(new Color(120, 120, 120));

        c.setFont(new Font("Segoe UI", Font.PLAIN, 14));
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
        card.add(lbl, gbc);                                     //adding labels inside card

        gbc.gridy = (row * 2) + 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        card.add(field, gbc);                                 //adding textfields inside card
    }

    private void styleButton(JButton b, boolean isPrimary) {
        b.setPreferredSize(new Dimension(150, 45));
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setBorderPainted(false);

        if (isPrimary) {
            b.setBackground(new Color(52, 116, 225)); // Windows Blue
            b.setForeground(Color.WHITE);
        } else {
            b.setBackground(new Color(235, 237, 240)); // Soft Gray
            b.setForeground(new Color(100, 100, 100));
        }
    }

    // getters
    public JTextField getTxtUsername() { return txtUsername; }
    public JPasswordField getTxtPassword() { return txtPassword; }
    public JComboBox<String> getComboRole() { return comboRole; }
    public JButton getBtnLogin() { return btnLogin; }
    public JButton getBtnBack() { return btnBack; }
}
