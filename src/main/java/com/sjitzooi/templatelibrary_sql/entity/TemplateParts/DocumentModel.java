package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;


import lombok.Data;

import java.util.Date;

@Data
public class DocumentModel {

    private String documentKey;
    private String documentName;
    private String documentType;
    private Date uploadDate;
}
