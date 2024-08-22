package com.surveyproject.categoriesCatalog.application;

import java.util.List;

import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;

public class ListAllCategoriesUC {
    private final CategoriesCatalogService category;

    public ListAllCategoriesUC(CategoriesCatalogService category){
        this.category = category;
    }

    public List<CategoriesCatalog> listAll(){
        return category.listAllCategories();
    }

}
