package com.surveyproject.mainUi.userUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserOptionsUI extends JFrame {
    public UserOptionsUI() {
        setTitle("User Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        JButton respondSurveysButton = new JButton("Respond Surveys");
        JButton viewHistoryButton = new JButton("View History");

        add(respondSurveysButton);
        add(viewHistoryButton);

        respondSurveysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this window
                new SurveyMenuUI().setVisible(true); // Show survey menu
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close this window
                new SurveyHistoryUI().setVisible(true); // Show survey history UI
            }
        });

        setVisible(true);
    }
}
