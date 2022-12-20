package com.example.templatesample.service;

import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Profile;
import com.example.templatesample.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
//    ResponseEntity<String> create(Profile profile);

    ResponseEntity<String> createProfessor(Professor professor);

    ResponseEntity<String> createStudent(Student student);
    List<Profile> getAll();
    List<Professor> getAllProfessors();
    List<Student> getAllStudents();

    ResponseEntity<Object> getProfessorOrStudent(Profile profile);

}
