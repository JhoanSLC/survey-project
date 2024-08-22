package com.surveyproject.roles.UI;

import com.surveyproject.roles.domain.entity.Roles;
import com.surveyproject.roles.infrastructure.controller.RolesController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class RolesUI extends JFrame {
    private final RolesController controller;
    private DefaultTableModel tableModel;
    private JTable roleTable;

    public RolesUI(RolesController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Roles Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createRoleItem = new JMenuItem("Create Role");
        JMenuItem listRolesItem = new JMenuItem("List Roles");
        JMenuItem editRoleItem = new JMenuItem("Edit Role");
        JMenuItem deleteRoleItem = new JMenuItem("Delete Role");
        JMenuItem findRoleItem = new JMenuItem("Find Role by ID");
        JMenuItem closeItem = new JMenuItem("Close");

        menu.add(createRoleItem);
        menu.add(listRolesItem);
        menu.add(editRoleItem);
        menu.add(deleteRoleItem);
        menu.add(findRoleItem);
        menu.add(closeItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display roles
        tableModel = new DefaultTableModel(new String[]{"ID", "Name"}, 0);
        roleTable = new JTable(tableModel);
        add(new JScrollPane(roleTable), BorderLayout.CENTER);

        // Event listeners
        createRoleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRoleDialog();
            }
        });

        listRolesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listRoles();
            }
        });

        editRoleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editRoleDialog();
            }
        });

        deleteRoleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRoleDialog();
            }
        });

        findRoleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findRoleByIdDialog();
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

    private void createRoleDialog() {
        JTextField nameField = new JTextField();
        Object[] fields = {
            "Name:", nameField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Role", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();

            if (!name.isEmpty()) {
                Roles role = new Roles();
                role.setName(name);
                controller.createRole(role);
                JOptionPane.showMessageDialog(this, "Role created successfully!");
                listRoles(); // Refrescar la tabla después de crear
            } else {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.");
            }
        }
    }

    private void listRoles() {
        List<Roles> roles = controller.listAllRoles();
        tableModel.setRowCount(0); // Clear existing rows
        for (Roles role : roles) {
            tableModel.addRow(new Object[]{
                role.getId(),
                role.getName()
            });
        }
    }

    private void editRoleDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Role ID to edit:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Roles> roleOpt = controller.findRoleById(id);
            if (roleOpt.isPresent()) {
                Roles role = roleOpt.get();

                JTextField nameField = new JTextField(role.getName());
                Object[] fields = {
                    "Name:", nameField
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit Role", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    role.setName(nameField.getText());
                    controller.updateRole(role, id);
                    JOptionPane.showMessageDialog(this, "Role updated successfully!");
                    listRoles(); // Refrescar la tabla después de actualizar
                }
            } else {
                JOptionPane.showMessageDialog(this, "Role not found with ID: " + id);
            }
        }
    }

    private void deleteRoleDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Role ID to delete:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Roles> roleOpt = controller.findRoleById(id);
            if (roleOpt.isPresent()) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the role?", "Delete Role", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.deleteRole(id);
                    JOptionPane.showMessageDialog(this, "Role deleted successfully!");
                    listRoles(); // Refrescar la tabla después de eliminar
                }
            } else {
                JOptionPane.showMessageDialog(this, "Role not found with ID: " + id);
            }
        }
    }

    private void findRoleByIdDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Role ID to find:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Roles> roleOpt = controller.findRoleById(id);
            if (roleOpt.isPresent()) {
                Roles role = roleOpt.get();
                JTextField nameField = new JTextField(role.getName());

                nameField.setEditable(false);

                Object[] fields = {
                    "Name:", nameField
                };

                JOptionPane.showMessageDialog(this, fields, "Role Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Role not found with ID: " + id);
            }
        }
    }
}
