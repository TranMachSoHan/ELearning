package com.example.templatesample.controller;


import com.example.templatesample.payload.ApiResponse;
import com.example.templatesample.payload.MessageResponse;
import com.example.templatesample.security.JwtUtils;
import com.example.templatesample.model.Professor;
import com.example.templatesample.model.ProfileDetails;
import com.example.templatesample.model.Student;
import com.example.templatesample.payload.JwtResponse;
import com.example.templatesample.payload.LoginRequest;
import com.example.templatesample.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ProfileService profileService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/sign-up/professor")
    public ResponseEntity<?> createProfessor(@RequestBody Professor professor) {
        Professor result = profileService.createProfessor(professor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/profile/me")
                .buildAndExpand(result.getProfileID()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Successful register professor"));
    }

    @PostMapping("/sign-up/student")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        Student result = profileService.createStudent(student);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/profile/me")
                .buildAndExpand(result.getProfileID()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "Successful register student"));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        Authentication
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.createToken(authentication);
            System.out.println(jwt);

            ProfileDetails userDetails = (ProfileDetails) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            System.out.println(roles);
            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    roles, userDetails.getName()));
    }
}
