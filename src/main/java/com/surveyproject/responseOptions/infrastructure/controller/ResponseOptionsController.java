package com.surveyproject.responseOptions.infrastructure.controller;

import com.surveyproject.responseOptions.application.CreateResponseUC;
import com.surveyproject.responseOptions.application.DeleteResponseUC;
import com.surveyproject.responseOptions.application.FindResponseByIdUC;
import com.surveyproject.responseOptions.application.ListAllResponsesUC;
import com.surveyproject.responseOptions.application.UpdateResponseUC;
import com.surveyproject.responseOptions.domain.entity.ResponseOptions;
import com.surveyproject.responseOptions.domain.service.ResponseOptionsService;
import com.surveyproject.responseOptions.infrastructure.repository.ResponseOptionsRepository;

import java.util.List;
import java.util.Optional;

public class ResponseOptionsController {
    private final ResponseOptionsService responseOptionsService;
    private final CreateResponseUC createResponseUC;
    private final DeleteResponseUC deleteResponseUC;
    private final FindResponseByIdUC findResponseByIdUC;
    private final ListAllResponsesUC listAllResponsesUC;
    private final UpdateResponseUC updateResponseUC;

    public ResponseOptionsController() {
        this.responseOptionsService = new ResponseOptionsRepository();
        this.createResponseUC = new CreateResponseUC(responseOptionsService);
        this.deleteResponseUC = new DeleteResponseUC(responseOptionsService);
        this.findResponseByIdUC = new FindResponseByIdUC(responseOptionsService);
        this.listAllResponsesUC = new ListAllResponsesUC(responseOptionsService);
        this.updateResponseUC = new UpdateResponseUC(responseOptionsService);
    }

    public void createResponse(ResponseOptions responseOption) {
        createResponseUC.create(responseOption);
    }

    public void deleteResponse(long id) {
        deleteResponseUC.delete(id);
    }

    public Optional<ResponseOptions> findResponseById(long id) {
        return findResponseByIdUC.findById(id);
    }

    public List<ResponseOptions> listAllResponses() {
        return listAllResponsesUC.listAll();
    }

    public void updateResponse(ResponseOptions responseOption, long id) {
        updateResponseUC.update(responseOption, id);
    }
}
