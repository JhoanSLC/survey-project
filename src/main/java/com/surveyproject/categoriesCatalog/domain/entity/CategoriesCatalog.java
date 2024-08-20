package com.surveyproject.categoriesCatalog.domain.entity;

import java.time.LocalDateTime;

public class CategoriesCatalog {
    private long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;

    public CategoriesCatalog(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}
