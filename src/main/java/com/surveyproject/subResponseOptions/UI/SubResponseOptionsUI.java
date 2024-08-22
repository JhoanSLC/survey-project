package com.surveyproject.subResponseOptions.UI;

import com.surveyproject.subResponseOptions.domain.entity.SubResponseOptions;
import com.surveyproject.subResponseOptions.infrastructure.controller.SubResponseOptionsController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class SubResponseOptionsUI extends JFrame {
    private final SubResponseOptionsController controller;
    private DefaultTableModel tableModel;
    private JTable subResponseTable;

    public SubResponseOptionsUI(SubResponseOptionsController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Sub-Response Options Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createSubResponseItem = new JMenuItem("Create Sub-Response");
        JMenuItem listSubResponsesItem = new JMenuItem("List Sub-Responses");
        JMenuItem editSubResponseItem = new JMenuItem("Edit Sub-Response");
        JMenuItem deleteSubResponseItem = new JMenuItem("Delete Sub-Response");
        JMenuItem findSubResponseItem = new JMenuItem("Find Sub-Response by ID");
        JMenuItem closeItem = new JMenuItem("Close");

        menu.add(createSubResponseItem);
        menu.add(listSubResponsesItem);
        menu.add(editSubResponseItem);
        menu.add(deleteSubResponseItem);
        menu.add(findSubResponseItem);
        menu.add(closeItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display sub-responses
        tableModel = new DefaultTableModel(new String[]{"ID", "Sub-Response Number", "Response Options ID", "Sub-Response Text", "Component HTML", "Created At", "Updated At"}, 0);
        subResponseTable = new JTable(tableModel);
        add(new JScrollPane(subResponseTable), BorderLayout.CENTER);

        // Event listeners
        createSubResponseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSubResponseDialog();
            }
        });

        listSubResponsesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listSubResponses();
            }
        });

        editSubResponseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSubResponseDialog();
            }
        });

        deleteSubResponseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSubResponseDialog();
            }
        });

        findSubResponseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findSubResponseByIdDialog();
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

    private void createSubResponseDialog() {
        JTextField subResponseNumberField = new JTextField();
        JTextField responseOptionsIdField = new JTextField();
        JTextField subResponseTextField = new JTextField();
        JTextField componentHtmlField = new JTextField();
        Object[] fields = {
            "Sub-Response Number:", subResponseNumberField,
            "Response Options ID:", responseOptionsIdField,
            "Sub-Response Text:", subResponseTextField,
            "Component HTML:", componentHtmlField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Sub-Response", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int subResponseNumber = Integer.parseInt(subResponseNumberField.getText());
            long responseOptionsId = Long.parseLong(responseOptionsIdField.getText());
            String subResponseText = subResponseTextField.getText();
            String componentHtml = componentHtmlField.getText();

            if (!subResponseText.isEmpty()) {
                SubResponseOptions subResponse = new SubResponseOptions();
                subResponse.setSubResponseNumber(subResponseNumber);
                subResponse.setResponseOptionsId(responseOptionsId);
                subResponse.setSubResponseText(subResponseText);
                subResponse.setComponentHtml(componentHtml);
                controller.createSubResponse(subResponse);
                JOptionPane.showMessageDialog(this, "Sub-Response created successfully!");
                listSubResponses(); // Refrescar la tabla después de crear
            } else {
                JOptionPane.showMessageDialog(this, "Sub-Response Text cannot be empty.");
            }
        }
    }

    private void listSubResponses() {
        List<SubResponseOptions> subResponses = controller.listAllSubResponses();
        tableModel.setRowCount(0); // Clear existing rows
        for (SubResponseOptions subResponse : subResponses) {
            tableModel.addRow(new Object[]{
                subResponse.getId(),
                subResponse.getSubResponseNumber(),
                subResponse.getResponseOptionsId(),
                subResponse.getSubResponseText(),
                subResponse.getComponentHtml(),
                subResponse.getCreatedAt(),
                subResponse.getUpdatedAt()
            });
        }
    }

    private void editSubResponseDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Sub-Response ID to edit:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<SubResponseOptions> subResponseOpt = controller.findSubResponseById(id);
            if (subResponseOpt.isPresent()) {
                SubResponseOptions subResponse = subResponseOpt.get();

                JTextField subResponseNumberField = new JTextField(String.valueOf(subResponse.getSubResponseNumber()));
                JTextField responseOptionsIdField = new JTextField(String.valueOf(subResponse.getResponseOptionsId()));
                JTextField subResponseTextField = new JTextField(subResponse.getSubResponseText());
                JTextField componentHtmlField = new JTextField(subResponse.getComponentHtml());
                Object[] fields = {
                    "Sub-Response Number:", subResponseNumberField,
                    "Response Options ID:", responseOptionsIdField,
                    "Sub-Response Text:", subResponseTextField,
                    "Component HTML:", componentHtmlField
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit Sub-Response", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    subResponse.setSubResponseNumber(Integer.parseInt(subResponseNumberField.getText()));
                    subResponse.setResponseOptionsId(Long.parseLong(responseOptionsIdField.getText()));
                    subResponse.setSubResponseText(subResponseTextField.getText());
                    subResponse.setComponentHtml(componentHtmlField.getText());
                    controller.updateSubResponse(subResponse, id);
                    JOptionPane.showMessageDialog(this, "Sub-Response updated successfully!");
                    listSubResponses(); // Refrescar la tabla después de actualizar
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sub-Response not found with ID: " + id);
            }
        }
    }

    private void deleteSubResponseDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Sub-Response ID to delete:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<SubResponseOptions> subResponseOpt = controller.findSubResponseById(id);
            if (subResponseOpt.isPresent()) {
                controller.deleteSubResponse(id);
                JOptionPane.showMessageDialog(this, "Sub-Response deleted successfully!");
                listSubResponses(); // Refrescar la tabla después de eliminar
            } else {
                JOptionPane.showMessageDialog(this, "Sub-Response not found with ID: " + id);
            }
        }
    }

    private void findSubResponseByIdDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Sub-Response ID to find:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<SubResponseOptions> subResponseOpt = controller.findSubResponseById(id);
            if (subResponseOpt.isPresent()) {
                SubResponseOptions subResponse = subResponseOpt.get();
                JOptionPane.showMessageDialog(this, String.format("ID: %d%nSub-Response Number: %d%nResponse Options ID: %d%nSub-Response Text: %s%nComponent HTML: %s%nCreated At: %s%nUpdated At: %s",
                    subResponse.getId(),
                    subResponse.getSubResponseNumber(),
                    subResponse.getResponseOptionsId(),
                    subResponse.getSubResponseText(),
                    subResponse.getComponentHtml(),
                    subResponse.getCreatedAt(),
                    subResponse.getUpdatedAt()
                ));
            } else {
                JOptionPane.showMessageDialog(this, "Sub-Response not found with ID: " + id);
            }
        }
    }
}
