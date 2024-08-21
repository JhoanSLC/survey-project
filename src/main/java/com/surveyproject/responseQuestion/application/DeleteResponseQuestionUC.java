package com.surveyproject.responseQuestion.application;

import com.surveyproject.responseQuestion.domain.service.ResponseQuestionService;

public class DeleteResponseQuestionUC {
    private final ResponseQuestionService service;

    public DeleteResponseQuestionUC(ResponseQuestionService newService){
        this.service = newService;
    }

    public void delete(long id){
        service.deleteResponseQuestion(id);
    }
}
