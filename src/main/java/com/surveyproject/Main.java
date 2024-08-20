package com.surveyproject;

import javax.swing.SwingUtilities;

import com.surveyproject.categoriesCatalog.infrasctructure.controller.CategoriesCatalogController;



public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CategoriesCatalogController::new);
    }


}