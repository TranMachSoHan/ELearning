package com.example.templatesample.service;

import com.example.templatesample.dto.ProfessorUpdateDTO;
import com.example.templatesample.dto.StudentUpdateDTO;
import com.example.templatesample.model.Payment;
import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Profile;
import com.example.templatesample.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProfileService {
//    ResponseEntity<String> create(Profile profile);

    Professor createProfessor(Professor professor);

    Student createStudent(Student student);

    Student updatePaymentStudent(Student student, Payment payment);

    ResponseEntity<Professor> updateProfessor(ProfessorUpdateDTO professor, String id);
    ResponseEntity<Student> updateStudent(StudentUpdateDTO student, String id);
    List<Profile> getAll();
    List<Professor> getAllProfessors();
    List<Student> getAllStudents();

    Optional<Object> getProfessorOrStudent(Profile profile);

    Profile getByEmail(String email);

    Optional<Student> getStudentByEmail(String email);
}
