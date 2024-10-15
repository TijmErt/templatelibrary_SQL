package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TemplatePostInput {

    private String title;

    private String description;

    private LocalDate createdDate;

    private String authorId;

    /* necessary data for DocumentModel */
    private String documentName;
    private String documentType;
    //private File image;
}
