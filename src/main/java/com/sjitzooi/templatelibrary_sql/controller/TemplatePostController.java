package com.sjitzooi.templatelibrary_sql.controller;

import com.netflix.graphql.dgs.*;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.*;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@DgsComponent
@Transactional
@CrossOrigin(origins ="*" )
@Slf4j
public class TemplatePostController {
    @Autowired
    public TemplatePostController(TemplatePostService templatePostService , SimpMessagingTemplate messagingTemplate) {
        this.templatePostService = templatePostService;
        this.messagingTemplate = messagingTemplate;
    }
    private final SimpMessagingTemplate messagingTemplate;
    private TemplatePostService templatePostService;

    @DgsQuery
    public TemplatePostConnection getFilteredTemplatePosts(@InputArgument PageInfo pageInfo, @InputArgument String searchTerm){
        try{
            TemplatePostConnection templatePosts = templatePostService.getFilteredTemplatePosts(searchTerm,pageInfo);
            return templatePosts;
        }
        catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("Failed to fetch TemplatePosts: " + e.getMessage());
        }
    }


    @DgsQuery
    public TemplatePost getTemplatePost(@InputArgument String id) {

        try {
            TemplatePost model = templatePostService.getById(id);
            log.info("TemplatePost fetched with ID: {}", model.getId());
            return model;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to fetch TemplatePost: " + e.getMessage());
        }
    }

    @Secured("ROLE_USER")
    @DgsMutation
    public String createTemplatePost(@InputArgument MultipartFile file, @InputArgument TemplatePostInput input) throws IOException {

        try{
            TemplatePost temp = templatePostService.save(file,input);
            log.info("TemplatePost created with ID: {}", temp.getId());
            log.info("File stored with key: {}", temp.getFileKey());

            return temp.getId();
        }
        catch (Exception e) {
            log.error("Error creating TemplatePost: {}", e.getMessage());
            throw new RuntimeException("Failed to create TemplatePost: " + e.getMessage());
        }
    }

    @Secured("ROLE_USER")
    @DgsMutation
    public TemplatePost updateTemplatePost(@InputArgument String postID, @InputArgument UpdateTemplatePostInput input) {
        TemplatePost model;
        try{
            model = templatePostService.updateTemplatePost(postID, input);
        }
        catch (Exception e) {
            log.error("Error creating TemplatePost: {}", e.getMessage());
            throw new RuntimeException("Failed to create TemplatePost: " + e.getMessage());
        }
        Object updatedInfo = new Object(){
            String id = model.getId();
            String title = model.getTitle();
            String message = "The post has been updated, check it out";

            public String getMessage() {return message;}

            public String getTitle() {
                return title;
            }
            public String getId() {return id;}
        };
        messagingTemplate.convertAndSend("/topic/post-updates/" + postID, updatedInfo);
        return model;
    }
}
