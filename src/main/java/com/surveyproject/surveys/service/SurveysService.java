package com.surveyproject.surveys.service;

import java.util.List;
import java.util.Optional;

import com.surveyproject.surveys.domain.entity.Surveys;

public interface SurveysService {
    void createSurveys(Surveys survey);
    Optional<Surveys> findSurveysById(long id);
    List<Surveys> listAllSurveys();
    void updateSurveys(Surveys survey);
    void deleteSurveys(long id);
}
