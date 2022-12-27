package com.example.templatesample.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {
    private String email;

    private String role;

    private String name;

    private String password;

}
