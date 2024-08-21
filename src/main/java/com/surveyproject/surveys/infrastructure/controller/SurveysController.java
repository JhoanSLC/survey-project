package com.surveyproject.surveys.infrastructure.controller;

import com.surveyproject.surveys.application.CreateSurveysUC;
import com.surveyproject.surveys.application.DeleteSurveysUC;
import com.surveyproject.surveys.application.FindSurveysByIdUC;
import com.surveyproject.surveys.application.ListAllSurveysUC;
import com.surveyproject.surveys.application.UpdateSurveysUC;
import com.surveyproject.surveys.domain.entity.Surveys;

import java.util.List;
import java.util.Optional;

public class SurveysController {
    private final CreateSurveysUC createSurveysUC;
    private final DeleteSurveysUC deleteSurveysUC;
    private final FindSurveysByIdUC findSurveysByIdUC;
    private final ListAllSurveysUC listAllSurveysUC;
    private final UpdateSurveysUC updateSurveysUC;

    public SurveysController(CreateSurveysUC createSurveysUC, DeleteSurveysUC deleteSurveysUC, 
                             FindSurveysByIdUC findSurveysByIdUC, ListAllSurveysUC listAllSurveysUC, 
                             UpdateSurveysUC updateSurveysUC) {
        this.createSurveysUC = createSurveysUC;
        this.deleteSurveysUC = deleteSurveysUC;
        this.findSurveysByIdUC = findSurveysByIdUC;
        this.listAllSurveysUC = listAllSurveysUC;
        this.updateSurveysUC = updateSurveysUC;
    }

    public void createSurvey(Surveys survey) {
        createSurveysUC.create(survey);
    }

    public void deleteSurvey(long id) {
        deleteSurveysUC.delete(id);
    }

    public Optional<Surveys> findSurveyById(long id) {
        return findSurveysByIdUC.findById(id);
    }

    public List<Surveys> listAllSurveys() {
        return listAllSurveysUC.listAll();
    }

    public void updateSurvey(Surveys survey, long id) {
        updateSurveysUC.update(survey, id);
    }
}
