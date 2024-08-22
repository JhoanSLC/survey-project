package com.surveyproject.questions.infrastructure.controller;

import com.surveyproject.questions.application.CreateQuestionUC;
import com.surveyproject.questions.application.DeleteQuestionUC;
import com.surveyproject.questions.application.FindQuestionByIdUC;
import com.surveyproject.questions.application.ListAllQuestionsUC;
import com.surveyproject.questions.application.UpdateQuestionUC;
import com.surveyproject.questions.domain.entity.Questions;
import com.surveyproject.questions.domain.service.QuestionsService;
import com.surveyproject.questions.infrastructure.repository.QuestionsRepository;

import java.util.List;
import java.util.Optional;

public class QuestionsController {
    private final QuestionsService questionsService;
    private final CreateQuestionUC createQuestionUC;
    private final DeleteQuestionUC deleteQuestionUC;
    private final FindQuestionByIdUC findQuestionByIdUC;
    private final ListAllQuestionsUC listAllQuestionsUC;
    private final UpdateQuestionUC updateQuestionUC;

    public QuestionsController() {
        this.questionsService = new QuestionsRepository();
        this.createQuestionUC = new CreateQuestionUC(questionsService);
        this.deleteQuestionUC = new DeleteQuestionUC(questionsService);
        this.findQuestionByIdUC = new FindQuestionByIdUC(questionsService);
        this.listAllQuestionsUC = new ListAllQuestionsUC(questionsService);
        this.updateQuestionUC = new UpdateQuestionUC(questionsService);
    }

    public void createQuestion(Questions question) {
        createQuestionUC.create(question);
    }

    public void deleteQuestion(long id) {
        deleteQuestionUC.delete(id);
    }

    public Optional<Questions> findQuestionById(long id) {
        return findQuestionByIdUC.findById(id);
    }

    public List<Questions> listAllQuestions() {
        return listAllQuestionsUC.listAll();
    }

    public void updateQuestion(Questions question, long id) {
        updateQuestionUC.update(question, id);
    }
}
