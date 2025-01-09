package com.sjitzooi.templatelibrary_sql.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.sjitzooi.templatelibrary_sql.entity.Category;
import com.sjitzooi.templatelibrary_sql.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
@Slf4j
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @DgsQuery
    public List<Category> getAllCategories(){
        try{
            List<Category> categories = categoryService.getAll();
            return categories;
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Failed to fetch TemplatePost: " + e.getMessage());
        }
    }
}
