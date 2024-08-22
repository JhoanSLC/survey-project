package com.surveyproject.initApplication;

import javax.swing.SwingUtilities;

import com.surveyproject.categoriesCatalog.UI.CategoriesCatalogUI;
import com.surveyproject.categoriesCatalog.application.CreateCategoryUC;
import com.surveyproject.categoriesCatalog.application.DeleteCategoryUC;
import com.surveyproject.categoriesCatalog.application.FindCategoryByIdUC;
import com.surveyproject.categoriesCatalog.application.ListAllCategoriesUC;
import com.surveyproject.categoriesCatalog.application.UpdateCategoryUC;
import com.surveyproject.categoriesCatalog.infrasctructure.controller.CategoriesCatalogController;
import com.surveyproject.categoriesCatalog.infrasctructure.repository.CategoriesCatalogRepository;
import com.surveyproject.surveys.UI.SurveysUI;
import com.surveyproject.surveys.application.CreateSurveysUC;
import com.surveyproject.surveys.application.DeleteSurveysUC;
import com.surveyproject.surveys.application.FindSurveysByIdUC;
import com.surveyproject.surveys.application.ListAllSurveysUC;
import com.surveyproject.surveys.application.UpdateSurveysUC;
import com.surveyproject.surveys.infrastructure.controller.SurveysController;
import com.surveyproject.surveys.infrastructure.repository.SurveysRepository;

public class InitApplication {
   
    public InitApplication(){}

    public void initSurveyUi(){
        SurveysController controller = new SurveysController(
            new CreateSurveysUC(new SurveysRepository()),
            new DeleteSurveysUC(new SurveysRepository()),
            new FindSurveysByIdUC(new SurveysRepository()),
            new ListAllSurveysUC(new SurveysRepository()),
            new UpdateSurveysUC(new SurveysRepository())
        );

        SwingUtilities.invokeLater(() -> new SurveysUI(controller));
    }

    public void initCategoryUi(){
        CategoriesCatalogController controller = new CategoriesCatalogController(
            new CreateCategoryUC(new CategoriesCatalogRepository()),
            new DeleteCategoryUC(new CategoriesCatalogRepository()),
            new FindCategoryByIdUC(new CategoriesCatalogRepository()),
            new ListAllCategoriesUC(new CategoriesCatalogRepository()),
            new UpdateCategoryUC(new CategoriesCatalogRepository())
        );

        SwingUtilities.invokeLater(() -> new CategoriesCatalogUI(controller));
    }
}
