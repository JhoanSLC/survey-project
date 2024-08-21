package com.surveyproject.subResponseOptions.application;

import com.surveyproject.subResponseOptions.domain.entity.SubResponseOptions;
import com.surveyproject.subResponseOptions.domain.service.SubResponseService;

public class CreateSubResponseUC {
    private final SubResponseService service;

    public CreateSubResponseUC(SubResponseService newService){
        this.service = newService;
    }

    public void create(SubResponseOptions subResponse){
        service.createSubResponse(subResponse);
    }
}
