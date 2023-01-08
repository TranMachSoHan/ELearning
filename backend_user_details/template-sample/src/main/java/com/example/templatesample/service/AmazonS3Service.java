//package com.example.templatesample.service;
//
//import com.amazonaws.services.s3.model.S3Object;
//
//import java.io.InputStream;
//import java.util.Map;
//import java.util.Optional;
//
//public interface AmazonS3Service {
//    public String upload(
//            String path,
//            String fileName,
//            Optional<Map<String, String>> optionalMetaData,
//            InputStream inputStream);
//    public String convertWithS3UrlPath(String bucketName,String path);
//    public String uploadFileTos3bucket(String bucketName,String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream);
//    public S3Object download(String path, String fileName);
//}