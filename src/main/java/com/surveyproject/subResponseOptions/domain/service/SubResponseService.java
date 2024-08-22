package com.surveyproject.subResponseOptions.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveyproject.subResponseOptions.domain.entity.SubResponseOptions;

public interface SubResponseService {
    void createSubResponse(SubResponseOptions subResponse);
    Optional<SubResponseOptions> findSubResponseById(long id);
    List<SubResponseOptions> listAllSubResponses();
    void updateSubResponse(SubResponseOptions subResponse, long id);
    void deleteSubResponse(long id);
}
