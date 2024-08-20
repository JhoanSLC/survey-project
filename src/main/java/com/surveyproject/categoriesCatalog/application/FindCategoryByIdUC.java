package com.surveyproject.categoriesCatalog.application;

import java.util.Optional;

import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;

public class FindCategoryByIdUC {
    private final CategoriesCatalogService category;

    public FindCategoryByIdUC(CategoriesCatalogService category){
        this.category = category;
    }

    public Optional<CategoriesCatalog> findById(long id){
        return category.finCategoriesById(id);
    }

}
