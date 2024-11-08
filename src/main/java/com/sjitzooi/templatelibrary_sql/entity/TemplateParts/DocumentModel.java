package com.sjitzooi.templatelibrary_sql.entity.TemplateParts;


import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentModel {

    private String documentKey; // Unique identifier for the document
    private String documentName; // Name of the uploaded document
    private String documentType; // Type of the document (e.g., PDF, DOCX)
    private LocalDate uploadDate; // Date of upload

    private byte[] fileData; // The actual file data (as a byte array)
    private long fileSize; // Size of the file in bytes
}
