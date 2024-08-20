package com.surveyproject.responseOptions.application;

import com.surveyproject.responseOptions.domain.entity.ResponseOptions;
import com.surveyproject.responseOptions.domain.service.ResponseOptionsService;

public class UpdateResponseUC {
    private final ResponseOptionsService service;

    public UpdateResponseUC(ResponseOptionsService service){
        this.service = service;
    }

    public void update(ResponseOptions response){
        service.updateResponse(response);
    }
}
