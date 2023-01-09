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
import com.example.templatesample.payload.StudentMajorResponse;
import com.example.templatesample.repository.ProfileRepository;
import com.example.templatesample.security.oauth2.user.CurrentUser;
import com.example.templatesample.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
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

    @Cacheable(value = "profiles")
    @GetMapping("/all-profiles")
    public List<Profile> getAllProfiles() {
        return profileService.getAll();
    }

    @GetMapping("/all-professors")
    @Cacheable(value = "professors")
    public List<Professor> getAllProfessors() {return profileService.getAllProfessors();}

    @GetMapping("/all-students")
    public List<Student> getAllStudents() {return profileService.getAllStudents();}

    @GetMapping("/all-professors-string")
    @Cacheable(value = "professorsId")
    public List<String> getAllProfessorsStr() {return profileService.getAllProfessors().stream().map(professor -> professor.getProfileID()).collect(Collectors.toList());}

    @GetMapping("/all-students-string")
    @Cacheable(value = "studentsId")
    public List<String> getAllStudentsStr() {return profileService.getAllStudents().stream().map(student -> student.getProfileID()).collect(Collectors.toList());}

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
        ProfessorGetDTO professorGetDTO = profileService.getProfessorById(id).orElse(null);
        System.out.println(id);
        return new ResponseEntity<>(professorGetDTO, HttpStatus.OK);
    }

    @GetMapping("student/{id}")
    public Optional<Student> getStudent(@PathVariable("id") String id) {

        System.out.println("call student api!!");
        return profileService.getStudentById(id);
    }

    @GetMapping("student/countMajor")
    public StudentMajorResponse countStudentMajor() {
        List<Student> addStudents = profileService.getAllStudents();
        long countSE = addStudents.stream().filter(c -> c.getMajor().equals("Software Engineering")).count();
        long countDM = addStudents.stream().filter(c -> c.getMajor().equals("Digital Marketing")).count();
        long countBM = addStudents.stream().filter(c -> c.getMajor().equals("Business Management")).count();
        long countIT = addStudents.stream().filter(c -> c.getMajor().equals("IT")).count();
        return new StudentMajorResponse(countSE,countDM,countBM,countIT);
    }






}