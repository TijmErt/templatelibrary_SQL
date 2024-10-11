package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.repository.TemplatePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplatePostService {

    @Autowired
    private TemplatePostRepository templatePostRepository;

    public List<TemplatePost> getAll(){
        return templatePostRepository.findAll();
    }
    public TemplatePost getById(String id){
        return templatePostRepository.findById(id).isPresent() ? templatePostRepository.findById(id).get() : null;
    }
}
