package com.example.templatesample.security.oauth2.user;

import com.example.templatesample.model.Student;
import com.example.templatesample.model.enums.Role;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class StudentPrincipal implements OAuth2User, UserDetails {
    private String id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;


    public StudentPrincipal(String id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public static StudentPrincipal create(Student student) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(Role.STUDENT.toString()));

        return new StudentPrincipal(
                student.getProfileID(),
                student.getEmail(),
                student.getPassword(),
                authorities
        );
    }

    public static StudentPrincipal create(Student student, Map<String, Object> attributes) {
        StudentPrincipal studentPrincipal = StudentPrincipal.create(student, attributes);
        studentPrincipal.setAttributes(attributes);
        return studentPrincipal;
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}
