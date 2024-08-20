package com.surveyproject.surveys.application;

import java.util.List;

import com.surveyproject.surveys.domain.entity.Surveys;
import com.surveyproject.surveys.domain.service.SurveysService;

public class ListAllSurveysUC {
    private final SurveysService surveysService;

    public ListAllSurveysUC(SurveysService survey){
        this.surveysService = survey;
    }

    public List<Surveys> listAll(){
        return surveysService.listAllSurveys();
    }
}
