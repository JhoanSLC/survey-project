package com.surveyproject.initApplication;

import javax.swing.SwingUtilities;

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
}
