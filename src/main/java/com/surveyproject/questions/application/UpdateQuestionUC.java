package com.surveyproject.questions.application;

import com.surveyproject.questions.domain.entity.Questions;
import com.surveyproject.questions.domain.service.QuestionsService;

public class UpdateQuestionUC {
    private final QuestionsService questionS;

    public UpdateQuestionUC(QuestionsService questionS){
        this.questionS = questionS;
    }

    public void update(Questions question, long id){
        questionS.updateQuestions(question,id);
    }
}
