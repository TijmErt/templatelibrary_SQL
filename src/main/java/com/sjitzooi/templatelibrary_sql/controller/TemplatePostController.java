package com.sjitzooi.templatelibrary_sql.controller;

import com.netflix.graphql.dgs.*;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostInput;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@DgsComponent
@Transactional
@CrossOrigin(origins ="*" )
@Slf4j
public class TemplatePostController {

    @Autowired
    public TemplatePostController(TemplatePostService templatePostService) {
        this.templatePostService = templatePostService;
    }

    private TemplatePostService templatePostService;



    @DgsQuery
    public TemplatePost getTemplatePost(@InputArgument String id) {

        TemplatePost model = templatePostService.getById(id);
        return model;
    }

    @DgsMutation
    public String createTemplatePost(@InputArgument MultipartFile file, @InputArgument TemplatePostInput input) throws IOException {
        TemplatePost temp = templatePostService.save(file,input);
        log.info(temp.getId());
        log.info(temp.getFileKey());
        return temp.getId();
    }
}
