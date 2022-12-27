package com.example.templatesample.model;

import com.example.templatesample.model.enums.Major;
import com.example.templatesample.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@SuperBuilder
@Getter
@Document(collection = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Profile {
        private Major major;
        private Payment payment;


        public void updateStudent(Student newStudent) {
                this.profileID = newStudent.getProfileID();
                this.email = newStudent.getEmail();
                this.name = newStudent.getName();
                this.password = newStudent.getPassword();
                this.age = newStudent.getAge();
                this.education = newStudent.education;
                this.avatar = newStudent.getAvatar();
                this.major = newStudent.getMajor();
                this.payment = newStudent.getPayment();
        }

        public Student(String email, String password, Role userRole, String name, Integer age, String education, String avatar, Major major, Payment payment) {
                this.email = email;
                this.password = password;
                this.userRole = userRole;
                this.name = name;
                this.age = age;
                this.education = education;
                this.avatar = avatar;
                this.major = major;
                this.payment = payment;
        }
}
