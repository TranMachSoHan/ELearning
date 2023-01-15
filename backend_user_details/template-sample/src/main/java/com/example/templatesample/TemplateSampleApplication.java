package com.example.templatesample;

import com.example.templatesample.model.Payment;
import com.example.templatesample.model.Professor;
import com.example.templatesample.model.Student;
import com.example.templatesample.model.enums.Role;
import com.example.templatesample.repository.ProfessorRepository;
import com.example.templatesample.repository.ProfileRepository;
import com.example.templatesample.repository.StudentRepository;
import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class TemplateSampleApplication implements CommandLineRunner {
	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(TemplateSampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		profileRepository.deleteAll();
		professorRepository.deleteAll();
		studentRepository.deleteAll();
		final String[] majorList = {"Software Engineering", "Digital Marketing", "Business Management", "IT"};

		FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());
		Faker faker = new Faker();
		for(int i = 0; i < 100; i++) {
			String name = faker.name().fullName();
			String email = "prof" + i + "@gmail.com";
			String password = bCryptPasswordEncoder.encode("123");
			Integer age = faker.number().numberBetween(20,70);
			Date createdDate = faker.date().birthday();
			String description = faker.lorem().characters();
			String avatar = faker.lorem().characters();
			Professor professor = new Professor(email, password, Role.PROFESSOR,name,age,avatar,description,createdDate);
			profileRepository.save(professor);
			professorRepository.save(professor);
		}

		for(int i = 0; i < 4900; i++) {
			String name = faker.name().fullName();
			String email = "stud" + i + "@gmail.com";
			String password = bCryptPasswordEncoder.encode("123");
			Integer age = faker.number().numberBetween(15,50);
			Date createdDate = faker.date().birthday();
			String avatar = faker.lorem().characters();
			Random random = new Random();
			int majorIndex = random.nextInt(majorList.length);
			int minorIndex = random.nextInt(majorList.length);
			String bank = faker.name().name();
			String accountNumber = Integer.toString(faker.number().numberBetween(1000,9999));
			Date issuedDate = faker.date().birthday();
			Payment payment = new Payment(name, bank, accountNumber,issuedDate);
			Student student = new Student(email, password, Role.STUDENT,name,age,avatar,majorList[majorIndex], majorList[minorIndex],payment,createdDate);
			profileRepository.save(student);
			studentRepository.save(student);
			}


//		Professor professor1 = new Professor("prof1@gmail.com", bCryptPasswordEncoder.encode("123"), Role.PROFESSOR,"prof1",null,null,null,"description");
//		Professor professor2 = new Professor("prof2@gmail.com",bCryptPasswordEncoder.encode("123"), Role.PROFESSOR,"prof2",null,null,null,"description");
//		Professor professor3 = new Professor("prof3@gmail.com",bCryptPasswordEncoder.encode("123"), Role.PROFESSOR,"prof3",null,null,null,"description");
//		Professor professor4 = new Professor("prof4@gmail.com",bCryptPasswordEncoder.encode("123"), Role.PROFESSOR,"prof4",null,null,null,"description");
//		Professor professor5 = new Professor("prof5@gmail.com",bCryptPasswordEncoder.encode("123"), Role.PROFESSOR,"prof5",null,null,null,"description");
//
//		Student student1 = new Student("stud1@gmail.com",bCryptPasswordEncoder.encode("123"), Role.STUDENT,"stud1",null,null,null,null,null, null);
//		Student student2 = new Student("stud2@gmail.com",bCryptPasswordEncoder.encode("123"), Role.STUDENT,"stud2",null,null,null,null,null, null);
//		Student student3 = new Student("stud3@gmail.com",bCryptPasswordEncoder.encode("123"), Role.STUDENT,"stud3",null,null,null,null,null, null);
//		Student student4 = new Student("stud4@gmail.com",bCryptPasswordEncoder.encode("123"), Role.STUDENT,"stud4",null,null,null,null,null, null);
//		Student student5 = new Student("stud5@gmail.com",bCryptPasswordEncoder.encode("123"), Role.STUDENT,"stud5",null,null,null,null,null, null);
//		profileRepository.saveAll(
//				Arrays.asList(professor1,professor2,professor3,professor4,professor5,student1,student2,student3,student4,student5));
//		professorRepository.saveAll(
//				Arrays.asList(professor1,professor2,professor3,professor4,professor5));
//		studentRepository.saveAll(
//				Arrays.asList(student1,student2,student3,student4,student5));
//
//
//
	}
}
