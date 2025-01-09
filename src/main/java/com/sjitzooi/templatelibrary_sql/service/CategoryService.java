package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.Category;
import com.sjitzooi.templatelibrary_sql.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CategoryService {
    private static final String ERROR_MESSAGE_SERVICE_LAYER= "CategoryService:";

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllByIds(List<String> ids) {
        List<Category> categories = new ArrayList<>();
        try{
            categories = categoryRepository.findAllByIdIn(ids);
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" findAllByIds: {}",e.getMessage());
            throw e;
        }
        return categories;
    }

    public List<Category> getAll(){
        List<Category> categories = new ArrayList<>();
        try{
            categories = categoryRepository.findAll();
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" getAll: {}",e.getMessage());
            throw e;
        }
        return categories;
    }
}
