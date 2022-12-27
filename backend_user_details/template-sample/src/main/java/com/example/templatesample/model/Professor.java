package com.example.templatesample.model;

import com.example.templatesample.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "professor")
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends Profile {
    private Integer yearsOfExperience;

    public void updateProfessor(Professor newProfessor) {
        this.profileID = newProfessor.getProfileID();
        this.email = newProfessor.getEmail();
        this.password = newProfessor.getPassword();
        this.age = newProfessor.getAge();
        this.education = newProfessor.education;
        this.avatar = newProfessor.getAvatar();
        this.yearsOfExperience = newProfessor.yearsOfExperience;
    }

    public Professor(String email, String password, Role userRole, String name, Integer age, String education, String avatar, Integer yearsOfExperience) {
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.name = name;
        this.age = age;
        this.education = education;
        this.avatar = avatar;
        this.yearsOfExperience = yearsOfExperience;
    }

}
