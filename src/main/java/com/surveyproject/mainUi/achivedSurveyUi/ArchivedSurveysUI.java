package com.surveyproject.mainUi.achivedSurveyUi;

import com.surveyproject.database.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ArchivedSurveysUI extends JFrame {
    private DatabaseConnection databaseConnection;
    private JTable surveyHistoryTable;
    private DefaultTableModel tableModel;
    private JTextArea responseTextArea;

    public ArchivedSurveysUI() {
        databaseConnection = new DatabaseConnection();
        setTitle("Archived Surveys Report");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel historyPanel = new JPanel(new BorderLayout());
        JLabel historyLabel = new JLabel("Survey History:");
        tableModel = new DefaultTableModel(new Object[]{"Survey ID", "Survey Name", "Date"}, 0);
        surveyHistoryTable = new JTable(tableModel);
        loadSurveyHistory();

        JScrollPane historyScrollPane = new JScrollPane(surveyHistoryTable);
        historyPanel.add(historyLabel, BorderLayout.NORTH);
        historyPanel.add(historyScrollPane, BorderLayout.CENTER);

        
        JPanel responsePanel = new JPanel(new BorderLayout());
        JLabel responseLabel = new JLabel("Survey Responses:");
        responseTextArea = new JTextArea(10, 50);
        responseTextArea.setEditable(false);
        JScrollPane responseScrollPane = new JScrollPane(responseTextArea);

        responsePanel.add(responseLabel, BorderLayout.NORTH);
        responsePanel.add(responseScrollPane, BorderLayout.CENTER);

        JButton viewResponsesButton = new JButton("View Responses");
        viewResponsesButton.addActionListener(e -> showSurveyResponses());

        
        add(historyPanel, BorderLayout.NORTH);
        add(viewResponsesButton, BorderLayout.CENTER);
        add(responsePanel, BorderLayout.SOUTH);
    }

    private void loadSurveyHistory() {
        try (Connection con = databaseConnection.connectDatabase();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT s.id, s.name, rq.createdAt " +
                             "FROM surveys s " +
                             "JOIN responseQuestion rq ON rq.responseId = s.id " +
                             "GROUP BY s.id, s.name, rq.createdAt")) {

            while (rs.next()) {
                int surveyId = rs.getInt("id");
                String surveyName = rs.getString("name");
                Timestamp timestamp = rs.getTimestamp("createdAt");
                tableModel.addRow(new Object[]{surveyId, surveyName, timestamp});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showSurveyResponses() {
        int selectedRow = surveyHistoryTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a survey from the history.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int surveyId = (int) tableModel.getValueAt(selectedRow, 0);
        loadSurveyResponses(surveyId);
    }

    private void loadSurveyResponses(int surveyId) {
        responseTextArea.setText("");

        try (Connection con = databaseConnection.connectDatabase();
             PreparedStatement stmt = con.prepareStatement(
                     "SELECT q.questionText, rq.responseText " +
                             "FROM questions q " +
                             "JOIN responseQuestion rq ON rq.responseId = q.id " +
                             "JOIN chapter c ON q.chapterId = c.id " +
                             "JOIN surveys s ON c.surveyId = s.id " +
                             "WHERE s.id = ?")) {
            stmt.setInt(1, surveyId);
            try (ResultSet rs = stmt.executeQuery()) {
                StringBuilder responses = new StringBuilder();

                while (rs.next()) {
                    String question = rs.getString("questionText");
                    String response = rs.getString("responseText");
                    responses.append("Q: ").append(question).append("\n");
                    responses.append("A: ").append(response).append("\n\n");
                }

                responseTextArea.setText(responses.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
