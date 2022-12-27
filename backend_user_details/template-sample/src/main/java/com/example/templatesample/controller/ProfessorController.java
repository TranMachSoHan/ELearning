package com.example.templatesample.controller;

import com.example.templatesample.model.Professor;
import com.example.templatesample.repository.ProfileRepository;
import com.example.templatesample.service.ProfessorService;
import com.example.templatesample.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")

public class ProfessorController {
    @Autowired
    private ProfessorService professorService;


    //Create
    @PostMapping
    public ResponseEntity<String> createProfessor(@RequestBody Professor professor) {
        return professorService.createProfessor(professor);
    }

    //Get all
    @GetMapping("/all-professors")
    public List<Professor> getAllCategories() {
        return professorService.getAllProfessors();
    }

    //Get by ID
    @GetMapping("/{id}")
    public Optional<Professor> getCategoryById(@PathVariable String id) {
        return professorService.getProfessorById(id);
    }

    //Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessor(@PathVariable String id) {
        return professorService.deleteProfessor(id);
    }

//    //Update by ID
//    @PutMapping("/{id}")
//    public ResponseEntity<Category> updateCategoryById(@PathVariable String id, @RequestBody Category category) {
//        return professorService.updateCategory(id, category);
//    }

}
