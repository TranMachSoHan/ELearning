//package com.example.templatesample.service.impl;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.services.s3.model.S3Object;
//import com.example.templatesample.repository.FileMetaRepository;
//import com.example.templatesample.service.AmazonS3Service;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.InputStream;
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@Slf4j
//public class AmazonS3ServiceImpl implements AmazonS3Service {
//    @Autowired
//    private AmazonS3 amazonS3;
//
//    @Autowired
//    private FileMetaRepository fileMetaRepository;
//
//    @Override
//    public String upload(String bucketName, String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream) {
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        optionalMetaData.ifPresent(map -> {
//            if (!map.isEmpty()){
//                map.forEach(objectMetadata::addUserMetadata);
//            }
//        });
//        String randomUUID = String.valueOf(UUID.randomUUID());
//        String path = String.format("%s/%s", bucketName, randomUUID);
//        log.debug("Path: " + path + ", FileName:" + fileName);
//        amazonS3.putObject(path, fileName, inputStream, objectMetadata);
//        return amazonS3.getUrl(bucketName, randomUUID+"/"+fileName).toString();
//    }
//
//    public String uploadFileTos3bucket(String bucketName,String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream) {
//        ObjectMetadata objectMetadata = new ObjectMetadata();
//        optionalMetaData.ifPresent(map -> {
//            if (!map.isEmpty()){
//                map.forEach(objectMetadata::addUserMetadata);
//            }
//        });
//        String path = String.format("%s/%s",  UUID.randomUUID(), fileName);
//        log.debug("Path: " + path + ", FileName:" + fileName);
//        amazonS3.putObject(new PutObjectRequest(bucketName, path, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
//
//        return path;
//    }
//
//    public String convertWithS3UrlPath(String bucketName,String path){
//        return amazonS3.getUrl(bucketName, path).toString();
//    }
//    public S3Object download(String path, String fileName) {
//        return amazonS3.getObject(path, fileName);
//    }
//
//}
