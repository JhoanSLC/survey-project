package com.surveyproject.subResponseOptions.application;

import java.util.List;

import com.surveyproject.subResponseOptions.domain.entity.SubResponseOptions;
import com.surveyproject.subResponseOptions.domain.service.SubResponseService;

public class ListAllSubResponsesUC {
    private final SubResponseService service;

    public ListAllSubResponsesUC(SubResponseService newService){
        this.service = newService;
    }

    public List<SubResponseOptions> listAll(){
        return service.listAllSubResponses();
    }
}
