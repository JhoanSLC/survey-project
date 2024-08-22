package com.surveyproject.initApplication;

import javax.swing.SwingUtilities;

import com.surveyproject.categoriesCatalog.UI.CategoriesCatalogUI;
import com.surveyproject.categoriesCatalog.infrasctructure.controller.CategoriesCatalogController;
import com.surveyproject.chapter.UI.ChapterUI;
import com.surveyproject.chapter.infrastructure.controller.ChapterController;
import com.surveyproject.questions.UI.QuestionsUI;
import com.surveyproject.questions.infrastructure.controller.QuestionsController;
import com.surveyproject.responseOptions.UI.ResponseOptionsUI;
import com.surveyproject.responseOptions.infrastructure.controller.ResponseOptionsController;
import com.surveyproject.responseQuestion.UI.ResponseQuestionUI;
import com.surveyproject.responseQuestion.infrastructure.controller.ResponseQuestionController;
import com.surveyproject.surveys.UI.SurveysUI;
import com.surveyproject.surveys.infrastructure.controller.SurveysController;

public class InitApplication {
   
    public InitApplication(){}

    public void initSurveyUi(){
        SurveysController controller = new SurveysController();

        SwingUtilities.invokeLater(() -> new SurveysUI(controller));
    }

    public void initCategoryUi(){
        CategoriesCatalogController controller = new CategoriesCatalogController();

        SwingUtilities.invokeLater(() -> new CategoriesCatalogUI(controller));
    }

    public void initChapterUi() {
        ChapterController controller = new ChapterController();

        SwingUtilities.invokeLater(() -> new ChapterUI(controller));
    }

    public void initQuestionsUI(){
        QuestionsController controller = new QuestionsController();

        SwingUtilities.invokeLater(() -> new QuestionsUI(controller));
    }

    public void initReponseOptionsUi(){
        ResponseOptionsController controller = new ResponseOptionsController();

        SwingUtilities.invokeLater(() -> new ResponseOptionsUI(controller));
    }

    public void initResponseQuestionUi(){
        ResponseQuestionController controller = new ResponseQuestionController();

        SwingUtilities.invokeLater(() -> new ResponseQuestionUI(controller));
    }
}
