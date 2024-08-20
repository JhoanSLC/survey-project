package com.surveyproject.responseOptions.application;

import com.surveyproject.responseOptions.domain.service.ResponseOptionsService;

public class DeleteResponseUC {
    private final ResponseOptionsService service;

    public DeleteResponseUC(ResponseOptionsService service){
        this.service = service;
    }

    public void delete(long id){
        service.deleteResponse(id);
    }
}
