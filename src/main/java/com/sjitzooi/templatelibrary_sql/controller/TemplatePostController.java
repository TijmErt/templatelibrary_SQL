package com.sjitzooi.templatelibrary_sql.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.DocumentModel;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostModel;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@DgsComponent
public class TemplatePostController {

    @Autowired
    private TemplatePostService templatePostService;

    @DgsQuery
    public TemplatePostModel getTemplatePost(String id) {
        TemplatePostModel model = new TemplatePostModel();
        model.setTemplatePost(templatePostService.getById(id));

        DocumentModel docModel = new DocumentModel();
        docModel.setDocumentKey(model.getTemplatePost().getDocumentKey());
        docModel.setDocumentType(".tempType");
        docModel.setDocumentName("tempDocName");
        docModel.setUploadDate(new Date());

        model.setDocumentModel(docModel);
        return model;
    }
}
