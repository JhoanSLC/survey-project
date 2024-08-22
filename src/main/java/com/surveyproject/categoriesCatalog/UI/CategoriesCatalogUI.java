package com.surveyproject.categoriesCatalog.UI;

import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.infrasctructure.controller.CategoriesCatalogController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class CategoriesCatalogUI extends JFrame {
    private final CategoriesCatalogController controller;
    private DefaultTableModel tableModel;
    private JTable categoryTable;

    public CategoriesCatalogUI(CategoriesCatalogController controller) {
        this.controller = controller;
        initUI();
    }

    private void initUI() {
        setTitle("Categories Management");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem createCategoryItem = new JMenuItem("Create Category");
        JMenuItem listCategoriesItem = new JMenuItem("List Categories");
        JMenuItem editCategoryItem = new JMenuItem("Edit Category");
        JMenuItem deleteCategoryItem = new JMenuItem("Delete Category");
        JMenuItem findCategoryItem = new JMenuItem("Find Category by ID");

        menu.add(createCategoryItem);
        menu.add(listCategoriesItem);
        menu.add(editCategoryItem);
        menu.add(deleteCategoryItem);
        menu.add(findCategoryItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Table to display categories
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Created At", "Updated At"}, 0);
        categoryTable = new JTable(tableModel);
        add(new JScrollPane(categoryTable), BorderLayout.CENTER);

        // Event listeners
        createCategoryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCategoryDialog();
            }
        });

        listCategoriesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listCategories();
            }
        });

        editCategoryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCategoryDialog();
            }
        });

        deleteCategoryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCategoryDialog();
            }
        });

        findCategoryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findCategoryByIdDialog();
            }
        });

        setVisible(true);
    }

    private void createCategoryDialog() {
        JTextField nameField = new JTextField();
        Object[] fields = {
            "Name:", nameField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Category", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();

            if (!name.isEmpty()) {
                CategoriesCatalog category = new CategoriesCatalog();
                category.setName(name);
                controller.createCategory(category);
                JOptionPane.showMessageDialog(this, "Category created successfully!");
                listCategories(); // Refrescar la tabla después de crear
            } else {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.");
            }
        }
    }

    private void listCategories() {
        List<CategoriesCatalog> categories = controller.listAllCategories();
        tableModel.setRowCount(0); // Clear existing rows
        for (CategoriesCatalog category : categories) {
            tableModel.addRow(new Object[]{
                category.getId(),
                category.getName(),
                category.getCreatedAt(),
                category.getUpdatedAt()
            });
        }
    }

    private void editCategoryDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Category ID to edit:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<CategoriesCatalog> categoryOpt = controller.findCategoryById(id);
            if (categoryOpt.isPresent()) {
                CategoriesCatalog category = categoryOpt.get();

                JTextField nameField = new JTextField(category.getName());
                Object[] fields = {
                    "Name:", nameField
                };

                int option = JOptionPane.showConfirmDialog(this, fields, "Edit Category", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    category.setName(nameField.getText());
                    controller.updateCategory(category, id);
                    JOptionPane.showMessageDialog(this, "Category updated successfully!");
                    listCategories(); // Refrescar la tabla después de actualizar
                }
            } else {
                JOptionPane.showMessageDialog(this, "Category not found with ID: " + id);
            }
        }
    }

    private void deleteCategoryDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Category ID to delete:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<CategoriesCatalog> categoryOpt = controller.findCategoryById(id);
            if (categoryOpt.isPresent()) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the category?", "Delete Category", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    controller.deleteCategory(id);
                    JOptionPane.showMessageDialog(this, "Category deleted successfully!");
                    listCategories(); // Refrescar la tabla después de eliminar
                }
            } else {
                JOptionPane.showMessageDialog(this, "Category not found with ID: " + id);
            }
        }
    }

    private void findCategoryByIdDialog() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Category ID to find:");
        if (idStr != null) {
            long id = Long.parseLong(idStr);
            Optional<CategoriesCatalog> categoryOpt = controller.findCategoryById(id);
            if (categoryOpt.isPresent()) {
                CategoriesCatalog category = categoryOpt.get();
                JTextField nameField = new JTextField(category.getName());
                JTextField createdField = new JTextField(category.getCreatedAt().toString());
                JTextField updatedField = new JTextField(category.getUpdatedAt().toString());

                nameField.setEditable(false);
                createdField.setEditable(false);
                updatedField.setEditable(false);

                Object[] fields = {
                    "Name:", nameField,
                    "Created At:", createdField,
                    "Updated At:", updatedField
                };

                JOptionPane.showMessageDialog(this, fields, "Category Details", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Category not found with ID: " + id);
            }
        }
    }
}
