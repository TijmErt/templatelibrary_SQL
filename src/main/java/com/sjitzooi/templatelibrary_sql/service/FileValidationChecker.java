package com.sjitzooi.templatelibrary_sql.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
public class FileValidationChecker {

    private static boolean isValidFileType(MultipartFile file) {
        // Allowed MIME types
        String[] allowedMimeTypes = {
                "application/msword", // .DOC
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .DOCX
                "application/vnd.oasis.opendocument.text", // .ODT
                "application/pdf" // .PDF
        };
        log.info("Checking file type: {}", file.getContentType());
        // Tika instance
        Tika tika = new Tika();
        try {
            // Detect MIME type of the file
            String mimeType = tika.detect(file.getInputStream());

            // Check if MIME type is in the allowed list
            for (String allowedType : allowedMimeTypes) {
                log.info("AllowedType: "+allowedType +"= CurrentType:  "+ mimeType);
                if (mimeType.equals(allowedType)) {
                    return true; // Valid file type

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Invalid file type
    }

    private static boolean isValidSize(long size) {
        log.info("Checking file size: {}", size);
        return size <= 20 * 1024 * 1024; // Max 20 MB
    }

    public static boolean isValid(MultipartFile file) {
        if(!isValidFileType(file)) throw new IllegalArgumentException("Invalid file type. Only .DOC, .DOCX, .ODT andPDF documents are allowed.");
        if(!isValidSize(file.getSize())) throw new IllegalArgumentException("File is to large, bigger than 20mb");
        if (file.isEmpty()) throw new IllegalArgumentException("File is empty");
        return true;
    }


}
