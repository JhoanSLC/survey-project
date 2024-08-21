package com.surveyproject.responseQuestion.application;

import java.util.List;

import com.surveyproject.responseQuestion.domain.entity.ResponseQuestion;
import com.surveyproject.responseQuestion.domain.service.ResponseQuestionService;

public class ListAllResponseQuestionsUC {
    private final ResponseQuestionService service;

    public ListAllResponseQuestionsUC(ResponseQuestionService newService){
        this.service = newService;
    }

    public List<ResponseQuestion> listAll(){
        return service.listAllResponseQuestions();
    }
}
