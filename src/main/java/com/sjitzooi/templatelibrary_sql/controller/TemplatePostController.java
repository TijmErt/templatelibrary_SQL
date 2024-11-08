package com.sjitzooi.templatelibrary_sql.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.DocumentModel;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePost;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostInput;
import com.sjitzooi.templatelibrary_sql.entity.TemplateParts.TemplatePostModel;
import com.sjitzooi.templatelibrary_sql.service.TemplatePostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public void createTemplatePost(@InputArgument TemplatePostInput input, @InputArgument("file") MultipartFile file) {
        DocumentModel docModel = new DocumentModel();

        if (file != null && !file.isEmpty()) {
            String fileType = file.getContentType();

            // Validate file type
            if (!fileType.equals("application/pdf") && !fileType.equals("application/msword")) {
                throw new IllegalArgumentException("Invalid file type. Only PDF and Word documents are allowed.");
            }

            try {
                // Read file data
                byte[] fileData = file.getBytes();
                long fileSize = file.getSize();
                String fileName = file.getOriginalFilename();

                // Set properties on DocumentModel
                docModel.setFileData(fileData);
                docModel.setDocumentName(fileName);
                docModel.setDocumentType(fileType);
                docModel.setFileSize(fileSize);
                docModel.setUploadDate(LocalDate.now());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read file data", e);
            }
        }

        TemplatePostModel model = new TemplatePostModel();
        TemplatePost temp = templatePostService.save(input);
        String id = temp.getId();
        TemplatePost templatePost = templatePostService.getById(id);
        model.setTemplatePost(templatePost);

        // Set the DocumentModel in the response
        model.setDocumentModel(docModel);

        System.out.println(model.getTemplatePost().getAuthor().getUserName());
        System.out.println(model.getDocumentModel());
        //return model;
    }
}
