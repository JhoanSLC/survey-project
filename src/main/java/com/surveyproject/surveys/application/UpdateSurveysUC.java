package com.surveyproject.surveys.application;

import com.surveyproject.surveys.domain.entity.Surveys;
import com.surveyproject.surveys.domain.service.SurveysService;

public class UpdateSurveysUC {
    private final SurveysService surveysService;

    public UpdateSurveysUC(SurveysService survey){
        this.surveysService = survey;
    }

    public void update(Surveys survey, long id){
        surveysService.updateSurveys(survey,id);
    }
}
