package com.surveyproject.responseOptions.domain.entity;

import java.time.LocalDateTime;

public class ResponseOptions {
    private long id;
    private int optionValue;
    private long categoryCatalogId;
    private LocalDateTime createdAt;
    private long parentResponseId;
    private long questionId;
    private LocalDateTime updatedAt;
    private String typeComponentHtml;
    private String commentResponse;
    private String optionText;
    
    
    public ResponseOptions(){}

    
    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(int optionValue) {
        this.optionValue = optionValue;
    }

    public long getCategoryCatalogId() {
        return categoryCatalogId;
    }

    public void setCategoryCatalogId(long categoryCatalogId) {
        this.categoryCatalogId = categoryCatalogId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getParentResponseId() {
        return parentResponseId;
    }

    public void setParentResponseId(long parentResponseId) {
        this.parentResponseId = parentResponseId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTypeComponentHtml() {
        return typeComponentHtml;
    }

    public void setTypeComponentHtml(String typeComponentHtml) {
        this.typeComponentHtml = typeComponentHtml;
    }

    public String getCommentResponse() {
        return commentResponse;
    }

    public void setCommentResponse(String commentResponse) {
        this.commentResponse = commentResponse;
    }

    
}
