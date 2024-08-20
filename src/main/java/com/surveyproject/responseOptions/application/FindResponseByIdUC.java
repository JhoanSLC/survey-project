package com.surveyproject.responseOptions.application;

import java.util.Optional;

import com.surveyproject.responseOptions.domain.entity.ResponseOptions;
import com.surveyproject.responseOptions.domain.service.ResponseOptionsService;

public class FindResponseByIdUC {
    private final ResponseOptionsService service;

    public FindResponseByIdUC(ResponseOptionsService service){
        this.service = service;
    }
    
    public Optional<ResponseOptions> findById(long id){
        return service.findResponseById(id);
    }

}
