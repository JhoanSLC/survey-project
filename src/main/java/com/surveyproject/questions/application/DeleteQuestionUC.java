package com.surveyproject.questions.application;

import com.surveyproject.questions.domain.service.QuestionsService;

public class DeleteQuestionUC {
    private final QuestionsService questionS;

    public DeleteQuestionUC(QuestionsService questionS){
        this.questionS = questionS;
    }

    public void delete(long id){
        questionS.deleteQuestions(id);
    }
}
