package com.surveyproject.mainUi.userUi;

import com.surveyproject.database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;


public class SurveyMenuUI extends JFrame {
    private DatabaseConnection databaseConnection;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> surveyComboBox;
    private JComboBox<String> chapterComboBox;
    private JComboBox<String> questionComboBox;
    private JTextArea answerTextArea;

    public SurveyMenuUI() {
        databaseConnection = new DatabaseConnection();
        setTitle("Survey Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        showCategorySelection();
    }

    private void showCategorySelection() {
        JPanel categoryPanel = new JPanel(new BorderLayout());
        categoryComboBox = new JComboBox<>(getCategories());
        JButton nextButton = new JButton("Next");
        JButton backButton = new JButton("Back");

        categoryPanel.add(new JLabel("Select a Category"), BorderLayout.NORTH);
        categoryPanel.add(categoryComboBox, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        categoryPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(categoryPanel);

        nextButton.addActionListener(e -> showSurveySelection());
        backButton.addActionListener(e -> {
            SurveyMainUI mainUI = new SurveyMainUI();
            mainUI.setVisible(true);
            dispose(); // Volver a la pantalla principal
        });
    }

    private void showSurveySelection() {
        removeCurrentPanel();
        JPanel surveyPanel = new JPanel(new BorderLayout());
        surveyComboBox = new JComboBox<>(getSurveysByCategory((String) categoryComboBox.getSelectedItem()));
        JButton nextButton = new JButton("Next");
        JButton backButton = new JButton("Back");

        surveyPanel.add(new JLabel("Select a Survey"), BorderLayout.NORTH);
        surveyPanel.add(surveyComboBox, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        surveyPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(surveyPanel);
        revalidate();
        repaint();

        nextButton.addActionListener(e -> showChapterSelection());
        backButton.addActionListener(e -> showCategorySelection());
    }

    private void showChapterSelection() {
        removeCurrentPanel();
        JPanel chapterPanel = new JPanel(new BorderLayout());
        chapterComboBox = new JComboBox<>(getChaptersBySurvey((String) surveyComboBox.getSelectedItem()));
        JButton nextButton = new JButton("Next");
        JButton backButton = new JButton("Back");

        chapterPanel.add(new JLabel("Select a Chapter"), BorderLayout.NORTH);
        chapterPanel.add(chapterComboBox, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        chapterPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(chapterPanel);
        revalidate();
        repaint();

        nextButton.addActionListener(e -> showQuestionSelection());
        backButton.addActionListener(e -> showSurveySelection());
    }

    private void showQuestionSelection() {
        removeCurrentPanel();
        JPanel questionPanel = new JPanel(new BorderLayout());
        questionComboBox = new JComboBox<>(getQuestionsByChapter((String) chapterComboBox.getSelectedItem()));
        JButton nextButton = new JButton("Next");
        JButton backButton = new JButton("Back");

        questionPanel.add(new JLabel("Select a Question"), BorderLayout.NORTH);
        questionPanel.add(questionComboBox, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        questionPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(questionPanel);
        revalidate();
        repaint();

        nextButton.addActionListener(e -> showAnswerInput());
        backButton.addActionListener(e -> showChapterSelection());
    }

    private void showAnswerInput() {
        removeCurrentPanel();
        JPanel answerPanel = new JPanel(new BorderLayout());
        answerTextArea = new JTextArea(5, 30);
        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        answerPanel.add(new JLabel("Provide your answer:"), BorderLayout.NORTH);
        answerPanel.add(new JScrollPane(answerTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);
        answerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(answerPanel);
        revalidate();
        repaint();

        submitButton.addActionListener(e -> handleSubmitAnswer());
        backButton.addActionListener(e -> showQuestionSelection());
    }

    private void handleSubmitAnswer() {
        String selectedQuestion = (String) questionComboBox.getSelectedItem();
        String answer = answerTextArea.getText();
        if (answer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Answer cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save the answer to the database
        saveAnswer(selectedQuestion, answer);

        int response = JOptionPane.showConfirmDialog(this, "Do you want to answer another question in this chapter?", "Continue?", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            showQuestionSelection();
        } else {
            response = JOptionPane.showConfirmDialog(this, "Do you want to go back to the chapters of this survey?", "Continue?", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                showChapterSelection();
            } else {
                showSurveySelection();
            }
        }
    }

    // Methods to get categories, surveys, chapters, and questions from the database
    private String[] getCategories() {
        ArrayList<String> categories = new ArrayList<>();
        try (Connection con = databaseConnection.connectDatabase();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM categoriesCatalog")) {
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories.toArray(new String[0]);
    }

    private String[] getSurveysByCategory(String category) {
        ArrayList<String> surveys = new ArrayList<>();
        try (Connection con = databaseConnection.connectDatabase();
             PreparedStatement stmt = con.prepareStatement(
                     "SELECT s.name FROM surveys s " +
                             "JOIN responseOptions ro ON s.id = ro.questionId " +
                             "JOIN categoriesCatalog cc ON ro.categoryCatalogId = cc.id " +
                             "WHERE cc.name = ?")) {
            stmt.setString(1, category);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    surveys.add(rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surveys.toArray(new String[0]);
    }

    private String[] getChaptersBySurvey(String survey) {
        ArrayList<String> chapters = new ArrayList<>();
        try (Connection con = databaseConnection.connectDatabase();
             PreparedStatement stmt = con.prepareStatement(
                     "SELECT chapterTitle FROM chapter c " +
                             "JOIN surveys s ON c.surveyId = s.id " +
                             "WHERE s.name = ?")) {
            stmt.setString(1, survey);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    chapters.add(rs.getString("chapterTitle"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters.toArray(new String[0]);
    }

    private String[] getQuestionsByChapter(String chapter) {
        ArrayList<String> questions = new ArrayList<>();
        try (Connection con = databaseConnection.connectDatabase();
             PreparedStatement stmt = con.prepareStatement(
                     "SELECT questionText FROM questions q " +
                             "JOIN chapter c ON q.chapterId = c.id " +
                             "WHERE c.chapterTitle = ?")) {
            stmt.setString(1, chapter);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    questions.add(rs.getString("questionText"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions.toArray(new String[0]);
    }

    private void saveAnswer(String question, String answer) {
        try (Connection con = databaseConnection.connectDatabase();
             PreparedStatement stmt = con.prepareStatement(
                     "INSERT INTO responseQuestion (responseText) VALUES (?)")) {
            stmt.setString(1, answer);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Answer saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save the answer", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeCurrentPanel() {
        getContentPane().removeAll();
    }
}
