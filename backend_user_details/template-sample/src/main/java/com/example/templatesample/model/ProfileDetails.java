package com.example.templatesample.model;

import com.amazonaws.services.dynamodbv2.xspec.S;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class ProfileDetails implements UserDetails, OAuth2User {
    private static final long serialVersionUID = 1L;
    private String id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private Map<String,Object> attributes;
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

    public static ProfileDetails create(Profile profile, Map<String, Object> attributes) {
        ProfileDetails profileDetails = ProfileDetails.build(profile);
        profileDetails.setAttributes(attributes);
        return profileDetails;
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
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
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

    @Override
    public String getName() {
        return String.valueOf(id);
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
