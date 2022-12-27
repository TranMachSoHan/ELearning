package course_eLearning.course_eLearning;

import course_eLearning.course_eLearning.dto.CourseListDTO;
import course_eLearning.course_eLearning.model.Comment;
import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.Skill;
import course_eLearning.course_eLearning.repository.CommentRepository;
import course_eLearning.course_eLearning.repository.CourseRepository;
import course_eLearning.course_eLearning.repository.ModuleRepository;
import course_eLearning.course_eLearning.util.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class CourseELearningApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(CourseELearningApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		commentRepository.deleteAll();
		courseRepository.deleteAll();
		moduleRepository.deleteAll();

		//		--------------Create comments ----------------------------------------
		Comment comment1 = new Comment("1", "details 1", new Date(), "1");
		Comment comment2 = new Comment("1", "details 2", new Date(), "2");
		comment1 = commentRepository.save(comment1);
		comment2 = commentRepository.save(comment2);

		// Create module
		Module module1 = new Module("title 1", true, null, null, true, null);
		Module module2 = new Module( "title 2", true, null, null, true, null);
		Module module3 = new Module("title 3", true, null, null, true, null);
		Module module4 = new Module( "title 4", true, null, null, true, null);
		Module module5 = new Module( "title 5", true, null, null, true, null);
		Module module6 = new Module( "title 6", true, null, null, true, null);
		module1 = moduleRepository.save(module1);
		module2 = moduleRepository.save(module2);

		// Create course
		ArrayList<Module> contents1 = new ArrayList<>();
		contents1.add(module1);
		contents1.add(module2);
		ArrayList<Comment> comments1 = new ArrayList<>();
		comments1.add(comment1);
		Course webCourse1 = new Course( "Web Development 1", "prof1", "description", comments1, Skill.REACTJS, "1", contents1);
		Course webCourse2 = new Course( "Web Development 2", "prof1", "description", comments1, Skill.REACTJS, "1", contents1);
		Course webCourse3 = new Course( "Web Development 3", "prof1", "description", comments1, Skill.REACTJS, "1", contents1);
		Course webCourse4 = new Course( "Web Development 4", "prof1", "description", comments1, Skill.REACTJS, "1", contents1);
		Course webCourse5 = new Course( "Web Development 5", "prof1", "description", comments1, Skill.REACTJS, "1", contents1);
		Course webCourse6 = new Course( "Web Development 6", "prof1", "description", comments1, Skill.REACTJS, "1", contents1);
		courseRepository.saveAll(Arrays.asList(webCourse1, webCourse2, webCourse3, webCourse4, webCourse5, webCourse6));

		Course dataScienceCourse = new Course("Data Science", "prof1", "description", comments1, Skill.PYTHON, "1", contents1);
		Course machineLearningCourse = new Course("Machine Learning", "prof1", "description", comments1, Skill.PYTHON, "1", contents1);
		Course dataAnalysisCourse = new Course("Data Analysis", "prof1", "description", comments1, Skill.PYTHON, "1", contents1);
		courseRepository.saveAll(Arrays.asList(dataScienceCourse, machineLearningCourse, dataAnalysisCourse));

	}
}
