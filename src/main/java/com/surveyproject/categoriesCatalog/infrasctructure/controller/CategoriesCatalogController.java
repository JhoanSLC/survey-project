package com.surveyproject.categoriesCatalog.infrasctructure.controller;

import com.surveyproject.categoriesCatalog.application.*;
import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;
import com.surveyproject.categoriesCatalog.infrasctructure.repository.CategoriesCatalogRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 3, 10, 10)); // Padding between buttons
        getContentPane().setBackground(new Color(60, 63, 65)); // Background color

        // Create buttons with styles
        JButton createButton = createStyledButton("Create Category", new Color(0, 123, 255));
        JButton findButton = createStyledButton("Find by ID", new Color(40, 167, 69));
        JButton listButton = createStyledButton("List All Categories", new Color(255, 193, 7));
        JButton updateButton = createStyledButton("Update Category", new Color(23, 162, 184));
        JButton deleteButton = createStyledButton("Delete Category", new Color(220, 53, 69));

        // Add buttons to the main window
        add(createButton);
        add(findButton);
        add(listButton);
        add(updateButton);
        add(deleteButton);

        // Add event listeners to each button
        createButton.addActionListener(e -> openCreateWindow());
        findButton.addActionListener(e -> openFindByIdWindow());
        listButton.addActionListener(e -> openListAllWindow());
        updateButton.addActionListener(e -> openUpdateWindow());
        deleteButton.addActionListener(e -> openDeleteWindow());

        setVisible(true);
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(35, 37, 38), 2), 
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return button;
    }

    private void openCreateWindow() {
        JFrame createFrame = new JFrame("Create Category");
        createFrame.setSize(400, 300);
        createFrame.setLayout(new GridLayout(4, 2, 10, 10));
        createFrame.getContentPane().setBackground(new Color(60, 63, 65));

        JLabel nameLabel = createStyledLabel("Category Name:");
        JTextField nameField = new JTextField();

        JButton submitButton = createStyledButton("Create", new Color(0, 123, 255));
        JButton cancelButton = createStyledButton("Cancel", new Color(220, 53, 69));

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
        findFrame.setLayout(new GridLayout(3, 2, 10, 10));
        findFrame.getContentPane().setBackground(new Color(60, 63, 65));

        JLabel idLabel = createStyledLabel("Category ID:");
        JTextField idField = new JTextField();
        JButton submitButton = createStyledButton("Find", new Color(40, 167, 69));
        JButton cancelButton = createStyledButton("Cancel", new Color(220, 53, 69));

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(43, 43, 43));
        resultArea.setForeground(Color.WHITE);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));

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
                    JOptionPane.showMessageDialog(findFrame, "Category not found.", "Not Found", JOptionPane.WARNING_MESSAGE);
                    resultArea.setText("");
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
        listFrame.getContentPane().setBackground(new Color(60, 63, 65));

        String[] columns = {"ID", "Name", "Created At", "Updated At"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        table.setBackground(new Color(43, 43, 43));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);

        List<CategoriesCatalog> categories = listAllCategoriesUC.listAllSurveys();
        if (categories.isEmpty()) {
            JOptionPane.showMessageDialog(listFrame, "No categories found.", "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (CategoriesCatalog category : categories) {
                tableModel.addRow(new Object[]{
                        category.getId(),
                        category.getName(),
                        category.getCreatedAt(),
                        category.getUpdatedAt()
                });
            }
        }

        JScrollPane scrollPane = new JScrollPane(table);
        listFrame.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = createStyledButton("Close", new Color(220, 53, 69));
        closeButton.addActionListener(e -> listFrame.dispose());

        listFrame.add(closeButton, BorderLayout.SOUTH);

        listFrame.setVisible(true);
    }

    private void openUpdateWindow() {
        JFrame idFrame = new JFrame("Enter ID to Update");
        idFrame.setSize(400, 200);
        idFrame.setLayout(new GridLayout(3, 2, 10, 10));
        idFrame.getContentPane().setBackground(new Color(60, 63, 65));

        JLabel idLabel = createStyledLabel("Category ID:");
        JTextField idField = new JTextField();
        JButton submitButton = createStyledButton("Next", new Color(23, 162, 184));
        JButton cancelButton = createStyledButton("Cancel", new Color(220, 53, 69));

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
                    JOptionPane.showMessageDialog(idFrame, "Category not found", "Not Found", JOptionPane.WARNING_MESSAGE);
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
        editFrame.setLayout(new GridLayout(4, 2, 10, 10));
        editFrame.getContentPane().setBackground(new Color(60, 63, 65));

        JLabel nameLabel = createStyledLabel("Category Name:");
        JTextField nameField = new JTextField(category.getName());

        JButton updateButton = createStyledButton("Update", new Color(23, 162, 184));
        JButton cancelButton = createStyledButton("Cancel", new Color(220, 53, 69));

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
        deleteFrame.setLayout(new GridLayout(3, 2, 10, 10));
        deleteFrame.getContentPane().setBackground(new Color(60, 63, 65));

        JLabel idLabel = createStyledLabel("Category ID:");
        JTextField idField = new JTextField();
        JButton deleteButton = createStyledButton("Delete", new Color(220, 53, 69));
        JButton cancelButton = createStyledButton("Cancel", new Color(23, 162, 184));

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

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }
}