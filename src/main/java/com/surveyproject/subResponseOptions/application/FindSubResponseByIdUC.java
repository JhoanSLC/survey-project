package com.surveyproject.subResponseOptions.application;

import java.util.Optional;

import com.surveyproject.subResponseOptions.domain.entity.SubResponseOptions;
import com.surveyproject.subResponseOptions.domain.service.SubResponseService;

public class FindSubResponseByIdUC {
    private final SubResponseService service;

    public FindSubResponseByIdUC(SubResponseService newService){
        this.service = newService;
    }

    public Optional<SubResponseOptions> findById(long id){
        return service.findSubResponseById(id);
    }
}
