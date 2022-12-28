package com.example.templatesample.service.impl;

import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Profile;
import com.example.templatesample.model.ProfileDetails;
import com.example.templatesample.model.Student;
import com.example.templatesample.model.enums.AuthenticationProvider;
import com.example.templatesample.model.enums.Role;
import com.example.templatesample.repository.ProfessorRepository;
import com.example.templatesample.repository.ProfileRepository;
import com.example.templatesample.repository.StudentRepository;
import com.example.templatesample.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "profileService")
public class ProfileServiceImpl implements ProfileService, UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    @Override
//    public ResponseEntity<String> create(Profile profile) {
//        Profile profileSameUsername = profileRepository.findByUserName(profile.getUserName());
//        if(profileSameUsername != null && profile.getProfileID() != profileSameUsername.getProfileID()) {
//            logger.error(String.format("Duplicate username %s",profile.getUserName()));
//            throw new RuntimeException("Duplicate username");
//        }
//
//        Profile profileSameEmail = profileRepository.findByEmail(profile.getEmail());
//        if(profileSameEmail != null && profile.getProfileID() != profileSameEmail.getProfileID()) {
//            logger.error(String.format("Duplicate email %s",profile.getEmail()));
//            throw new RuntimeException("Duplicate email");
//        }
//
//        profile.setPassword(bCryptPasswordEncoder.encode(profile.getPassword()));
//        return new ResponseEntity<>("Successfully create profile " + profileRepository.save(profile).getProfileID(), HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<String> createProfessor(Professor professor) {
        Profile profileSameEmail = profileRepository.findByEmail(professor.getEmail());
        if(profileSameEmail != null && professor.getProfileID() != profileSameEmail.getProfileID()) {
            logger.error(String.format("Duplicate email %s",professor.getEmail()));
            throw new RuntimeException("Duplicate email");
        }
        professor.setPassword(bCryptPasswordEncoder.encode(professor.getPassword()));
        professorRepository.save(professor);
        return new ResponseEntity<>("Successfully create professor " + profileRepository.save(professor).getProfileID(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> createStudent(Student student) {
        Profile profileSameEmail = profileRepository.findByEmail(student.getEmail());
        if(profileSameEmail != null && student.getProfileID() != profileSameEmail.getProfileID()) {
            logger.error(String.format("Duplicate email %s",student.getEmail()));
            throw new RuntimeException("Duplicate email");
        }
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        studentRepository.save(student);
        return new ResponseEntity<>("Successfully create professor " + profileRepository.save(student).getProfileID(), HttpStatus.OK);
    }



    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public List<Professor> getAllProfessors() {
        return profileRepository.findAllProfessors();
    }

    @Override
    public List<Student> getAllStudents() {
        return profileRepository.findAllStudents();
    }

    @Override
    public Optional<Object> getProfessorOrStudent(Profile profile) {
        Optional<Professor> professor = profileRepository.findProfessorByEmail(profile.getEmail());
        Optional<Student> student = profileRepository.findStudentByEmail(profile.getEmail());
        if(professor.isEmpty()) {
            return Optional.ofNullable(student);
        }
        return Optional.of(professor);
    }

    @Override
    public Profile getByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public void createStudentAfterGoogleLogin(String name, String email, AuthenticationProvider authenticationProvider) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setAuthenticationProvider(authenticationProvider);
        student.setUserRole(Role.STUDENT);
        profileRepository.save(student);
        studentRepository.save(student);
    }

    @Override
    public void updateStudentAfterGoogleLogin(String name, AuthenticationProvider authenticationProvider, Student student) {
        student.setName(name);
        student.setAuthenticationProvider(authenticationProvider);
        profileRepository.save(student);
    }


    @Override
    public Optional<Student> getStudentByEmail(String email) {
        return profileRepository.findStudentByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profile profile = profileRepository.findByEmail(email);
        if(profile == null) {
            logger.error("Invalid email or password!");
            throw new UsernameNotFoundException("Invalid email or password!");
        }
        return ProfileDetails.build(profile);
    }

    private List<SimpleGrantedAuthority> getAuthorities(Profile profile) {
        Role roleByUserId = profile.getUserRole();
        Set<Role> roles = Set.of(roleByUserId);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        for(Role role : roles) {
            simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return simpleGrantedAuthorities;
    }

}
