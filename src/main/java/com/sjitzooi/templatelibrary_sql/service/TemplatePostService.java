package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostInput;
import com.sjitzooi.templatelibrary_sql.entity.User;
import com.sjitzooi.templatelibrary_sql.repository.TemplatePostRepository;
import com.sjitzooi.templatelibrary_sql.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TemplatePostService {

    @Autowired
    private TemplatePostRepository templatePostRepository;

    @Autowired
    private UserService userService;

    public List<TemplatePost> getAll(){
        return templatePostRepository.findAll();
    }
    public TemplatePost getById(String id){
        return templatePostRepository.findById(id).isPresent() ? templatePostRepository.findById(id).get() : null;
    }

    public TemplatePost save(TemplatePostInput input){
        TemplatePost templatePost = new TemplatePost();

        templatePost.setTitle(input.getTitle());
        templatePost.setDescription(input.getDescription());
        templatePost.setCreatedDate(input.getCreatedDate());
        templatePost.setAuthor(userService.getById(input.getAuthorId()));
        //Add code for uploading file
        templatePost.setDocumentKey("documentKey");
        templatePostRepository.save(templatePost);
        //save to backend
        templatePostRepository.flush();
        return templatePost;
    }
}
