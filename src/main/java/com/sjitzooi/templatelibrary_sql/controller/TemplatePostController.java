package com.sjitzooi.templatelibrary_sql.controller;

import com.netflix.graphql.dgs.*;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostInput;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TemplatePost> getTemplatePost(@InputArgument String id) {

        try {
            TemplatePost model = templatePostService.getById(id);
            return ResponseEntity.ok(model);
        }
        catch (Exception e) {
            log.debug(e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @DgsMutation
    public ResponseEntity createTemplatePost(@InputArgument MultipartFile file, @InputArgument TemplatePostInput input) throws IOException {

        try{
            TemplatePost temp = templatePostService.save(file,input);
            log.info(temp.getId());
            log.info(temp.getFileKey());
            return ResponseEntity.ok(temp.getId());
        }
        catch(Exception e){
            log.debug(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
