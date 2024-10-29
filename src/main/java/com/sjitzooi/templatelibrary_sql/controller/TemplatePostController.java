package com.sjitzooi.templatelibrary_sql.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.sjitzooi.templatelibrary_sql.entity.DocumentModel;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostInput;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostModel;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;

@DgsComponent
@Transactional
@CrossOrigin(origins ="*" )
public class TemplatePostController {

    @Autowired
    private TemplatePostService templatePostService;

    @DgsQuery
    public TemplatePostModel getTemplatePost(@InputArgument String id) {
        TemplatePostModel model = new TemplatePostModel();
        model.setTemplatePost(templatePostService.getById(id));

        DocumentModel docModel = new DocumentModel();
        docModel.setDocumentKey(model.getTemplatePost().getDocumentKey());
        docModel.setDocumentType(".tempType");
        docModel.setDocumentName("tempDocName");
        docModel.setUploadDate( LocalDate.now());

        model.setDocumentModel(docModel);
        return model;
    }

    @DgsMutation
    public TemplatePostModel createTemplatePost(@InputArgument TemplatePostInput input) {
        TemplatePostModel model = new TemplatePostModel();
        TemplatePost temp = templatePostService.save(input);
        String id = temp.getId();
        TemplatePost templatePost = templatePostService.getById(id);
        model.setTemplatePost(templatePost);

        DocumentModel docModel = new DocumentModel();
        docModel.setDocumentType(input.getDocumentType());
        docModel.setDocumentName(input.getDocumentName());
        docModel.setDocumentKey(model.getTemplatePost().getDocumentKey());
        docModel.setUploadDate( LocalDate.now());

        model.setDocumentModel(docModel);

        System.out.println(model.getTemplatePost().getAuthor().getUserName());
        System.out.println(model.getDocumentModel());
        return model;
    }
}
