package com.surveyproject.questions.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveyproject.questions.domain.entity.Questions;


public interface QuestionsService {
    void createQuestion(Questions question);
    Optional<Questions> findQuestionsById(long id);
    List<Questions> listAllQuestions();
    void updateQuestions(Questions question);
    void deleteQuestions(long id);
}
