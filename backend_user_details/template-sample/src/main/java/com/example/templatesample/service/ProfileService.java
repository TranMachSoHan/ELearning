package com.example.templatesample.service;

import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Profile;
import com.example.templatesample.model.Student;
import com.example.templatesample.model.enums.AuthenticationProvider;
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

    Optional<Object> getProfessorOrStudent(Profile profile);

    Profile getByEmail(String email);

    void createStudentAfterGoogleLogin(String name, String email, AuthenticationProvider authenticationProvider);

    void updateStudentAfterGoogleLogin(String name, AuthenticationProvider authenticationProvider, Student student);

    Student getStudentByEmail(String email);
}
