package com.surveyproject.responseQuestion.application;

import com.surveyproject.responseQuestion.domain.entity.ResponseQuestion;
import com.surveyproject.responseQuestion.domain.service.ResponseQuestionService;

public class CreateResponseQuestionUC {
    private final ResponseQuestionService service;

    public CreateResponseQuestionUC(ResponseQuestionService newService){
        this.service = newService;
    }

    public void create(ResponseQuestion responseQuestion){
        service.createResponseQuestions(responseQuestion);
    }
}
