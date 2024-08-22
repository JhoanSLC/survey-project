package com.surveyproject.responseOptions.UI;

import com.surveyproject.responseOptions.domain.entity.ResponseOptions;
import com.surveyproject.responseOptions.infrastructure.controller.ResponseOptionsController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class ResponseOptionsUI extends JFrame {
    private final ResponseOptionsController controller;
    private DefaultTableModel tableModel;
    private JTable responseOptionsTable;

    public ResponseOptionsUI(ResponseOptionsController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Response Options Management");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createResponseItem = new JMenuItem("Create Response");
        JMenuItem listResponsesItem = new JMenuItem("List Responses");
        JMenuItem editResponseItem = new JMenuItem("Edit Response");
        JMenuItem deleteResponseItem = new JMenuItem("Delete Response");
        JMenuItem findResponseItem = new JMenuItem("Find Response by ID");

        menu.add(createResponseItem);
        menu.add(listResponsesItem);
        menu.add(editResponseItem);
        menu.add(deleteResponseItem);
        menu.add(findResponseItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display responses
        tableModel = new DefaultTableModel(new String[]{
                "ID", "Option Value", "Category Catalog ID", "Parent Response ID", "Question ID", "Created At", "Updated At", "Type Component HTML", "Comment Response", "Option Text"
        }, 0);
        responseOptionsTable = new JTable(tableModel);
        add(new JScrollPane(responseOptionsTable), BorderLayout.CENTER);

        // Event listeners
        createResponseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createResponseDialog();
            }
        });

        listResponsesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listResponses();
            }
        });

        editResponseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editResponseDialog();
            }
        });

        deleteResponseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteResponseDialog();
            }
        });

        findResponseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findResponseByIdDialog();
            }
        });

        setVisible(true);
    }

    private void createResponseDialog() {
        JTextField optionValueField = new JTextField();
        JTextField categoryCatalogIdField = new JTextField();
        JTextField parentResponseIdField = new JTextField();
        JTextField questionIdField = new JTextField();
        JTextField typeComponentHtmlField = new JTextField();
        JTextField commentResponseField = new JTextField();
        JTextField optionTextField = new JTextField();

        Object[] fields = {
                "Option Value:", optionValueField,
                "Category Catalog ID:", categoryCatalogIdField,
                "Parent Response ID ('0' if doesn't apply):", parentResponseIdField,
                "Question ID:", questionIdField,
                "Type Component HTML:", typeComponentHtmlField,
                "Comment Response:", commentResponseField,
                "Option Text:", optionTextField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Response Option", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            ResponseOptions responseOption = new ResponseOptions();
            responseOption.setOptionValue(Integer.parseInt(optionValueField.getText()));
            responseOption.setCategoryCatalogId(Long.parseLong(categoryCatalogIdField.getText()));
            responseOption.setParentResponseId(Long.parseLong(parentResponseIdField.getText()));
            responseOption.setQuestionId(Long.parseLong(questionIdField.getText()));
            responseOption.setTypeComponentHtml(typeComponentHtmlField.getText());
            responseOption.setCommentResponse(commentResponseField.getText());
            responseOption.setOptionText(optionTextField.getText());

            controller.createResponse(responseOption);
            JOptionPane.showMessageDialog(this, "Response created successfully!");
            listResponses(); // Refresh the table after creation
        }
    }

    private void listResponses() {
        List<ResponseOptions> responses = controller.listAllResponses();
        tableModel.setRowCount(0); // Clear existing rows
        for (ResponseOptions response : responses) {
            tableModel.addRow(new Object[]{
                    response.getId(),
                    response.getOptionValue(),
                    response.getCategoryCatalogId(),
                    response.getParentResponseId(),
                    response.getQuestionId(),
                    response.getCreatedAt(),
                    response.getUpdatedAt(),
                    response.getTypeComponentHtml(),
                    response.getCommentResponse(),
                    response.getOptionText()
            });
        }
    }

    private void editResponseDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Response ID to edit:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<ResponseOptions> responseOpt = controller.findResponseById(id);
            if (responseOpt.isPresent()) {
                ResponseOptions response = responseOpt.get();

                JTextField optionValueField = new JTextField(String.valueOf(response.getOptionValue()));
                JTextField categoryCatalogIdField = new JTextField(String.valueOf(response.getCategoryCatalogId()));
                JTextField parentResponseIdField = new JTextField(String.valueOf(response.getParentResponseId()));
                JTextField questionIdField = new JTextField(String.valueOf(response.getQuestionId()));
                JTextField typeComponentHtmlField = new JTextField(response.getTypeComponentHtml());
                JTextField commentResponseField = new JTextField(response.getCommentResponse());
                JTextField optionTextField = new JTextField(response.getOptionText());

                Object[] fields = {
                        "Option Value:", optionValueField,
                        "Category Catalog ID:", categoryCatalogIdField,
                        "Parent Response ID:", parentResponseIdField,
                        "Question ID:", questionIdField,
                        "Type Component HTML:", typeComponentHtmlField,
                        "Comment Response:", commentResponseField,
                        "Option Text:", optionTextField
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit Response Option", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    response.setOptionValue(Integer.parseInt(optionValueField.getText()));
                    response.setCategoryCatalogId(Long.parseLong(categoryCatalogIdField.getText()));
                    response.setParentResponseId(Long.parseLong(parentResponseIdField.getText()));
                    response.setQuestionId(Long.parseLong(questionIdField.getText()));
                    response.setTypeComponentHtml(typeComponentHtmlField.getText());
                    response.setCommentResponse(commentResponseField.getText());
                    response.setOptionText(optionTextField.getText());

                    controller.updateResponse(response, id);
                    JOptionPane.showMessageDialog(this, "Response updated successfully!");
                    listResponses(); // Refresh the table after editing
                }
            } else {
                JOptionPane.showMessageDialog(this, "Response not found!");
            }
        }
    }

    private void deleteResponseDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Response ID to delete:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            controller.deleteResponse(id);
            JOptionPane.showMessageDialog(this, "Response deleted successfully!");
            listResponses(); // Refresh the table after deletion
        }
    }

    private void findResponseByIdDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Response ID to find:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<ResponseOptions> responseOpt = controller.findResponseById(id);
            if (responseOpt.isPresent()) {
                ResponseOptions response = responseOpt.get();
                JOptionPane.showMessageDialog(this, "Response found:\n" +
                        "ID: " + response.getId() + "\n" +
                        "Option Value: " + response.getOptionValue() + "\n" +
                        "Category Catalog ID: " + response.getCategoryCatalogId() + "\n" +
                        "Parent Response ID: " + response.getParentResponseId() + "\n" +
                        "Question ID: " + response.getQuestionId() + "\n" +
                        "Created At: " + response.getCreatedAt() + "\n" +
                        "Updated At: " + response.getUpdatedAt() + "\n" +
                        "Type Component HTML: " + response.getTypeComponentHtml() + "\n" +
                        "Comment Response: " + response.getCommentResponse() + "\n" +
                        "Option Text: " + response.getOptionText()
                );
            } else {
                JOptionPane.showMessageDialog(this, "Response not found!");
            }
        }
    }
}
