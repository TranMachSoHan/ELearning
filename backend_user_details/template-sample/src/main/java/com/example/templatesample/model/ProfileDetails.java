package com.example.templatesample.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProfileDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    public ProfileDetails(String id, String email, String password,
                              Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static ProfileDetails build(Profile profile) {
        List<GrantedAuthority> authorities = new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(profile.getUserRole().name())));
        return new ProfileDetails(
                profile.getProfileID(),
                profile.getEmail(),
                profile.getPassword(),
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
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
}
