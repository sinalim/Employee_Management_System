package EmployeeManagementSystem;

import EmployeeManagementSystem.Controller.WelcomeController;
import EmployeeManagementSystem.View.WelcomeView;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        //  Initialize the View first
        WelcomeView view = new WelcomeView();

        //  Initialize the Controller
        new WelcomeController(view);

        JFrame frame = new JFrame("Employee Management System - Welcome");
        frame.setContentPane(view.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.setSize(850, 650);
        frame.setLocationRelativeTo(null); // Centers the window
        frame.setVisible(true); // Makes the window appear
    }
}

