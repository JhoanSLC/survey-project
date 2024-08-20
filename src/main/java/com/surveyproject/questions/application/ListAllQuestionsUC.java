package com.surveyproject.questions.application;

import java.util.List;

import com.surveyproject.questions.domain.entity.Questions;
import com.surveyproject.questions.domain.service.QuestionsService;

public class ListAllQuestionsUC {
    private final QuestionsService questionS;

    public ListAllQuestionsUC(QuestionsService questionS){
        this.questionS = questionS;
    }

    public List<Questions> listAll(){
        return questionS.listAllQuestions();
    }
}
