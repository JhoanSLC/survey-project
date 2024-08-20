package com.surveyproject.surveys.application;

import com.surveyproject.surveys.domain.service.SurveysService;
import com.surveyproject.surveys.domain.entity.Surveys;

public class CreateSurveysUC {
    private final SurveysService surveysService;

    public CreateSurveysUC(SurveysService survey){
        this.surveysService = survey;
    }

    public void create(Surveys survey){
        surveysService.createSurveys(survey);
    }
}
