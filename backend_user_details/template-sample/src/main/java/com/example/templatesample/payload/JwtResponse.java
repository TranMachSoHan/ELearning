package com.example.templatesample.payload;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
        private String token;
        private String type = "Bearer";
        private String id;
        private String email;
        private List<String> roles;

        public JwtResponse(String accessToken, String id, String email, List<String> roles) {
                this.token = accessToken;
                this.id = id;
                this.email = email;
                this.roles = roles;
        }

    }
