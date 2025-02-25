package com.sjitzooi.templatelibrary_sql.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static final String ERROR_MESSAGE_SERVICE_LAYER= "NoSQLCallerService:";

    private RestTemplate restTemplate;

    @Value("${url.nosql}")
    private String urlNosql;

    @Autowired
    public NoSQLCallerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String uploadFile(MultipartFile file) {
        try{
            String url = urlNosql +"/api/DocumentModelController";
            if(file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            log.info(url);
            log.info(urlNosql);
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
        catch(Exception e){
            log.error(ERROR_MESSAGE_SERVICE_LAYER +" uploadFile: {}", e.getMessage());
            throw e;
        }
    }
}

