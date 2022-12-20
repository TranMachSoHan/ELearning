package com.example.templatesample.service;

import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Profile;
import com.example.templatesample.model.Student;
import com.example.templatesample.repository.ProfileRepository;
import com.example.templatesample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ResponseEntity<String> createStudent(Student student) {
        profileRepository.save(student);
        return new ResponseEntity<>("Successfully create student " + studentRepository.save(student).getProfileID(), HttpStatus.OK);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public ResponseEntity<String> deleteStudent(String id) {
        try {
            studentRepository.deleteById(id);
            profileRepository.deleteById(id);
            return new ResponseEntity<>("Successfully delete a student " + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot find a student]",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Student> updateStudent(String id, Student student) {
        Optional<Student> studentData = studentRepository.findById(id);
        if(studentData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Student _student = studentData.get();
        _student.updateStudent(student);
        studentRepository.save(_student);
        profileRepository.save(_student);
        return new ResponseEntity<>(_student, HttpStatus.OK);
    }
}
