package com.sjitzooi.templatelibrary_sql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.*;

@Service
@Slf4j
public class NoSQLCallerService {

    private RestTemplate restTemplate;

    @Autowired
    public NoSQLCallerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static String url = "http://localhost:8081/api/DocumentModelController";
    public String uploadFile(MultipartFile file) {
        if(file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        // Create the request entity with the multipart body and headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Call the file upload endpoint
        String serverUrl = url+"/add";
        ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.POST, requestEntity, String.class);

        // Return the server's response
        return response.getBody();
    }
}

