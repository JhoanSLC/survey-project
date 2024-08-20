package com.surveyproject.categoriesCatalog.application;

import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;

public class UpdateCategoryUC {
    private final CategoriesCatalogService category;

    public UpdateCategoryUC(CategoriesCatalogService category){
        this.category = category;
    }

    public void update(CategoriesCatalog categoryCatalog){
        category.updateCategories(categoryCatalog);
    }

}
