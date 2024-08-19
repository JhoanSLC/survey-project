package com.surveyproject.surveys.domain.entity;

import java.time.LocalDateTime;

public class Surveys {
    private long id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String description;
    private String name;

    public Surveys(){}

    public Surveys(long id, LocalDateTime created, LocalDateTime updated, String description, String name){
        this.id = id;
        this.created_at = created;
        this.updated_at = updated;
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
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
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
