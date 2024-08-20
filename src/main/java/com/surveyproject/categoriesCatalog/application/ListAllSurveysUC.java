package com.surveyproject.categoriesCatalog.application;

import java.util.List;

import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;

public class ListAllSurveysUC {
    private final CategoriesCatalogService category;

    public ListAllSurveysUC(CategoriesCatalogService category){
        this.category = category;
    }

    public List<CategoriesCatalog> listAllSurveys(){
        return category.listAllCategories();
    }

}
