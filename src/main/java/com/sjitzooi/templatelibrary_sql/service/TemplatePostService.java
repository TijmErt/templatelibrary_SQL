package com.sjitzooi.templatelibrary_sql.service;

import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostInput;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostFilter;
import com.sjitzooi.templatelibrary_sql.repository.TemplatePostRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TemplatePostService {

    @Autowired
    private TemplatePostRepository templatePostRepository;

    @Autowired
    private UserService userService;

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
