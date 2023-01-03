package com.example.templatesample.service.impl;

import com.example.templatesample.dto.PaymentDTO;
import com.example.templatesample.dto.ProfessorUpdateDTO;
import com.example.templatesample.dto.StudentUpdateDTO;
import com.example.templatesample.exception.BadRequestException;
import com.example.templatesample.model.*;
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

    @Override
    public Professor createProfessor(Professor professor) {
        Profile profileSameEmail = profileRepository.findByEmail(professor.getEmail());
        if(profileSameEmail != null && professor.getProfileID() != profileSameEmail.getProfileID()) {
            logger.error(String.format("Duplicate email %s",professor.getEmail()));
            throw new BadRequestException("Duplicate email");
        }
        professor.setPassword(bCryptPasswordEncoder.encode(professor.getPassword()));
        profileRepository.save(professor);
        return professorRepository.save(professor);
    }

    @Override
    public Student createStudent(Student student) {
        Profile profileSameEmail = profileRepository.findByEmail(student.getEmail());
        if(profileSameEmail != null && student.getProfileID() != profileSameEmail.getProfileID()) {
            logger.error(String.format("Duplicate email %s",student.getEmail()));
            throw new BadRequestException("Duplicate email");
        }
        student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
        studentRepository.save(student);
        return profileRepository.save(student);
    }

    @Override
    public ResponseEntity<Student> addPaymentStudent(String id, PaymentDTO paymentDTO) {
        Optional<Student> studentData = studentRepository.findById(id);
        if(studentData.isPresent()) {
            Student _student = studentData.get();
            Payment payment = new Payment(paymentDTO.getBank(), paymentDTO.getAccountNumber(), _student.getProfileID());
            _student.setPayment(payment);
            studentRepository.save(_student);
            return new ResponseEntity<>(profileRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<Professor> updateProfessor(ProfessorUpdateDTO professor, String id) {
        Optional<Professor> professorData = professorRepository.findById(id);
        if(professorData.isPresent()) {
            Professor _professor = professorData.get();
            _professor.setName(professor.getName());
            _professor.setEducation(professor.getEducation());
            _professor.setAge(professor.getAge());
            _professor.setAvatar(professor.getAvatar());
            _professor.setDescription(professor.getDescription());
            professorRepository.save(_professor);
            return new ResponseEntity<>(profileRepository.save(_professor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Student> updateStudent(StudentUpdateDTO student, String id) {
        Optional<Student> studentData = studentRepository.findById(id);
        if(studentData.isPresent()) {
            Student _student = studentData.get();
            _student.setName(student.getName());
            _student.setEducation(student.getEducation());
            _student.setAge(student.getAge());
            _student.setAvatar(student.getAvatar());
            _student.setMajor(student.getMajor());
            _student.setMinor(student.getMinor());
            studentRepository.save(_student);
            return new ResponseEntity<>(profileRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
