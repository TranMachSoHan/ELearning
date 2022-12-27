package com.example.templatesample;

import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Student;
import com.example.templatesample.model.enums.Role;
import com.example.templatesample.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class TemplateSampleApplication implements CommandLineRunner {
	@Autowired
	private ProfileRepository profileRepository;


	public static void main(String[] args) {
		SpringApplication.run(TemplateSampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		profileRepository.deleteAll();
		Professor professor1 = new Professor("prof1@gmail.com","123", Role.PROFESSOR,"prof1",null,null,null,null);
		Professor professor2 = new Professor("prof2@gmail.com","123", Role.PROFESSOR,"prof2",null,null,null,null);
		Professor professor3 = new Professor("prof3@gmail.com","123", Role.PROFESSOR,"prof3",null,null,null,null);
		Professor professor4 = new Professor("prof4@gmail.com","123", Role.PROFESSOR,"prof4",null,null,null,null);
		Professor professor5 = new Professor("prof5@gmail.com","123", Role.PROFESSOR,"prof5",null,null,null,null);

		Student student1 = new Student("stud1@gmail.com","123", Role.STUDENT,"stud1",null,null,null,null,null);
		Student student2 = new Student("stud2@gmail.com","123", Role.STUDENT,"stud2",null,null,null,null,null);
		Student student3 = new Student("stud3@gmail.com","123", Role.STUDENT,"stud3",null,null,null,null,null);
		Student student4 = new Student("stud4@gmail.com","123", Role.STUDENT,"stud4",null,null,null,null,null);
		Student student5 = new Student("stud5@gmail.com","123", Role.STUDENT,"stud5",null,null,null,null,null);
		profileRepository.saveAll(
				Arrays.asList(professor1,professor2,professor3,professor4,professor5,student1,student2,student3,student4,student5));



	}
}
