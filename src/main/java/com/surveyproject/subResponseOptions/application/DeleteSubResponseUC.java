package com.surveyproject.subResponseOptions.application;

import com.surveyproject.subResponseOptions.domain.service.SubResponseService;

public class DeleteSubResponseUC {
    private final SubResponseService service;

    public DeleteSubResponseUC(SubResponseService newService){
        this.service = newService;
    }

    public void delete(long id){
        service.deleteSubResponse(id);
    }
}
