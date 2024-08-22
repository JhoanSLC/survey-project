package com.surveyproject.questions.UI;

import com.surveyproject.questions.domain.entity.Questions;
import com.surveyproject.questions.infrastructure.controller.QuestionsController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class QuestionsUI extends JFrame {
    private final QuestionsController controller;
    private DefaultTableModel tableModel;
    private JTable questionTable;

    public QuestionsUI(QuestionsController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Question Management");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createQuestionItem = new JMenuItem("Create Question");
        JMenuItem listQuestionsItem = new JMenuItem("List Questions");
        JMenuItem editQuestionItem = new JMenuItem("Edit Question");
        JMenuItem deleteQuestionItem = new JMenuItem("Delete Question");
        JMenuItem findQuestionItem = new JMenuItem("Find Question by ID");

        menu.add(createQuestionItem);
        menu.add(listQuestionsItem);
        menu.add(editQuestionItem);
        menu.add(deleteQuestionItem);
        menu.add(findQuestionItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display questions
        tableModel = new DefaultTableModel(new String[]{"ID", "Chapter ID", "Question Number", "Response Type", "Comment", "Question Text", "Created At", "Updated At"}, 0);
        questionTable = new JTable(tableModel);
        add(new JScrollPane(questionTable), BorderLayout.CENTER);

        // Event listeners
        createQuestionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createQuestionDialog();
            }
        });

        listQuestionsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listQuestions();
            }
        });

        editQuestionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editQuestionDialog();
            }
        });

        deleteQuestionItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteQuestionDialog();
            }
        });

        findQuestionItem.addActionListener(new ActionListener() { // Listener para buscar por ID
            @Override
            public void actionPerformed(ActionEvent e) {
                findQuestionByIdDialog();
            }
        });

        setVisible(true);
    }

    private void createQuestionDialog() {
        JTextField chapterIdField = new JTextField();
        JTextField questionNumberField = new JTextField();
        JTextField responseTypeField = new JTextField();
        JTextField commentField = new JTextField();
        JTextField questionTextField = new JTextField();

        Object[] fields = {
            "Chapter ID:", chapterIdField,
            "Question Number:", questionNumberField,
            "Response Type:", responseTypeField,
            "Comment:", commentField,
            "Question Text:", questionTextField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Question", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            long chapterId = Long.parseLong(chapterIdField.getText());
            String questionNumber = questionNumberField.getText();
            String responseType = responseTypeField.getText();
            String comment = commentField.getText();
            String questionText = questionTextField.getText();

            if (!questionNumber.isEmpty() && !responseType.isEmpty() && !comment.isEmpty() && !questionText.isEmpty()) {
                Questions question = new Questions();
                question.setChapterId(chapterId);
                question.setQuestionNumber(questionNumber);
                question.setResponseType(responseType);
                question.setCommentQuestion(comment);
                question.setQuestionText(questionText);

                controller.createQuestion(question);
                JOptionPane.showMessageDialog(this, "Question created successfully!");
                listQuestions(); // Refrescar la tabla después de crear
            } else {
                JOptionPane.showMessageDialog(this, "All fields must be filled.");
            }
        }
    }

    private void listQuestions() {
        List<Questions> questions = controller.listAllQuestions();
        tableModel.setRowCount(0); // Clear existing rows
        for (Questions question : questions) {
            tableModel.addRow(new Object[]{
                question.getId(),
                question.getChapterId(),
                question.getQuestionNumber(),
                question.getResponseType(),
                question.getCommentQuestion(),
                question.getQuestionText(),
                question.getCreatedAt(),
                question.getUpdatedAt()
            });
        }
    }

    private void editQuestionDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Question ID to edit:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Questions> questionOpt = controller.findQuestionById(id);
            if (questionOpt.isPresent()) {
                Questions question = questionOpt.get();

                JTextField chapterIdField = new JTextField(String.valueOf(question.getChapterId()));
                JTextField questionNumberField = new JTextField(question.getQuestionNumber());
                JTextField responseTypeField = new JTextField(question.getResponseType());
                JTextField commentField = new JTextField(question.getCommentQuestion());
                JTextField questionTextField = new JTextField(question.getQuestionText());

                Object[] fields = {
                    "Chapter ID:", chapterIdField,
                    "Question Number:", questionNumberField,
                    "Response Type:", responseTypeField,
                    "Comment:", commentField,
                    "Question Text:", questionTextField
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit Question", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    question.setChapterId(Long.parseLong(chapterIdField.getText()));
                    question.setQuestionNumber(questionNumberField.getText());
                    question.setResponseType(responseTypeField.getText());
                    question.setCommentQuestion(commentField.getText());
                    question.setQuestionText(questionTextField.getText());

                    controller.updateQuestion(question, id);
                    JOptionPane.showMessageDialog(this, "Question updated successfully!");
                    listQuestions(); // Refrescar la tabla después de actualizar
                }
            } else {
                JOptionPane.showMessageDialog(this, "Question not found with ID: " + id);
            }
        }
    }

    private void deleteQuestionDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Question ID to delete:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Questions> questionOpt = controller.findQuestionById(id);
            if (questionOpt.isPresent()) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the question?", "Delete Question", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.deleteQuestion(id);
                    JOptionPane.showMessageDialog(this, "Question deleted successfully!");
                    listQuestions(); // Refrescar la tabla después de eliminar
                }
            } else {
                JOptionPane.showMessageDialog(this, "Question not found with ID: " + id);
            }
        }
    }

    private void findQuestionByIdDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Question ID to find:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Questions> questionOpt = controller.findQuestionById(id);
            if (questionOpt.isPresent()) {
                Questions question = questionOpt.get();
                JOptionPane.showMessageDialog(this, "Question Details:\n" +
                        "ID: " + question.getId() + "\n" +
                        "Chapter ID: " + question.getChapterId() + "\n" +
                        "Question Number: " + question.getQuestionNumber() + "\n" +
                        "Response Type: " + question.getResponseType() + "\n" +
                        "Comment: " + question.getCommentQuestion() + "\n" +
                        "Question Text: " + question.getQuestionText() + "\n" +
                        "Created At: " + question.getCreatedAt() + "\n" +
                        "Updated At: " + question.getUpdatedAt());
            } else {
                JOptionPane.showMessageDialog(this, "Question not found with ID: " + id);
            }
        }
    }
}
