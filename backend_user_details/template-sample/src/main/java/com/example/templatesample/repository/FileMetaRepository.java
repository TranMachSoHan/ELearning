package com.example.templatesample.repository;

import com.example.templatesample.model.FileMeta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileMetaRepository extends MongoRepository<FileMeta, String> {
}
