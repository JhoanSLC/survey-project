package com.surveyproject.surveys.UI;

import com.surveyproject.surveys.domain.entity.Surveys;
import com.surveyproject.surveys.infrastructure.controller.SurveysController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class SurveysUI extends JFrame {
    private final SurveysController controller;
    private DefaultTableModel tableModel;
    private JTable surveyTable;

    public SurveysUI(SurveysController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Survey Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Solo cierra la ventana actual
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createSurveyItem = new JMenuItem("Create Survey");
        JMenuItem listSurveysItem = new JMenuItem("List Surveys");
        JMenuItem editSurveyItem = new JMenuItem("Edit Survey");
        JMenuItem deleteSurveyItem = new JMenuItem("Delete Survey");
        JMenuItem findSurveyItem = new JMenuItem("Find Survey by ID");
        JMenuItem closeItem = new JMenuItem("Close");

        menu.add(createSurveyItem);
        menu.add(listSurveysItem);
        menu.add(editSurveyItem);
        menu.add(deleteSurveyItem);
        menu.add(findSurveyItem);
        menu.add(closeItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display surveys
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Description", "Created At", "Updated At"}, 0);
        surveyTable = new JTable(tableModel);
        add(new JScrollPane(surveyTable), BorderLayout.CENTER);

        // Event listeners
        createSurveyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSurveyDialog();
            }
        });

        listSurveysItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listSurveys();
            }
        });

        editSurveyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editSurveyDialog();
            }
        });

        deleteSurveyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSurveyDialog();
            }
        });

        findSurveyItem.addActionListener(new ActionListener() { // Listener para buscar por ID
            @Override
            public void actionPerformed(ActionEvent e) {
                findSurveyByIdDialog();
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

    private void createSurveyDialog() {
        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        Object[] fields = {
            "Name:", nameField,
            "Description:", descriptionField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Survey", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String description = descriptionField.getText();

            if (!name.isEmpty() && !description.isEmpty()) {
                Surveys survey = new Surveys(0, null, null, description, name);
                controller.createSurvey(survey);
                JOptionPane.showMessageDialog(this, "Survey created successfully!");
                listSurveys(); // Refrescar la tabla después de crear
            } else {
                JOptionPane.showMessageDialog(this, "Name and Description cannot be empty.");
            }
        }
    }

    private void listSurveys() {
        List<Surveys> surveys = controller.listAllSurveys();
        tableModel.setRowCount(0); // Clear existing rows
        for (Surveys survey : surveys) {
            tableModel.addRow(new Object[]{
                survey.getId(),
                survey.getName(),
                survey.getDescription(),
                survey.getCreated_at(),
                survey.getUpdated_at()
            });
        }
    }

    private void editSurveyDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Survey ID to edit:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Surveys> surveyOpt = controller.findSurveyById(id);
            if (surveyOpt.isPresent()) {
                Surveys survey = surveyOpt.get();

                JTextField nameField = new JTextField(survey.getName());
                JTextField descriptionField = new JTextField(survey.getDescription());
                Object[] fields = {
                    "Name:", nameField,
                    "Description:", descriptionField
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit Survey", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    survey.setName(nameField.getText());
                    survey.setDescription(descriptionField.getText());
                    controller.updateSurvey(survey, Long.parseLong(idStr));
                    JOptionPane.showMessageDialog(this, "Survey updated successfully!");
                    listSurveys(); // Refrescar la tabla después de actualizar
                }
            } else {
                JOptionPane.showMessageDialog(this, "Survey not found with ID: " + id);
            }
        }
    }

    private void deleteSurveyDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Survey ID to delete:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Surveys> surveyOpt = controller.findSurveyById(id);
            if (surveyOpt.isPresent()) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the survey?", "Delete Survey", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.deleteSurvey(id);
                    JOptionPane.showMessageDialog(this, "Survey deleted successfully!");
                    listSurveys(); // Refrescar la tabla después de eliminar
                }
            } else {
                JOptionPane.showMessageDialog(this, "Survey not found with ID: " + id);
            }
        }
    }

    private void findSurveyByIdDialog() { // Nueva función para buscar por ID
        String idStr = JOptionPane.showInputDialog(this, "Enter Survey ID to find:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<Surveys> surveyOpt = controller.findSurveyById(id);
            if (surveyOpt.isPresent()) {
                Surveys survey = surveyOpt.get();
                JTextField nameField = new JTextField(survey.getName());
                JTextField descriptionField = new JTextField(survey.getDescription());
                JTextField createdField = new JTextField(survey.getCreated_at().toString());
                JTextField updatedField = new JTextField(survey.getUpdated_at().toString());

                nameField.setEditable(false);
                descriptionField.setEditable(false);
                createdField.setEditable(false);
                updatedField.setEditable(false);

                Object[] fields = {
                    "Name:", nameField,
                    "Description:", descriptionField,
                    "Created At:", createdField,
                    "Updated At:", updatedField
                };

                JOptionPane.showMessageDialog(this, fields, "Survey Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Survey not found with ID: " + id);
            }
        }
    }
}
