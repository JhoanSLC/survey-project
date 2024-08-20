package com.surveyproject.surveys.application;

import java.util.Optional;

import com.surveyproject.surveys.domain.entity.Surveys;
import com.surveyproject.surveys.domain.service.SurveysService;

public class FindSurveysByIdUC {
    private final SurveysService surveysService;

    public FindSurveysByIdUC(SurveysService survey){
        this.surveysService = survey;
    }

    public Optional<Surveys> findById(long id){
        return surveysService.findSurveysById(id);
    }
}
