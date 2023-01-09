package com.example.templatesample.config;

import com.example.templatesample.payload.FileMetaResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class RestTemplateConfig {
    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String BASE_URL_API = "http://localhost:8082";


    public static FileMetaResponse getUploadFileMeta(MultipartFile file){
        ResponseEntity<FileMetaResponse> response
                = restTemplate.postForEntity(BASE_URL_API + "/fileMeta/uploadFile/", file, FileMetaResponse.class);

        if( response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        else {
            throw new NullPointerException();
        }
    }
}