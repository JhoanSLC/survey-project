package com.surveyproject.categoriesCatalog.domain.service;

import java.util.List;
import java.util.Optional;

import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;

public interface CategoriesCatalogService {
    void createCategories(CategoriesCatalog category);
    Optional<CategoriesCatalog> finCategoriesById(long id);
    List<CategoriesCatalog> listAllCategories();
    void updateCategories(CategoriesCatalog category);
    void deleteCategories(long id);
}
