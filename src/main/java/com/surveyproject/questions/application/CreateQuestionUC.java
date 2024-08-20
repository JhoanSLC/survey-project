package com.surveyproject.questions.application;

import com.surveyproject.questions.domain.entity.Questions;
import com.surveyproject.questions.domain.service.QuestionsService;

public class CreateQuestionUC {
    private final QuestionsService questionS;

    public CreateQuestionUC(QuestionsService questionS){
        this.questionS = questionS;
    }

    public void create(Questions question){
        questionS.createQuestion(question);
    }
}
