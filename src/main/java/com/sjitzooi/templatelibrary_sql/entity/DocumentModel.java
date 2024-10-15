package com.sjitzooi.templatelibrary_sql.entity;


import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentModel {

    private String documentKey;
    private String documentName;
    private String documentType;
    private LocalDate uploadDate;
}
