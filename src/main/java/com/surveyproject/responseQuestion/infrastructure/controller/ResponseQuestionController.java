package com.surveyproject.responseQuestion.infrastructure.controller;

import com.surveyproject.responseQuestion.application.CreateResponseQuestionUC;
import com.surveyproject.responseQuestion.application.DeleteResponseQuestionUC;
import com.surveyproject.responseQuestion.application.FindResponseQuestionByIdUC;
import com.surveyproject.responseQuestion.application.ListAllResponseQuestionsUC;
import com.surveyproject.responseQuestion.application.UpdateResponseQuestionUC;
import com.surveyproject.responseQuestion.domain.entity.ResponseQuestion;
import com.surveyproject.responseQuestion.domain.service.ResponseQuestionService;
import com.surveyproject.responseQuestion.infrastructure.repository.ResponseQuestionRepository;

import java.util.List;
import java.util.Optional;

public class ResponseQuestionController {
    private final ResponseQuestionService responseQuestionService;
    private final CreateResponseQuestionUC createResponseQuestionUC;
    private final DeleteResponseQuestionUC deleteResponseQuestionUC;
    private final FindResponseQuestionByIdUC findResponseQuestionByIdUC;
    private final ListAllResponseQuestionsUC listAllResponseQuestionsUC;
    private final UpdateResponseQuestionUC updateResponseQuestionUC;

    public ResponseQuestionController() {
        this.responseQuestionService = new ResponseQuestionRepository();
        this.createResponseQuestionUC = new CreateResponseQuestionUC(responseQuestionService);
        this.deleteResponseQuestionUC = new DeleteResponseQuestionUC(responseQuestionService);
        this.findResponseQuestionByIdUC = new FindResponseQuestionByIdUC(responseQuestionService);
        this.listAllResponseQuestionsUC = new ListAllResponseQuestionsUC(responseQuestionService);
        this.updateResponseQuestionUC = new UpdateResponseQuestionUC(responseQuestionService);
    }

    public void createResponseQuestion(ResponseQuestion responseQuestion) {
        createResponseQuestionUC.create(responseQuestion);
    }

    public void deleteResponseQuestion(long id) {
        deleteResponseQuestionUC.delete(id);
    }

    public Optional<ResponseQuestion> findResponseQuestionById(long id) {
        return findResponseQuestionByIdUC.findById(id);
    }

    public List<ResponseQuestion> listAllResponseQuestions() {
        return listAllResponseQuestionsUC.listAll();
    }

    public void updateResponseQuestion(ResponseQuestion responseQuestion, long id) {
        updateResponseQuestionUC.update(responseQuestion, id);
    }
}
