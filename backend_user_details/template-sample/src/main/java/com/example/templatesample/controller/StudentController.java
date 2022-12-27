package com.example.templatesample.controller;

import com.example.templatesample.model.Student;
import com.example.templatesample.repository.ProfileRepository;
import com.example.templatesample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //Create
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    //Get all
    @GetMapping("/all-students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    //Get by ID
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable String id) {
        return studentService.getStudentById(id);
    }

    //Delete by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(id);
    }

//    //Update by ID
//    @PutMapping("/{id}")
//    public ResponseEntity<Category> updateCategoryById(@PathVariable String id, @RequestBody Category category) {
//        return professorService.updateCategory(id, category);
//    }
}
