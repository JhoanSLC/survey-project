package com.surveyproject.mainUi.adminUi;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.surveyproject.initApplication.InitApplication;

public class AdminUI extends JFrame {

    private InitApplication initApp;

    public AdminUI() {
        initApp = new InitApplication();
        setTitle("Admin Dashboard");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3)); // Adjust the grid layout as needed

        // Add buttons for each table UI
        addButton(panel, "Surveys", () -> initApp.initSurveyUi());
        addButton(panel, "Categories", () -> initApp.initCategoryUi());
        addButton(panel, "Chapter", () -> initApp.initChapterUi());
        addButton(panel, "Questions", () -> initApp.initQuestionsUI());
        addButton(panel, "Response Options", () -> initApp.initReponseOptionsUi());
        addButton(panel, "Response Question", () -> initApp.initResponseQuestionUi());
        addButton(panel, "Roles", () -> initApp.initRolesUi());
        addButton(panel, "Sub Response Options", () -> initApp.initSubResponseUi());
        addButton(panel, "Users", () -> initApp.initUsersUi());
        addButton(panel, "Users Roles", () -> initApp.initUsersRolesUi());

        getContentPane().add(panel);
    }

    private void addButton(JPanel panel, String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
        panel.add(button);
    }

}
