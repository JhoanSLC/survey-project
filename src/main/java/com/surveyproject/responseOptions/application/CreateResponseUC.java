package com.surveyproject.responseOptions.application;

import com.surveyproject.responseOptions.domain.entity.ResponseOptions;
import com.surveyproject.responseOptions.domain.service.ResponseOptionsService;

public class CreateResponseUC {
    private final ResponseOptionsService service;

    public CreateResponseUC(ResponseOptionsService service){
        this.service = service;
    }

    public void create(ResponseOptions response){
        service.createResponse(response);
    }
}
