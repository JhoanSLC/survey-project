package com.surveyproject.surveys.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import com.surveyproject.surveys.domain.entity.Surveys;
import com.surveyproject.surveys.domain.service.SurveysService;

public class SurveysRepository implements SurveysService{

    @Override
    public void createSurveys(Surveys survey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createSurveys'");
    }

    @Override
    public Optional<Surveys> findSurveysById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSurveysById'");
    }

    @Override
    public List<Surveys> listAllSurveys() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAllSurveys'");
    }

    @Override
    public void updateSurveys(Surveys survey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSurveys'");
    }

    @Override
    public void deleteSurveys(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSurveys'");
    }

}
