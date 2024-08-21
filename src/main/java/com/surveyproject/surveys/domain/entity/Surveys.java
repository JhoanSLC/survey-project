package com.surveyproject.surveys.domain.entity;

import java.time.LocalDateTime;

public class Surveys {
    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
    private String name;

    public Surveys(){}

    public Surveys(long id, LocalDateTime created, LocalDateTime updated, String description, String name){
        this.id = id;
        this.createdAt = created;
        this.updatedAt = updated;
        this.description = description;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreated_at() {
        return createdAt;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.createdAt = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updatedAt;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updatedAt = updated_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
