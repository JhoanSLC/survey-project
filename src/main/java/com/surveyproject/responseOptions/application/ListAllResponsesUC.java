package com.surveyproject.responseOptions.application;

import java.util.List;

import com.surveyproject.responseOptions.domain.entity.ResponseOptions;
import com.surveyproject.responseOptions.domain.service.ResponseOptionsService;

public class ListAllResponsesUC {
    private final ResponseOptionsService service;

    public ListAllResponsesUC(ResponseOptionsService service){
        this.service = service;
    }

    public List<ResponseOptions> listAll(){
        return service.listAllResponses();
    }
}
