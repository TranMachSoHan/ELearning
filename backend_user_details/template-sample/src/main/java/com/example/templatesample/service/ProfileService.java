package com.example.templatesample.service;

import com.example.templatesample.dto.PaymentDTO;
import com.example.templatesample.dto.ProfessorGetDTO;
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

    ResponseEntity<Professor> updateProfessor(ProfessorUpdateDTO professor, String id);
    ResponseEntity<Student> updateStudent(StudentUpdateDTO student, String id);
    List<Profile> getAll();
    List<Professor> getAllProfessors();
    List<Student> getAllStudents();

    Optional<Student> getStudentByEmail(String email);

    //get by id
    Optional<Student> getStudentById(String id);
    Optional<ProfessorGetDTO> getProfessorById(String id);


    long countMajor(String major);

}
