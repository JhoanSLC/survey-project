package com.surveyproject.responseOptions.domain.service;

import com.surveyproject.responseOptions.domain.entity.ResponseOptions;
import java.util.Optional;
import java.util.List;

public interface ResponseOptionsService {
    void createQuestion(ResponseOptions responseOption);
    Optional<ResponseOptions> findQuestionsById(long id);
    List<ResponseOptions> listAllQuestions();
    void updateQuestions(ResponseOptions responseOption);
    void deleteQuestions(long id);
}
