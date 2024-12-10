package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.*;
import com.sjitzooi.templatelibrary_sql.repository.TemplatePostRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@Slf4j
public class TemplatePostService {

    private static final String ERROR_MESSAGE_SERVICE_LAYER= "TemplatePostService:";

    private TemplatePostRepository templatePostRepository;

    private UserService userService;

    private NoSQLCallerService noSQLCallerService;

    @Autowired
    public TemplatePostService(TemplatePostRepository templatePostRepository, UserService userService, NoSQLCallerService noSQLCallerService) {
        this.templatePostRepository = templatePostRepository;
        this.userService = userService;
        this.noSQLCallerService = noSQLCallerService;
    }

    public Page<TemplatePost> getFilteredTemplatePosts(TemplatePostFilter filter, int page, int size) {
//        Sort sort = Sort.by(filter.getSortBy());
//        if (filter.getAscending() != null && !filter.getAscending()) {
//            sort = sort.descending();
//        }
//
//        PageRequest pageRequest = PageRequest.of(page, size, sort);
//        return templatePostRepository.findFilteredTemplatePosts(filter.getName(), filter.getCategories() != null ? filter.getCategories().stream().map(Tag::getId).toList() : null, pageRequest);
        return null;
    }

    public TemplatePost getById(String id){
        try{
            TemplatePost post;
            if(templatePostRepository.findById(id).isPresent()) post = templatePostRepository.findById(id).get();
            else post = null;
            return post;
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" getByID: {}",e.getMessage());
            throw e;
        }

    }

    public TemplatePost save(MultipartFile file , TemplatePostInput input){
        try{
            TemplatePost templatePost = new TemplatePost();
            templatePost.setTitle(input.getTitle());
            templatePost.setDescription(input.getDescription());
            templatePost.setCreatedDate(input.getCreatedDate());
            templatePost.setAuthor(userService.getById(input.getAuthorId()));

            log.debug(file.getOriginalFilename());
            String fileKey = "fileKey_Placeholder";
            if (!file.isEmpty()) {

                // Validate file type
                FileValidationChecker.isValid(file);
                String result = noSQLCallerService.uploadFile(file);
                if(result != null || !result.equals("")) {
                    fileKey = result;
                }
            }
            templatePost.setFileKey(fileKey);
            templatePostRepository.save(templatePost);
            templatePostRepository.flush();
            return templatePost;
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" save: {}",e.getMessage());
            throw e;
        }
    }
}
