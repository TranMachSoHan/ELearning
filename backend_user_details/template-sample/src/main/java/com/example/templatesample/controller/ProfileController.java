package com.example.templatesample.controller;

import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Profile;
import com.example.templatesample.model.Student;
import com.example.templatesample.service.AuthenticationFacadeService;
import com.example.templatesample.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    public static final String ROLE_PROFESSOR = "ROLE_PROFESSOR";
    public static final String ROLE_STUDENT = "ROLE_STUDENT";

    @Autowired
    private ProfileService profileService;

    @Autowired
    public AuthenticationManager authenticationManager;


    @GetMapping("/all-profiles")
    public List<Profile> getAllProfiles() {
        return profileService.getAll();
    }

    @GetMapping("/all-professors")
    public List<Professor> getAllProfessors() {return profileService.getAllProfessors();}

    @GetMapping("/all-students")
    public List<Student> getAllStudents() {return profileService.getAllStudents();}


    @PostMapping("/sign-up/professor")
    public ResponseEntity<String> createProfessor(@RequestBody Professor professor) {
        return profileService.createProfessor(professor);

    }

    @PostMapping("/sign-up/student")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        return profileService.createStudent(student);

    }

    @PostMapping("/sign-in")
    public ResponseEntity<Object> signIn(@RequestBody Profile profile) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(profile.getUserName(), profile.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid");
            //print
        }
        return new ResponseEntity<>(profileService.getProfessorOrStudent(profile),HttpStatus.OK);


    }

}