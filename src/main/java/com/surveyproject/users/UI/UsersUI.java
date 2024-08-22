package com.surveyproject.users.UI;

import com.surveyproject.users.domain.entity.Users;
import com.surveyproject.users.infrastructure.controller.UsersController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class UsersUI extends JFrame {
    private final UsersController controller;
    private DefaultTableModel tableModel;
    private JTable userTable;

    public UsersUI(UsersController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("User Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createUserItem = new JMenuItem("Create User");
        JMenuItem listUsersItem = new JMenuItem("List Users");
        JMenuItem editUserItem = new JMenuItem("Edit User");
        JMenuItem deleteUserItem = new JMenuItem("Delete User");
        JMenuItem findUserItem = new JMenuItem("Find User by ID");
        JMenuItem closeItem = new JMenuItem("Close");

        menu.add(createUserItem);
        menu.add(listUsersItem);
        menu.add(editUserItem);
        menu.add(deleteUserItem);
        menu.add(findUserItem);
        menu.add(closeItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display users
        tableModel = new DefaultTableModel(new String[]{"ID", "Enabled", "Username", "Password"}, 0);
        userTable = new JTable(tableModel);
        add(new JScrollPane(userTable), BorderLayout.CENTER);

        // Event listeners
        createUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUserDialog();
            }
        });

        listUsersItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listUsers();
            }
        });

        editUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUserDialog();
            }
        });

        deleteUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUserDialog();
            }
        });

        findUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findUserByIdDialog();
            }
        });

        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });

        setVisible(true);
    }

    private void createUserDialog() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JCheckBox enabledCheckbox = new JCheckBox("Enabled");

        Object[] fields = {
            "Username:", usernameField,
            "Password:", passwordField,
            enabledCheckbox
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            boolean enabled = enabledCheckbox.isSelected();

            if (!username.isEmpty() && !password.isEmpty()) {
                Users user = new Users();
                user.setUsername(username);
                user.setPassword(password);
                user.setEnabled(enabled);
                controller.createUser(user);
                JOptionPane.showMessageDialog(this, "User created successfully!");
                listUsers(); // Refrescar la tabla después de crear
            } else {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty.");
            }
        }
    }

    private void listUsers() {
        List<Users> users = controller.listAllUsers();
        tableModel.setRowCount(0); // Clear existing rows
        for (Users user : users) {
            tableModel.addRow(new Object[]{
                user.getId(),
                user.getEnabled(),
                user.getUsername(),
                user.getPassword()
            });
        }
    }

    private void editUserDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter User ID to edit:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Users> userOpt = controller.findUserById(id);
            if (userOpt.isPresent()) {
                Users user = userOpt.get();

                JTextField usernameField = new JTextField(user.getUsername());
                JPasswordField passwordField = new JPasswordField(user.getPassword());
                JCheckBox enabledCheckbox = new JCheckBox("Enabled", user.getEnabled());

                Object[] fields = {
                    "Username:", usernameField,
                    "Password:", passwordField,
                    enabledCheckbox
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit User", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    user.setUsername(usernameField.getText());
                    user.setPassword(new String(passwordField.getPassword()));
                    user.setEnabled(enabledCheckbox.isSelected());
                    controller.updateUser(user, id);
                    JOptionPane.showMessageDialog(this, "User updated successfully!");
                    listUsers(); // Refrescar la tabla después de actualizar
                }
            } else {
                JOptionPane.showMessageDialog(this, "User not found with ID: " + id);
            }
        }
    }

    private void deleteUserDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter User ID to delete:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Users> userOpt = controller.findUserById(id);
            if (userOpt.isPresent()) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the user?", "Delete User", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.deleteUser(id);
                    JOptionPane.showMessageDialog(this, "User deleted successfully!");
                    listUsers(); // Refrescar la tabla después de eliminar
                }
            } else {
                JOptionPane.showMessageDialog(this, "User not found with ID: " + id);
            }
        }
    }

    private void findUserByIdDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter User ID to find:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Users> userOpt = controller.findUserById(id);
            if (userOpt.isPresent()) {
                Users user = userOpt.get();
                JTextField usernameField = new JTextField(user.getUsername());
                JPasswordField passwordField = new JPasswordField(user.getPassword());
                JCheckBox enabledCheckbox = new JCheckBox("Enabled", user.getEnabled());

                usernameField.setEditable(false);
                passwordField.setEditable(false);
                enabledCheckbox.setEnabled(false);

                Object[] fields = {
                    "Username:", usernameField,
                    "Password:", passwordField,
                    "Enabled:", enabledCheckbox
                };

                JOptionPane.showMessageDialog(this, fields, "User Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "User not found with ID: " + id);
            }
        }
    }
}
