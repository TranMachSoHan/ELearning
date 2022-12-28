package com.example.templatesample.repository;

import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Profile;
import com.example.templatesample.model.Student;
import com.example.templatesample.model.enums.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    Profile findByEmail(String email);

    List<Profile> findAllByUserRole(Role userRole);

    @Query("{'_class': 'com.example.templatesample.model.Professor'}")
    List<Professor> findAllProfessors();

    @Query("{'_class': 'com.example.templatesample.model.Student'}")
    List<Student> findAllStudents();

    @Query("{'_class': 'com.example.templatesample.model.Professor','email': ?0}")
    Optional<Professor> findProfessorByEmail(String email);

    @Query("{'_class': 'com.example.templatesample.model.Student','email': ?0}")
    Optional<Student> findStudentByEmail(String email);


}
