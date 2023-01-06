package com.example.templatesample.controller;

import com.example.templatesample.dto.PaymentDTO;
import com.example.templatesample.dto.ProfessorGetDTO;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/profile")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ProfileController {

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

    @GetMapping("/all-professors-string")
    public List<String> getAllProfessorsStr() {return profileService.getAllProfessors().stream().map(professor -> professor.getProfileID()).collect(Collectors.toList());}

    @GetMapping("/all-students-string")
    public List<String> getAllStudentsStr() {return profileService.getAllStudents().stream().map(student -> student.getProfileID()).collect(Collectors.toList());}

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

    @GetMapping("professor/{id}")
    public ResponseEntity<ProfessorGetDTO> getProfessor(@PathVariable("id") String id) {
        System.out.println(id);
        return profileService.getProfessorById(id);
    }

    @GetMapping("student/{id}")
    public Optional<Student> getStudent(@PathVariable("id") String id) {
        return profileService.getStudentById(id);
    }


    @PutMapping("student/{id}/add-payment")
    public ResponseEntity<Student> addPayment(@PathVariable("id") String id, @RequestBody PaymentDTO paymentDTO) {
        return profileService.addPaymentStudent(id,paymentDTO);
    }






}