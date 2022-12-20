package com.example.templatesample.service;

import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    ResponseEntity<String> createStudent(Student student);
    List<Student> getAllStudents();
    Optional<Student> getStudentById(String id);
    //    ResponseEntity<Professor> updateStudent(String id, Student student);
    ResponseEntity<String> deleteStudent(String id);
    ResponseEntity<Student> updateStudent(String id, Student student);
}
