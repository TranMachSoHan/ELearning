package com.example.templatesample.service;

import com.example.templatesample.model.Professor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProfessorService {
    ResponseEntity<String> createProfessor(Professor professor);
    List<Professor> getAllProfessors();
    Optional<Professor> getProfessorById(String id);
    ResponseEntity<String> deleteProfessor(String id);
    ResponseEntity<Professor> updateProfessor(String id, Professor professor);

}
