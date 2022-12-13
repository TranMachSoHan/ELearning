package course_eLearning.course_eLearning;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/course")
public class CourseELearningApplication {

	@Autowired
	private CourseRepository courseRepository;


	public static void main(String[] args) {
		SpringApplication.run(CourseELearningApplication.class, args);
	}

}
