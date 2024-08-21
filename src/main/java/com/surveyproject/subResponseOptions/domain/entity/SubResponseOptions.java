package com.surveyproject.subResponseOptions.domain.entity;

import java.time.LocalDateTime;

public class SubResponseOptions {
    private long id;
    private int subResponseNumber;
    private LocalDateTime createdAt;
    private long responseOptionsId;
    private LocalDateTime updatedAt;
    private String componentHtml;
    private String subResponseText;

    public SubResponseOptions(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSubResponseNumber() {
        return subResponseNumber;
    }

    public void setSubResponseNumber(int subResponseNumber) {
        this.subResponseNumber = subResponseNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public long getResponseOptionsId() {
        return responseOptionsId;
    }

    public void setResponseOptionsId(long responseOptionsId) {
        this.responseOptionsId = responseOptionsId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getComponentHtml() {
        return componentHtml;
    }

    public void setComponentHtml(String componentHtml) {
        this.componentHtml = componentHtml;
    }

    public String getSubResponseText() {
        return subResponseText;
    }

    public void setSubResponseText(String subResponseText) {
        this.subResponseText = subResponseText;
    }

    
}
