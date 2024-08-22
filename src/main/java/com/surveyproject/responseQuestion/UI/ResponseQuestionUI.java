package com.surveyproject.responseQuestion.UI;

import com.surveyproject.responseQuestion.domain.entity.ResponseQuestion;
import com.surveyproject.responseQuestion.infrastructure.controller.ResponseQuestionController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class ResponseQuestionUI extends JFrame {
    private final ResponseQuestionController controller;
    private DefaultTableModel tableModel;
    private JTable responseQuestionTable;

    public ResponseQuestionUI(ResponseQuestionController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Response Question Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createResponseQuestionItem = new JMenuItem("Create Response Question");
        JMenuItem listResponseQuestionsItem = new JMenuItem("List Response Questions");
        JMenuItem editResponseQuestionItem = new JMenuItem("Edit Response Question");
        JMenuItem deleteResponseQuestionItem = new JMenuItem("Delete Response Question");
        JMenuItem findResponseQuestionItem = new JMenuItem("Find Response Question by ID");

        menu.add(createResponseQuestionItem);
        menu.add(listResponseQuestionsItem);
        menu.add(editResponseQuestionItem);
        menu.add(deleteResponseQuestionItem);
        menu.add(findResponseQuestionItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display response questions
        tableModel = new DefaultTableModel(new String[]{"ID", "Response ID", "Sub Response ID", "Response Text"}, 0);
        responseQuestionTable = new JTable(tableModel);
        add(new JScrollPane(responseQuestionTable), BorderLayout.CENTER);

        // Event listeners
        createResponseQuestionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createResponseQuestionDialog();
            }
        });

        listResponseQuestionsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listResponseQuestions();
            }
        });

        editResponseQuestionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editResponseQuestionDialog();
            }
        });

        deleteResponseQuestionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteResponseQuestionDialog();
            }
        });

        findResponseQuestionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findResponseQuestionByIdDialog();
            }
        });

        setVisible(true);
    }

    private void createResponseQuestionDialog() {
        JTextField responseIdField = new JTextField();
        JTextField subResponseIdField = new JTextField();
        JTextField responseTextField = new JTextField();
        Object[] fields = {
            "Response ID:", responseIdField,
            "Sub Response ID:", subResponseIdField,
            "Response Text:", responseTextField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Response Question", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                long responseId = Long.parseLong(responseIdField.getText());
                long subResponseId = Long.parseLong(subResponseIdField.getText());
                String responseText = responseTextField.getText();

                if (!responseText.isEmpty()) {
                    ResponseQuestion responseQuestion = new ResponseQuestion();
                    responseQuestion.setResponseId(responseId);
                    responseQuestion.setSubResponsesId(subResponseId);
                    responseQuestion.setResponseText(responseText);

                    controller.createResponseQuestion(responseQuestion);
                    JOptionPane.showMessageDialog(this, "Response Question created successfully!");
                    listResponseQuestions(); // Refresh table after creation
                } else {
                    JOptionPane.showMessageDialog(this, "Response Text cannot be empty.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format.");
            }
        }
    }

    private void listResponseQuestions() {
        List<ResponseQuestion> responseQuestions = controller.listAllResponseQuestions();
        tableModel.setRowCount(0); // Clear existing rows
        for (ResponseQuestion responseQuestion : responseQuestions) {
            tableModel.addRow(new Object[]{
                responseQuestion.getId(),
                responseQuestion.getResponseId(),
                responseQuestion.getSubResponsesId(),
                responseQuestion.getResponseText()
            });
        }
    }

    private void editResponseQuestionDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Response Question ID to edit:");
        if (idStr != null) {
            try {
                long id = Long.parseLong(idStr);
                Optional<ResponseQuestion> responseQuestionOpt = controller.findResponseQuestionById(id);
                if (responseQuestionOpt.isPresent()) {
                    ResponseQuestion responseQuestion = responseQuestionOpt.get();

                    JTextField responseIdField = new JTextField(String.valueOf(responseQuestion.getResponseId()));
                    JTextField subResponseIdField = new JTextField(String.valueOf(responseQuestion.getSubResponsesId()));
                    JTextField responseTextField = new JTextField(responseQuestion.getResponseText());
                    Object[] fields = {
                        "Response ID:", responseIdField,
                        "Sub Response ID:", subResponseIdField,
                        "Response Text:", responseTextField
                    };

                    int option = JOptionPane.showConfirmDialog(this, fields, "Edit Response Question", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        responseQuestion.setResponseId(Long.parseLong(responseIdField.getText()));
                        responseQuestion.setSubResponsesId(Long.parseLong(subResponseIdField.getText()));
                        responseQuestion.setResponseText(responseTextField.getText());
                        controller.updateResponseQuestion(responseQuestion, id);
                        JOptionPane.showMessageDialog(this, "Response Question updated successfully!");
                        listResponseQuestions(); // Refresh table after update
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Response Question not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format.");
            }
        }
    }

    private void deleteResponseQuestionDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Response Question ID to delete:");
        if (idStr != null) {
            try {
                long id = Long.parseLong(idStr);
                Optional<ResponseQuestion> responseQuestionOpt = controller.findResponseQuestionById(id);
                if (responseQuestionOpt.isPresent()) {
                    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the response question?", "Delete Response Question", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        controller.deleteResponseQuestion(id);
                        JOptionPane.showMessageDialog(this, "Response Question deleted successfully!");
                        listResponseQuestions(); // Refresh table after deletion
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Response Question not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format.");
            }
        }
    }

    private void findResponseQuestionByIdDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Response Question ID to find:");
        if (idStr != null) {
            try {
                long id = Long.parseLong(idStr);
                Optional<ResponseQuestion> responseQuestionOpt = controller.findResponseQuestionById(id);
                if (responseQuestionOpt.isPresent()) {
                    ResponseQuestion responseQuestion = responseQuestionOpt.get();
                    JTextField responseIdField = new JTextField(String.valueOf(responseQuestion.getResponseId()));
                    JTextField subResponseIdField = new JTextField(String.valueOf(responseQuestion.getSubResponsesId()));
                    JTextField responseTextField = new JTextField(responseQuestion.getResponseText());

                    responseIdField.setEditable(false);
                    subResponseIdField.setEditable(false);
                    responseTextField.setEditable(false);

                    Object[] fields = {
                        "Response ID:", responseIdField,
                        "Sub Response ID:", subResponseIdField,
                        "Response Text:", responseTextField
                    };

                    JOptionPane.showMessageDialog(this, fields, "Response Question Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Response Question not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format.");
            }
        }
    }
}
