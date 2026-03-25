package EmployeeManagementSystem.Controller;

import EmployeeManagementSystem.Model.*;
import EmployeeManagementSystem.View.*;
import javax.swing.*;
import java.awt.Dimension;

public class WelcomeController {
    private WelcomeView view;

    public WelcomeController(WelcomeView view) {
        this.view = view;

        // goes to login
        this.view.getBtnGetStarted().addActionListener(e -> {
            LoginView lv = new LoginView();

            new LoginController(lv);
            switchPanel(lv.mainPanel);
        });

        // Goes to Register
        this.view.getBtnRegister().addActionListener(e -> {
            RegisterView rv = new RegisterView();

            switchPanel(rv.mainPanel);
        });
    }

    private void switchPanel(JPanel newPanel) {
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(view.mainPanel);
        if (currentFrame != null) {
            currentFrame.setContentPane(newPanel);

            // Adjusting size for the new screen
            currentFrame.setSize(850, 650);
            currentFrame.setMinimumSize(new Dimension(800, 600));
            currentFrame.setLocationRelativeTo(null);

            currentFrame.revalidate();
            currentFrame.repaint();
        }
    }
}
