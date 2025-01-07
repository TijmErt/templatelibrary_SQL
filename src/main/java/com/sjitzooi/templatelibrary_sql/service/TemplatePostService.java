package com.sjitzooi.templatelibrary_sql.service;

import com.netflix.graphql.dgs.InputArgument;
import com.sjitzooi.templatelibrary_sql.entity.Category;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.*;
import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.repository.TemplatePostRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class TemplatePostService {

    private static final String ERROR_MESSAGE_SERVICE_LAYER= "TemplatePostService:";

    private TemplatePostRepository templatePostRepository;

    private UserService userService;

    private NoSQLCallerService noSQLCallerService;
    private CategoryService categoryService;

    @Autowired
    public TemplatePostService(TemplatePostRepository templatePostRepository, UserService userService, NoSQLCallerService noSQLCallerService, CategoryService categoryService) {
        this.templatePostRepository = templatePostRepository;
        this.userService = userService;
        this.noSQLCallerService = noSQLCallerService;
        this.categoryService = categoryService;
    }

    public TemplatePostConnection getFilteredTemplatePosts( String searchTerm, PageInfo pageInfo) {
        try{
            // Convert sort order to Sort.Direction
            Sort.Direction direction = "desc".equalsIgnoreCase(pageInfo.getSortOrder()) ? Sort.Direction.DESC : Sort.Direction.ASC;

            // Create dynamic Sort based on field and order
            Sort sort = Sort.by(direction, pageInfo.getSortField());
            Pageable pageable = PageRequest.of(pageInfo.getPage(), pageInfo.getLimit(),sort);
            Page<Object[]> templatePostPage = templatePostRepository.getFilteredTemplatePosts(searchTerm, pageable
            );
            List<TemplatePost> posts = templatePostPage.map(row ->{
                TemplatePost post = (TemplatePost) row[0];
                post.setAvgRating((double)row[1]);
                return post;
            }).getContent();


            // Create PageInfo for pagination metadata
            PageInfo newPageInfo = new PageInfo(
                    pageInfo.getLimit(),
                    pageInfo.getPage(),
                    (int) templatePostPage.getTotalElements(),
                    templatePostPage.getTotalPages(),
                    pageInfo.getSortField() ,
                    pageInfo.getSortOrder() ,
                    templatePostPage.hasPrevious() ,
                    templatePostPage.hasNext()
            );

            return new TemplatePostConnection(posts, newPageInfo);
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" getFilteredTemplatePosts: {}",e.getMessage());
            throw e;
        }
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

    public TemplatePost updateTemplatePost(@InputArgument String id, @InputArgument UpdateTemplatePostInput input){
        TemplatePost templatePost;
        try{
            templatePost = this.getById(id);

            if (input.getTitle() != null) {
                templatePost.setTitle(input.getTitle());
            }
            if (input.getDescription() != null) {
                templatePost.setDescription(input.getDescription());
            }
            if (input.getCreatedDate() != null) {
                templatePost.setCreatedDate(input.getCreatedDate());
            }
            if (input.getCategories() != null) {
                List<Category> categories = categoryService.findAllByIds(input.getCategories());
                templatePost.setCategories(categories);
            }
        }
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" updateTemplatePost: {}",e.getMessage());
            throw e;
        }
        return templatePost;
    }
}
