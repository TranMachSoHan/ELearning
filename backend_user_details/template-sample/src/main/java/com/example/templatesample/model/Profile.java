package com.example.templatesample.model;


import com.example.templatesample.model.enums.AuthenticationProvider;
import com.example.templatesample.model.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profile implements Serializable {
    @Id
    public String profileID;
    public String email;
    public String password;

    public Role userRole;
    public String name;
    public Integer age;
    public String education;
    public String avatar;

    public Date createdDate;
}
