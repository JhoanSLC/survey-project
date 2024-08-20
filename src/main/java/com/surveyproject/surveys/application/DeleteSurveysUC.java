package com.surveyproject.surveys.application;

import com.surveyproject.surveys.domain.service.SurveysService;

public class DeleteSurveysUC {
    private final SurveysService surveysService;

    public DeleteSurveysUC(SurveysService survey){
        this.surveysService = survey;
    }

    public void delete(long id){
        surveysService.deleteSurveys(id);
    }
}
