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

    @Autowired
    private TemplatePostRepository templatePostRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NoSQLCallerService noSQLCallerService;

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
        TemplatePost post = templatePostRepository.findById(id).isPresent() ? templatePostRepository.findById(id).get() : null;

        return post;
    }

    public TemplatePost save(MultipartFile file , TemplatePostInput input){
        TemplatePost templatePost = new TemplatePost();
        templatePost.setTitle(input.getTitle());
        templatePost.setDescription(input.getDescription());
        templatePost.setCreatedDate(input.getCreatedDate());
        templatePost.setAuthor(userService.getById(input.getAuthorId()));

        log.debug(file.getOriginalFilename());
        String fileKey = "fileKey_Placeholder";
        if (!file.isEmpty()) {
            String fileType = file.getContentType();

            // Validate file type
            if (!fileType.equals("application/pdf")) {
                throw new IllegalArgumentException("Invalid file type. Only PDF documents are allowed.");
            }

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
}
