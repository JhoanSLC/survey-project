package com.surveyproject.categoriesCatalog.application;

import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;

public class CreateCategoryUC {
    private final CategoriesCatalogService category;

    public CreateCategoryUC(CategoriesCatalogService category){
        this.category = category;
    }

    public void create(CategoriesCatalog categoryCatalog){
        category.createCategories(categoryCatalog);
    }
}
