package com.surveyproject.chapter.UI;

import com.surveyproject.chapter.domain.entity.Chapter;
import com.surveyproject.chapter.infrastructure.controller.ChapterController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class ChapterUI extends JFrame {
    private final ChapterController controller;
    private DefaultTableModel tableModel;
    private JTable chapterTable;

    public ChapterUI(ChapterController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Chapter Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createChapterItem = new JMenuItem("Create Chapter");
        JMenuItem listChaptersItem = new JMenuItem("List Chapters");
        JMenuItem editChapterItem = new JMenuItem("Edit Chapter");
        JMenuItem deleteChapterItem = new JMenuItem("Delete Chapter");
        JMenuItem findChapterItem = new JMenuItem("Find Chapter by ID");
        JMenuItem closeItem = new JMenuItem("Close");

        menu.add(createChapterItem);
        menu.add(listChaptersItem);
        menu.add(editChapterItem);
        menu.add(deleteChapterItem);
        menu.add(findChapterItem);
        menu.add(closeItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display chapters
        tableModel = new DefaultTableModel(new String[]{"ID", "Survey ID", "Chapter Number", "Chapter Title", "Created At", "Updated At"}, 0);
        chapterTable = new JTable(tableModel);
        add(new JScrollPane(chapterTable), BorderLayout.CENTER);

        // Event listeners
        createChapterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChapterDialog();
            }
        });

        listChaptersItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listChapters();
            }
        });

        editChapterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editChapterDialog();
            }
        });

        deleteChapterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteChapterDialog();
            }
        });

        findChapterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findChapterByIdDialog();
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

    private void createChapterDialog() {
        JTextField surveyIdField = new JTextField();
        JTextField chapterNumberField = new JTextField();
        JTextField chapterTitleField = new JTextField();
        Object[] fields = {
            "Survey ID:", surveyIdField,
            "Chapter Number:", chapterNumberField,
            "Chapter Title:", chapterTitleField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Chapter", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                long surveyId = Long.parseLong(surveyIdField.getText());
                String chapterNumber = chapterNumberField.getText();
                String chapterTitle = chapterTitleField.getText();

                if (!chapterNumber.isEmpty() && !chapterTitle.isEmpty()) {
                    Chapter chapter = new Chapter();
                    chapter.setSurveyId(surveyId);
                    chapter.setChapterNumber(chapterNumber);
                    chapter.setChapterTitle(chapterTitle);
                    controller.createChapter(chapter);
                    JOptionPane.showMessageDialog(this, "Chapter created successfully!");
                    listChapters(); // Refresh the table after creation
                } else {
                    JOptionPane.showMessageDialog(this, "Chapter Number and Title cannot be empty.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Survey ID.");
            }
        }
    }

    private void listChapters() {
        List<Chapter> chapters = controller.listAllChapters();
        tableModel.setRowCount(0); // Clear existing rows
        for (Chapter chapter : chapters) {
            tableModel.addRow(new Object[]{
                chapter.getId(),
                chapter.getSurveyId(),
                chapter.getChapterNumber(),
                chapter.getChapterTitle(),
                chapter.getCreatedAt(),
                chapter.getUpdatedAt()
            });
        }
    }

    private void editChapterDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Chapter ID to edit:");
        if (idStr != null) {
            try {
                long id = Long.parseLong(idStr);
                Optional<Chapter> chapterOpt = controller.findChapterById(id);
                if (chapterOpt.isPresent()) {
                    Chapter chapter = chapterOpt.get();

                    JTextField surveyIdField = new JTextField(String.valueOf(chapter.getSurveyId()));
                    JTextField chapterNumberField = new JTextField(chapter.getChapterNumber());
                    JTextField chapterTitleField = new JTextField(chapter.getChapterTitle());
                    Object[] fields = {
                        "Survey ID:", surveyIdField,
                        "Chapter Number:", chapterNumberField,
                        "Chapter Title:", chapterTitleField
                    };

                    int option = JOptionPane.showConfirmDialog(this, fields, "Edit Chapter", JOptionPane.OK_CANCEL_OPTION);
                    if (option == JOptionPane.OK_OPTION) {
                        chapter.setSurveyId(Long.parseLong(surveyIdField.getText()));
                        chapter.setChapterNumber(chapterNumberField.getText());
                        chapter.setChapterTitle(chapterTitleField.getText());
                        controller.updateChapter(chapter, id);
                        JOptionPane.showMessageDialog(this, "Chapter updated successfully!");
                        listChapters(); // Refresh the table after update
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chapter not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Chapter ID.");
            }
        }
    }

    private void deleteChapterDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Chapter ID to delete:");
        if (idStr != null) {
            try {
                long id = Long.parseLong(idStr);
                Optional<Chapter> chapterOpt = controller.findChapterById(id);
                if (chapterOpt.isPresent()) {
                    int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the chapter?", "Delete Chapter", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        controller.deleteChapter(id);
                        JOptionPane.showMessageDialog(this, "Chapter deleted successfully!");
                        listChapters(); // Refresh the table after deletion
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Chapter not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Chapter ID.");
            }
        }
    }

    private void findChapterByIdDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Chapter ID to find:");
        if (idStr != null) {
            try {
                long id = Long.parseLong(idStr);
                Optional<Chapter> chapterOpt = controller.findChapterById(id);
                if (chapterOpt.isPresent()) {
                    Chapter chapter = chapterOpt.get();
                    JTextField surveyIdField = new JTextField(String.valueOf(chapter.getSurveyId()));
                    JTextField chapterNumberField = new JTextField(chapter.getChapterNumber());
                    JTextField chapterTitleField = new JTextField(chapter.getChapterTitle());
                    JTextField createdField = new JTextField(chapter.getCreatedAt().toString());
                    JTextField updatedField = new JTextField(chapter.getUpdatedAt().toString());

                    surveyIdField.setEditable(false);
                    chapterNumberField.setEditable(false);
                    chapterTitleField.setEditable(false);
                    createdField.setEditable(false);
                    updatedField.setEditable(false);

                    Object[] fields = {
                        "Survey ID:", surveyIdField,
                        "Chapter Number:", chapterNumberField,
                        "Chapter Title:", chapterTitleField,
                        "Created At:", createdField,
                        "Updated At:", updatedField
                    };

                    JOptionPane.showMessageDialog(this, fields, "Chapter Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Chapter not found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Chapter ID.");
            }
        }
    }
}
