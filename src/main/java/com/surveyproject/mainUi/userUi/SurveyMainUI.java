package com.surveyproject.mainUi.userUi;

import javax.swing.*;
import java.awt.*;
import com.surveyproject.mainUi.achivedSurveyUi.ArchivedSurveysUI;;;

public class SurveyMainUI extends JFrame {
    public SurveyMainUI() {
        setTitle("Survey System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

        // Agregar componentes a la interfaz
        JLabel welcomeLabel = new JLabel("Welcome to the Survey System", SwingConstants.CENTER);
        JButton respondSurveyButton = new JButton("Respond Survey");
        JButton viewHistoryButton = new JButton("View Survey History");

        add(welcomeLabel);
        add(respondSurveyButton);
        add(viewHistoryButton);

        // Acción para "Responder Encuesta"
        respondSurveyButton.addActionListener(e -> {
            SurveyMenuUI surveyMenu = new SurveyMenuUI();
            surveyMenu.setVisible(true);
            dispose(); // Cerrar esta ventana
        });

        // Acción para "Ver Historial de Encuestas"
        viewHistoryButton.addActionListener(e -> {
            ArchivedSurveysUI archivedSurveys = new ArchivedSurveysUI();
            archivedSurveys.setVisible(true);
            dispose(); // Cerrar esta ventana
        });
    }
}
