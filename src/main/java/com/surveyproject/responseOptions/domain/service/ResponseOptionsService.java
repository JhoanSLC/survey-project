package com.surveyproject.responseOptions.domain.service;

import com.surveyproject.responseOptions.domain.entity.ResponseOptions;
import java.util.Optional;
import java.util.List;

public interface ResponseOptionsService {
    void createResponse(ResponseOptions responseOption);
    Optional<ResponseOptions> findResponseById(long id);
    List<ResponseOptions> listAllResponses();
    void updateResponse(ResponseOptions responseOption);
    void deleteResponse(long id);
}
