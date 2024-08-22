package com.surveyproject.categoriesCatalog.infrasctructure.controller;

import com.surveyproject.categoriesCatalog.application.CreateCategoryUC;
import com.surveyproject.categoriesCatalog.application.DeleteCategoryUC;
import com.surveyproject.categoriesCatalog.application.FindCategoryByIdUC;
import com.surveyproject.categoriesCatalog.application.ListAllCategoriesUC;
import com.surveyproject.categoriesCatalog.application.UpdateCategoryUC;
import com.surveyproject.categoriesCatalog.domain.entity.CategoriesCatalog;
import com.surveyproject.categoriesCatalog.domain.service.CategoriesCatalogService;
import com.surveyproject.categoriesCatalog.infrasctructure.repository.CategoriesCatalogRepository;

import java.util.List;
import java.util.Optional;

public class CategoriesCatalogController {
    private final CategoriesCatalogService service;
    private final CreateCategoryUC createCategoryUC;
    private final DeleteCategoryUC deleteCategoryUC;
    private final FindCategoryByIdUC findCategoryByIdUC;
    private final ListAllCategoriesUC listAllCategoriesUC;
    private final UpdateCategoryUC updateCategoryUC;

    public CategoriesCatalogController(){
        this.service = new CategoriesCatalogRepository();
        this.createCategoryUC = new CreateCategoryUC(service);
        this.deleteCategoryUC = new DeleteCategoryUC(service);
        this.findCategoryByIdUC = new FindCategoryByIdUC(service);
        this.listAllCategoriesUC = new ListAllCategoriesUC(service);
        this.updateCategoryUC = new UpdateCategoryUC(service);
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
