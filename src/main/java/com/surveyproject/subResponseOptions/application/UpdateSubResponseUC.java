package com.surveyproject.subResponseOptions.application;

import com.surveyproject.subResponseOptions.domain.entity.SubResponseOptions;
import com.surveyproject.subResponseOptions.domain.service.SubResponseService;

public class UpdateSubResponseUC {
    private final SubResponseService service;

    public UpdateSubResponseUC(SubResponseService newService){
        this.service = newService;
    }

    public void update(SubResponseOptions subResponse,long id){
        service.updateSubResponse(subResponse,id);
    }
}
