package com.surveyproject.mainUi.userUi;

import com.surveyproject.database.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class SurveyHistoryUI extends JFrame {
    private DatabaseConnection databaseConnection;
    private JComboBox<String> historyComboBox;
    private JTextArea answersTextArea;

    public SurveyHistoryUI() {
        databaseConnection = new DatabaseConnection();
        setTitle("Survey History");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        historyComboBox = new JComboBox<>(getSurveyHistory());
        JButton viewButton = new JButton("View Responses");
        answersTextArea = new JTextArea(15, 50);
        answersTextArea.setEditable(false);

        add(new JLabel("Select Survey:"), BorderLayout.NORTH);
        add(historyComboBox, BorderLayout.CENTER);
        add(viewButton, BorderLayout.SOUTH);
        add(new JScrollPane(answersTextArea), BorderLayout.EAST);

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResponses((String) historyComboBox.getSelectedItem());
            }
        });

        setVisible(true);
    }

    private String[] getSurveyHistory() {
        ArrayList<String> surveys = new ArrayList<>();
        try (Connection con = databaseConnection.connectDatabase();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT DISTINCT s.name FROM surveys s")) {
            while (rs.next()) {
                surveys.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surveys.toArray(new String[0]);
    }

    private void showResponses(String surveyName) {
        if (surveyName == null) return;
        StringBuilder responses = new StringBuilder();
        try (Connection con = databaseConnection.connectDatabase();
             PreparedStatement stmt = con.prepareStatement(
                     "SELECT q.questionText, rq.responseText " +
                     "FROM responseQuestion rq " +
                     "JOIN questions q ON rq.responseId = q.id " +
                     "WHERE q.chapterId IN (" +
                     "    SELECT c.id FROM chapter c " +
                     "    JOIN surveys s ON c.surveyId = s.id " +
                     "    WHERE s.name = ? " +
                     ")")) {
            stmt.setString(1, surveyName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    responses.append("Question: ").append(rs.getString("questionText")).append("\n");
                    responses.append("Answer: ").append(rs.getString("responseText")).append("\n\n");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        answersTextArea.setText(responses.toString());
    }
}
