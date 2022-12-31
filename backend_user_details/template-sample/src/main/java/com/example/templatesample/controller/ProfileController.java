package com.example.templatesample.controller;

import com.example.templatesample.dto.ProfessorUpdateDTO;
import com.example.templatesample.dto.StudentUpdateDTO;
import com.example.templatesample.exception.ResourceNotFoundException;
import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Profile;
import com.example.templatesample.model.ProfileDetails;
import com.example.templatesample.model.Student;
import com.example.templatesample.repository.ProfileRepository;
import com.example.templatesample.security.oauth2.user.CurrentUser;
import com.example.templatesample.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    public static final String ROLE_PROFESSOR = "ROLE_PROFESSOR";
    public static final String ROLE_STUDENT = "ROLE_STUDENT";

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/all-profiles")
    public List<Profile> getAllProfiles() {
        return profileService.getAll();
    }

    @GetMapping("/all-professors")
    public List<Professor> getAllProfessors() {return profileService.getAllProfessors();}

    @GetMapping("/all-students")
    public List<Student> getAllStudents() {return profileService.getAllStudents();}

    @GetMapping("/test")
    public Optional<Student> getStudent() {return profileService.getStudentByEmail("khangnick14@gmail.com");}

    @GetMapping("/me")
    public Profile getCurrentProfile(@CurrentUser ProfileDetails profileDetails) {
        return profileRepository.findById(profileDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User","id",profileDetails.getId()));
    }

    @PutMapping("professor/{id}")
    public ResponseEntity<Professor> updateProfessor(@PathVariable("id") String id, @RequestBody ProfessorUpdateDTO professorUpdateDTO) {
        return profileService.updateProfessor(professorUpdateDTO,id);
    }

    @PutMapping("student/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") String id, @RequestBody StudentUpdateDTO studentUpdateDTO) {
        return profileService.updateStudent(studentUpdateDTO,id);
    }




}