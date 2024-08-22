package com.surveyproject.surveys.infrastructure.controller;

import com.surveyproject.surveys.application.CreateSurveysUC;
import com.surveyproject.surveys.application.DeleteSurveysUC;
import com.surveyproject.surveys.application.FindSurveysByIdUC;
import com.surveyproject.surveys.application.ListAllSurveysUC;
import com.surveyproject.surveys.application.UpdateSurveysUC;
import com.surveyproject.surveys.domain.entity.Surveys;
import com.surveyproject.surveys.domain.service.SurveysService;
import com.surveyproject.surveys.infrastructure.repository.SurveysRepository;

import java.util.List;
import java.util.Optional;

public class SurveysController {
    private final SurveysService surveysService;
    private final CreateSurveysUC createSurveysUC;
    private final DeleteSurveysUC deleteSurveysUC;
    private final FindSurveysByIdUC findSurveysByIdUC;
    private final ListAllSurveysUC listAllSurveysUC;
    private final UpdateSurveysUC updateSurveysUC;

    public SurveysController() {
        this.surveysService = new SurveysRepository();
        this.createSurveysUC = new CreateSurveysUC(surveysService);
        this.deleteSurveysUC = new DeleteSurveysUC(surveysService);
        this.findSurveysByIdUC = new FindSurveysByIdUC(surveysService);
        this.listAllSurveysUC = new ListAllSurveysUC(surveysService);
        this.updateSurveysUC = new UpdateSurveysUC(surveysService);
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
