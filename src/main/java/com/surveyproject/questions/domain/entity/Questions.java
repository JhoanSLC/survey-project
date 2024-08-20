package com.surveyproject.questions.domain.entity;

import java.time.LocalDateTime;

public class Questions {
    private long id;
    private long chapterId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String questionNumber;
    private String responseType;
    private String commentQuestion;
    private String questionText;

    public Questions(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getCommentQuestion() {
        return commentQuestion;
    }

    public void setCommentQuestion(String commentQuestion) {
        this.commentQuestion = commentQuestion;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    
}
