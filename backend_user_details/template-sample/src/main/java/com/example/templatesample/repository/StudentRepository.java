package com.example.templatesample.repository;

import com.example.templatesample.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    @Query(value = "{major: ?0}", count = true)
    public long countMajor(String major);
}
