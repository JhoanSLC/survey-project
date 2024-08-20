package com.surveyproject.categoriesCatalog.application;

import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;

public class DeleteCategoryUC {
    private final CategoriesCatalogService category;

    public DeleteCategoryUC(CategoriesCatalogService category){
        this.category = category;
    }

    public void delete(long id){
        category.deleteCategories(id);
    }

}
