package com.surveyproject.responseQuestion.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveyproject.responseQuestion.domain.entity.ResponseQuestion;

public interface ResponseQuestionService {
    void createResponseQuestions(ResponseQuestion responseQuestion);
    Optional<ResponseQuestion> findResponseQuestionById(long id);
    List<ResponseQuestion> listAllResponseQuestions();
    void updateResponseQuestion(ResponseQuestion responseQuestion);
    void deleteResponseQuestion(long id);
}
