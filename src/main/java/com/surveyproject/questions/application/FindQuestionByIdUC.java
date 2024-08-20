package com.surveyproject.questions.application;

import java.util.Optional;

import com.surveyproject.questions.domain.entity.Questions;
import com.surveyproject.questions.domain.service.QuestionsService;

public class FindQuestionByIdUC {
    private final QuestionsService questionS;

    public FindQuestionByIdUC(QuestionsService questionS){
        this.questionS = questionS;
    }

    public Optional<Questions> findById(long id){
        return questionS.findQuestionsById(id);
    }
}
