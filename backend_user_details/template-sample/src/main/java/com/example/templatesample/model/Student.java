package com.example.templatesample.model;

import com.example.templatesample.model.enums.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;


@SuperBuilder
@Getter
@Setter
@Document(collection = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Profile {
        private String major;

        private String minor;
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
                this.minor = newStudent.getMinor();
                this.payment = newStudent.getPayment();
        }

        public Student(String email, String password, Role userRole, String name, Integer age, String education, String avatar, String major, String minor, Payment payment) {
                this.email = email;
                this.password = password;
                this.userRole = userRole;
                this.name = name;
                this.age = age;
                this.education = education;
                this.avatar = avatar;
                this.major = major;
                this.minor = minor;
                this.payment = payment;
        }
}
