package com.surveyproject.categoriesCatalog.infrasctructure.controller;

import com.surveyproject.categoriesCatalog.application.CreateCategoryUC;
import com.surveyproject.categoriesCatalog.application.DeleteCategoryUC;
import com.surveyproject.categoriesCatalog.application.FindCategoryByIdUC;
import com.surveyproject.categoriesCatalog.application.ListAllCategoriesUC;
import com.surveyproject.categoriesCatalog.application.UpdateCategoryUC;
import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;

import java.util.List;
import java.util.Optional;

public class CategoriesCatalogController {
    private final CreateCategoryUC createCategoryUC;
    private final DeleteCategoryUC deleteCategoryUC;
    private final FindCategoryByIdUC findCategoryByIdUC;
    private final ListAllCategoriesUC listAllCategoriesUC;
    private final UpdateCategoryUC updateCategoryUC;

    public CategoriesCatalogController(CreateCategoryUC createCategoryUC, DeleteCategoryUC deleteCategoryUC, 
                                        FindCategoryByIdUC findCategoryByIdUC, ListAllCategoriesUC listAllCategoriesUC, 
                                        UpdateCategoryUC updateCategoryUC) {
        this.createCategoryUC = createCategoryUC;
        this.deleteCategoryUC = deleteCategoryUC;
        this.findCategoryByIdUC = findCategoryByIdUC;
        this.listAllCategoriesUC = listAllCategoriesUC;
        this.updateCategoryUC = updateCategoryUC;
    }

    public void createCategory(CategoriesCatalog category) {
        createCategoryUC.create(category);
    }

    public void deleteCategory(long id) {
        deleteCategoryUC.delete(id);
    }

    public Optional<CategoriesCatalog> findCategoryById(long id) {
        return findCategoryByIdUC.findById(id);
    }

    public List<CategoriesCatalog> listAllCategories() {
        return listAllCategoriesUC.listAll();
    }

    public void updateCategory(CategoriesCatalog category, long id) {
        updateCategoryUC.update(category, id);
    }
}
