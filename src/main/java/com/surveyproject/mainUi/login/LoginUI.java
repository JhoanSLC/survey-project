package com.surveyproject.mainUi.login;

import javax.swing.*;
import com.surveyproject.database.DatabaseConnection;
import com.surveyproject.mainUi.adminUi.AdminUI;
import com.surveyproject.mainUi.register.RegisterUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public LoginUI() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        frame.setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter username and password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection con = databaseConnection.connectDatabase()) {
            String query = "SELECT u.id, ur.roleId FROM users u JOIN usersRoles ur ON ur.userId = u.id WHERE username = ? AND password = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        long roleId = rs.getLong("roleId");

                        if (roleId == 2) {
                            JOptionPane.showMessageDialog(frame, "Welcome, Admin!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                            SwingUtilities.invokeLater(() -> new AdminUI().setVisible(true));
                        } else {
                            JOptionPane.showMessageDialog(frame, "Welcome, User!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                            // Open User Menu or Dashboard
                        }
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        // Open registration form
        SwingUtilities.invokeLater(() -> new RegisterUI());
    }
}
