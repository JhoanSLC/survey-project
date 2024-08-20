package com.surveyproject.categoriesCatalog.infrasctructure.controller;

import com.surveyproject.categoriesCatalog.application.*;
import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;
import com.surveyproject.categoriesCatalog.infrasctructure.repository.CategoriesCatalogRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class CategoriesCatalogController extends JFrame {
    private final CategoriesCatalogService categoryService;
    private final CreateCategoryUC createCategoryUC;
    private final DeleteCategoryUC deleteCategoryUC;
    private final FindCategoryByIdUC findCategoryByIdUC;
    private final UpdateCategoryUC updateCategoryUC;
    private final ListAllSurveysUC listAllCategoriesUC;

    public CategoriesCatalogController() {
        this.categoryService = new CategoriesCatalogRepository();
        this.createCategoryUC = new CreateCategoryUC(categoryService);
        this.deleteCategoryUC = new DeleteCategoryUC(categoryService);
        this.findCategoryByIdUC = new FindCategoryByIdUC(categoryService);
        this.updateCategoryUC = new UpdateCategoryUC(categoryService);
        this.listAllCategoriesUC = new ListAllSurveysUC(categoryService);

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Categories Catalog Management");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 3));

        JButton createButton = new JButton("Create Category");
        JButton findButton = new JButton("Find by ID");
        JButton listButton = new JButton("List All Categories");
        JButton updateButton = new JButton("Update Category");
        JButton deleteButton = new JButton("Delete Category");

        add(createButton);
        add(findButton);
        add(listButton);
        add(updateButton);
        add(deleteButton);

        createButton.addActionListener(e -> openCreateWindow());
        findButton.addActionListener(e -> openFindByIdWindow());
        listButton.addActionListener(e -> openListAllWindow());
        updateButton.addActionListener(e -> openUpdateWindow());
        deleteButton.addActionListener(e -> openDeleteWindow());

        setVisible(true);
    }

    private void openCreateWindow() {
        JFrame createFrame = new JFrame("Create Category");
        createFrame.setSize(400, 300);
        createFrame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Category Name:");
        JTextField nameField = new JTextField();

        JButton submitButton = new JButton("Create");
        JButton cancelButton = new JButton("Cancel");

        createFrame.add(nameLabel);
        createFrame.add(nameField);
        createFrame.add(submitButton);
        createFrame.add(cancelButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(createFrame, "Category name is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                CategoriesCatalog category = new CategoriesCatalog();
                category.setName(name);
                createCategoryUC.create(category);
                JOptionPane.showMessageDialog(createFrame, "Category created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                createFrame.dispose();
            }
        });

        cancelButton.addActionListener(e -> createFrame.dispose());

        createFrame.setVisible(true);
    }

    private void openFindByIdWindow() {
        JFrame findFrame = new JFrame("Find Category by ID");
        findFrame.setSize(400, 200);
        findFrame.setLayout(new GridLayout(3, 2));

        JLabel idLabel = new JLabel("Category ID:");
        JTextField idField = new JTextField();
        JButton submitButton = new JButton("Find");
        JButton cancelButton = new JButton("Cancel");

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        findFrame.add(idLabel);
        findFrame.add(idField);
        findFrame.add(submitButton);
        findFrame.add(cancelButton);
        findFrame.add(new JLabel()); // Empty cell for spacing
        findFrame.add(resultArea);

        submitButton.addActionListener(e -> {
            try {
                long id = Long.parseLong(idField.getText());
                Optional<CategoriesCatalog> category = findCategoryByIdUC.findById(id);

                if (category.isPresent()) {
                    CategoriesCatalog found = category.get();
                    resultArea.setText(String.format("ID: %d\nName: %s\nCreated At: %s\nUpdated At: %s\n",
                            found.getId(), found.getName(), found.getCreatedAt(), found.getUpdatedAt()));
                } else {
                    resultArea.setText("Category not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(findFrame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> findFrame.dispose());

        findFrame.setVisible(true);
    }

    private void openListAllWindow() {
        JFrame listFrame = new JFrame("List All Categories");
        listFrame.setSize(600, 400);
        listFrame.setLayout(new BorderLayout());

        String[] columns = {"ID", "Name", "Created At", "Updated At"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);

        List<CategoriesCatalog> categories = listAllCategoriesUC.listAllSurveys();
        for (CategoriesCatalog category : categories) {
            tableModel.addRow(new Object[]{
                    category.getId(),
                    category.getName(),
                    category.getCreatedAt(),
                    category.getUpdatedAt()
            });
        }

        JScrollPane scrollPane = new JScrollPane(table);
        listFrame.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> listFrame.dispose());

        listFrame.add(closeButton, BorderLayout.SOUTH);

        listFrame.setVisible(true);
    }

    private void openUpdateWindow() {
        JFrame idFrame = new JFrame("Enter ID to Update");
        idFrame.setSize(400, 200);
        idFrame.setLayout(new GridLayout(3, 2));

        JLabel idLabel = new JLabel("Category ID:");
        JTextField idField = new JTextField();
        JButton submitButton = new JButton("Next");
        JButton cancelButton = new JButton("Cancel");

        idFrame.add(idLabel);
        idFrame.add(idField);
        idFrame.add(submitButton);
        idFrame.add(cancelButton);

        submitButton.addActionListener(e -> {
            try {
                long id = Long.parseLong(idField.getText());
                Optional<CategoriesCatalog> category = findCategoryByIdUC.findById(id);

                if (category.isPresent()) {
                    CategoriesCatalog foundCategory = category.get();
                    idFrame.dispose();
                    openEditCategoryWindow(foundCategory);
                } else {
                    JOptionPane.showMessageDialog(idFrame, "Category not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(idFrame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> idFrame.dispose());

        idFrame.setVisible(true);
    }

    private void openEditCategoryWindow(CategoriesCatalog category) {
        JFrame editFrame = new JFrame("Update Category");
        editFrame.setSize(400, 300);
        editFrame.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Category Name:");
        JTextField nameField = new JTextField(category.getName());

        JButton updateButton = new JButton("Update");
        JButton cancelButton = new JButton("Cancel");

        editFrame.add(nameLabel);
        editFrame.add(nameField);
        editFrame.add(updateButton);
        editFrame.add(cancelButton);

        updateButton.addActionListener(e -> {
            String newName = nameField.getText();
            if (newName.isEmpty()) {
                JOptionPane.showMessageDialog(editFrame, "Category name is required", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                category.setName(newName);
                updateCategoryUC.update(category);
                JOptionPane.showMessageDialog(editFrame, "Category updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                editFrame.dispose();
            }
        });

        cancelButton.addActionListener(e -> editFrame.dispose());

        editFrame.setVisible(true);
    }

    private void openDeleteWindow() {
        JFrame deleteFrame = new JFrame("Delete Category");
        deleteFrame.setSize(400, 200);
        deleteFrame.setLayout(new GridLayout(3, 2));

        JLabel idLabel = new JLabel("Category ID:");
        JTextField idField = new JTextField();
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");

        deleteFrame.add(idLabel);
        deleteFrame.add(idField);
        deleteFrame.add(deleteButton);
        deleteFrame.add(cancelButton);

        deleteButton.addActionListener(e -> {
            try {
                long id = Long.parseLong(idField.getText());
                deleteCategoryUC.delete(id);
                JOptionPane.showMessageDialog(deleteFrame, "Category deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                deleteFrame.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(deleteFrame, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> deleteFrame.dispose());

        deleteFrame.setVisible(true);
    }
}