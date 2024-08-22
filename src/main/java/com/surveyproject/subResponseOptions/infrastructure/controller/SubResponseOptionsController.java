package com.surveyproject.subResponseOptions.infrastructure.controller;

import com.surveyproject.subResponseOptions.application.CreateSubResponseUC;
import com.surveyproject.subResponseOptions.application.DeleteSubResponseUC;
import com.surveyproject.subResponseOptions.application.FindSubResponseByIdUC;
import com.surveyproject.subResponseOptions.application.ListAllSubResponsesUC;
import com.surveyproject.subResponseOptions.application.UpdateSubResponseUC;
import com.surveyproject.subResponseOptions.domain.entity.SubResponseOptions;
import com.surveyproject.subResponseOptions.domain.service.SubResponseService;
import com.surveyproject.subResponseOptions.infrastructure.repository.SubResponseRepository;

import java.util.List;
import java.util.Optional;

public class SubResponseOptionsController {
    private final SubResponseService service;
    private final CreateSubResponseUC createSubResponseUC;
    private final DeleteSubResponseUC deleteSubResponseUC;
    private final FindSubResponseByIdUC findSubResponseByIdUC;
    private final ListAllSubResponsesUC listAllSubResponsesUC;
    private final UpdateSubResponseUC updateSubResponseUC;

    public SubResponseOptionsController() {
        this.service = new SubResponseRepository();
        this.createSubResponseUC = new CreateSubResponseUC(service);
        this.deleteSubResponseUC = new DeleteSubResponseUC(service);
        this.findSubResponseByIdUC = new FindSubResponseByIdUC(service);
        this.listAllSubResponsesUC = new ListAllSubResponsesUC(service);
        this.updateSubResponseUC = new UpdateSubResponseUC(service);
    }

    public void createSubResponse(SubResponseOptions subResponse) {
        createSubResponseUC.create(subResponse);
    }

    public void deleteSubResponse(long id) {
        deleteSubResponseUC.delete(id);
    }

    public Optional<SubResponseOptions> findSubResponseById(long id) {
        return findSubResponseByIdUC.findById(id);
    }

    public List<SubResponseOptions> listAllSubResponses() {
        return listAllSubResponsesUC.listAll();
    }

    public void updateSubResponse(SubResponseOptions subResponse, long id) {
        updateSubResponseUC.update(subResponse, id);
    }
}
