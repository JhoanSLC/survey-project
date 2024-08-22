package com.surveyproject.usersRoles.UI;

import com.surveyproject.usersRoles.domain.entity.UsersRoles;
import com.surveyproject.usersRoles.infrastructure.controller.UsersRolesController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class UsersRolesUI extends JFrame {
    private final UsersRolesController controller;
    private DefaultTableModel tableModel;
    private JTable userRoleTable;

    public UsersRolesUI(UsersRolesController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Users Roles Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createUserRoleItem = new JMenuItem("Create User Role");
        JMenuItem listUserRolesItem = new JMenuItem("List User Roles");
        JMenuItem editUserRoleItem = new JMenuItem("Edit User Role");
        JMenuItem deleteUserRoleItem = new JMenuItem("Delete User Role");
        JMenuItem findUserRoleItem = new JMenuItem("Find User Role by ID");
        JMenuItem closeItem = new JMenuItem("Close");

        menu.add(createUserRoleItem);
        menu.add(listUserRolesItem);
        menu.add(editUserRoleItem);
        menu.add(deleteUserRoleItem);
        menu.add(findUserRoleItem);
        menu.add(closeItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display user roles
        tableModel = new DefaultTableModel(new String[]{"Role ID", "User ID"}, 0);
        userRoleTable = new JTable(tableModel);
        add(new JScrollPane(userRoleTable), BorderLayout.CENTER);

        // Event listeners
        createUserRoleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUserRoleDialog();
            }
        });

        listUserRolesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listUserRoles();
            }
        });

        editUserRoleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUserRoleDialog();
            }
        });

        deleteUserRoleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteUserRoleDialog();
            }
        });

        findUserRoleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findUserRoleByIdDialog();
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

    private void createUserRoleDialog() {
        JTextField roleIdField = new JTextField();
        JTextField userIdField = new JTextField();
        Object[] fields = {
            "Role ID:", roleIdField,
            "User ID:", userIdField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create User Role", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                long roleId = Long.parseLong(roleIdField.getText());
                long userId = Long.parseLong(userIdField.getText());

                UsersRoles userRole = new UsersRoles();
                userRole.setRoleId(roleId);
                userRole.setUserId(userId);
                controller.createUserRole(userRole);
                JOptionPane.showMessageDialog(this, "User role created successfully!");
                listUserRoles(); // Refresh table after creation
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for Role ID and User ID.");
            }
        }
    }

    private void listUserRoles() {
        List<UsersRoles> userRoles = controller.listAllUserRoles();
        tableModel.setRowCount(0); // Clear existing rows
        for (UsersRoles userRole : userRoles) {
            tableModel.addRow(new Object[]{
                userRole.getRoleId(),
                userRole.getUserId()
            });
        }
    }

    private void editUserRoleDialog() {
        String roleIdStr = JOptionPane.showInputDialog(this, "Enter Role ID to edit:");
        String userIdStr = JOptionPane.showInputDialog(this, "Enter User ID to edit:");
        if (roleIdStr != null && userIdStr != null) {
            try {
                long roleId = Long.parseLong(roleIdStr);
                long userId = Long.parseLong(userIdStr);
                Optional<UsersRoles> userRoleOpt = controller.findUserRoleById(roleId, userId);
                if (userRoleOpt.isPresent()) {
                    UsersRoles userRole = userRoleOpt.get();

                    JTextField newRoleIdField = new JTextField(String.valueOf(userRole.getRoleId()));
                    JTextField newUserIdField = new JTextField(String.valueOf(userRole.getUserId()));
                    Object[] fields = {
                        "Role ID:", newRoleIdField,
                        "User ID:", newUserIdField
                    };

                    int option = JOptionPane.showConfirmDialog(this, fields, "Edit User Role", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        userRole.setRoleId(Long.parseLong(newRoleIdField.getText()));
                        userRole.setUserId(Long.parseLong(newUserIdField.getText()));
                        controller.updateUserRole(userRole, userId, roleId);
                        JOptionPane.showMessageDialog(this, "User role updated successfully!");
                        listUserRoles(); // Refresh table after update
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User role not found with Role ID: " + roleId + " and User ID: " + userId);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for Role ID and User ID.");
            }
        }
    }

    private void deleteUserRoleDialog() {
        String roleIdStr = JOptionPane.showInputDialog(this, "Enter Role ID to delete:");
        String userIdStr = JOptionPane.showInputDialog(this, "Enter User ID to delete:");
        if (roleIdStr != null && userIdStr != null) {
            try {
                long roleId = Long.parseLong(roleIdStr);
                long userId = Long.parseLong(userIdStr);
                Optional<UsersRoles> userRoleOpt = controller.findUserRoleById(roleId, userId);
                if (userRoleOpt.isPresent()) {
                    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the user role?", "Delete User Role", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        controller.deleteUserRole(roleId, userId);
                        JOptionPane.showMessageDialog(this, "User role deleted successfully!");
                        listUserRoles(); // Refresh table after deletion
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "User role not found with Role ID: " + roleId + " and User ID: " + userId);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for Role ID and User ID.");
            }
        }
    }

    private void findUserRoleByIdDialog() {
        String roleIdStr = JOptionPane.showInputDialog(this, "Enter Role ID to find:");
        String userIdStr = JOptionPane.showInputDialog(this, "Enter User ID to find:");
        if (roleIdStr != null && userIdStr != null) {
            try {
                long roleId = Long.parseLong(roleIdStr);
                long userId = Long.parseLong(userIdStr);
                Optional<UsersRoles> userRoleOpt = controller.findUserRoleById(roleId, userId);
                if (userRoleOpt.isPresent()) {
                    UsersRoles userRole = userRoleOpt.get();
                    JOptionPane.showMessageDialog(this, "User Role Found:\nRole ID: " + userRole.getRoleId() + "\nUser ID: " + userRole.getUserId());
                } else {
                    JOptionPane.showMessageDialog(this, "User role not found with Role ID: " + roleId + " and User ID: " + userId);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for Role ID and User ID.");
            }
        }
    }
}
