package com.surveyproject.responseQuestion.application;

import java.util.Optional;

import com.surveyproject.responseQuestion.domain.entity.ResponseQuestion;
import com.surveyproject.responseQuestion.domain.service.ResponseQuestionService;

public class FindResponseQuestionByIdUC {
    private final ResponseQuestionService service;

    public FindResponseQuestionByIdUC(ResponseQuestionService newService){
        this.service = newService;
    }

    public Optional<ResponseQuestion> findById(long id){
        return service.findResponseQuestionById(id);
    }
}
