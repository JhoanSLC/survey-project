package com.surveyproject.responseQuestion.application;

import com.surveyproject.responseQuestion.domain.entity.ResponseQuestion;
import com.surveyproject.responseQuestion.domain.service.ResponseQuestionService;

public class UpdateResponseQuestionUC {
    private final ResponseQuestionService service;

    public UpdateResponseQuestionUC(ResponseQuestionService newService){
        this.service = newService;
    }

    public void update(ResponseQuestion responseQuestion){
        service.updateResponseQuestion(responseQuestion);
    }
}
