package com.surveyproject.mainUi.register;

import javax.swing.*;
import com.surveyproject.database.DatabaseConnection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterUI {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public RegisterUI() {
        frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(430, 300);
        frame.setLayout(new GridLayout(4, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(new JLabel()); // empty label for spacing
        frame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });

        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection con = databaseConnection.connectDatabase()) {
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();
                ResultSet generatedId = ps.getGeneratedKeys();
                if (generatedId.next()){
                    String insertUserRole = "INSERT INTO usersRoles (roleId,userId) VALUES (1,?)";
                    try (PreparedStatement stm = con.prepareStatement(insertUserRole)){
                        
                        stm.setLong(1,generatedId.getLong(1));
                        stm.executeUpdate();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(frame, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
              
                
                frame.dispose();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterUI());
    }
}
