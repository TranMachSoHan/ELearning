package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class ApiGateAwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateAwayApplication.class, args);
    }

    @GetMapping("/")
    public String pageableCourseBySkill(){
        return "Hello world";
    }
}