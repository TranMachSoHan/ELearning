package course_eLearning.course_eLearning;

import com.github.javafaker.Faker;
import course_eLearning.course_eLearning.model.*;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.helper.LessonType;
import course_eLearning.course_eLearning.repository.*;
import course_eLearning.course_eLearning.util.RestTemplateConfig;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableCaching
public class CourseELearningApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private CourseProgressRepository courseProgressRepository;

	@Autowired
	private LessonRepository lessonRepository;

	@Autowired
	private FileMetaRepository fileMetaRepository;

	private final Faker faker = new Faker();
	public static void main(String[] args) {
		SpringApplication.run(CourseELearningApplication.class, args);
	}

	public void readFile() throws IOException {
		courseRepository.deleteAll();
		moduleRepository.deleteAll();
		commentRepository.deleteAll();
		lessonRepository.deleteAll();
		fileMetaRepository.deleteAll();
		courseProgressRepository.deleteAll();

		List<Course> courses = new ArrayList<>();
		//Creating instance to avoid static member methods
		for (Skill skillEnum : Skill.values()){
			String skill = skillEnum.name();
			InputStream is = getFileAsIOStream("data/udemy_"+skill.toLowerCase()+".csv");
			List<Course> skillCourse = csvToCourses(is, skill);
			courses.addAll(skillCourse);
		}

		System.out.println("Course size: ");
		System.out.println(courses.size());
	}

	public List<Course> csvToCourses(InputStream is , String skill) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			 CSVParser csvParser = new CSVParser(fileReader,
					 CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Course> courses = new ArrayList<Course>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

//			List<String> professors = RestTemplateConfig.getProfessorList();
//			List<String> students = RestTemplateConfig.getStudentList();

			List<String> professors = createProffesorTemps();
			List<String> students = createStudentTemps();

			for (CSVRecord csvRecord : csvRecords) {
				//Create faker comment
				Course course = createCourse(csvRecord, skill, professors, students);
				courses.add(course);
			}

			return courses;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public List<String> createProffesorTemps(){
		List<String> professors = new ArrayList<>();

		for (int i = 0 ; i < 50;i++){
			professors.add("professor_id"+i);
		}
		return professors;
	}

	public List<String> createStudentTemps(){
		List<String> students = new ArrayList<>();

		for (int i = 0 ; i < 50;i++){
			students.add("student_id"+i);
		}
		return students;
	}
	public Course createCourse(CSVRecord csvRecord, String skill, List<String> professors, List<String> students ){
		List<Comment> comments = createComments(students);
		List<Module> modules = createModule();
		Course course =
				new Course(
					csvRecord.get("title"),
					professors.get(new Random().nextInt(professors.size())),
					csvRecord.get("Summary"),
					comments,
					Skill.valueOf(skill),
					new Random().nextDouble(5),
					modules
				);
		course = courseRepository.save(course);
		createCourseProgresses(course, students);
		return course;
	}

	public List<Module> createModule(){
		// create lesson
		List<Module> modules = new ArrayList<>();
		int numOfModuleNeeded = new Random().nextInt(5);
		for (int i =0 ; i< numOfModuleNeeded; i++){
			List<Lesson> lessons = createLessons();
			Module module = new Module(faker.lorem().sentence(), new Random().nextBoolean(), lessons);
			module = moduleRepository.save(module);
			modules.add(module);
		}

		return modules;
	}

	public List<Lesson> createLessons(){
		List<Lesson> lessons = new ArrayList<>();
		int numOfLessonNeeded = new Random().nextInt(8);

		for (int i = 0 ; i < numOfLessonNeeded; i++){
			LessonType lessonType = List.of(LessonType.values()).get(new Random().nextInt(List.of(LessonType.values()).size()));
			FileMeta imageMeta = fileMetaRepository.save(new FileMeta("CryptShield-2.png", "https://elearning-sead-storage.s3.ap-south-1.amazonaws.com/36fd2194-ee0b-4f9d-bb36-28163fb7a947/CryptShield-2.png"));
			FileMeta videoMeta = fileMetaRepository.save(new FileMeta("Ghi Màn hình 2022-11-06 lúc 16.20.29 (online-video-cutter.com).mp4","elearning-sead-storage/1085dfb9-ffc8-4318-99c6-193f158f1203"));
			Lesson lesson = null;
			switch (lessonType){
				case VIDEO -> {
					Lesson.Video video = new Lesson.Video(videoMeta, 40);
					lesson = lessonRepository.save(new Lesson(faker.lorem().sentence(), lessonType, video, null, null));
				}
				default -> {
					// return article
					Lesson.Article article = new Lesson.Article(faker.lorem().paragraph(), imageMeta);
					lesson = lessonRepository.save(new Lesson(faker.lorem().sentence(), lessonType, null, article, null));
				}
			}
			lessons.add(lesson);
		}
		return lessons;
	}

	public Course createCourseProgresses(Course course, List<String> students){
		int numOfCourseProgress = new Random().nextInt(50);
		int i = 0 ;
		List<String> helper = new ArrayList<>();
		while (i != numOfCourseProgress){
			String student = students.get(new Random().nextInt(students.size()));
			if (helper.contains(student)) continue;

			CourseProgress courseProgress = new CourseProgress(course, student, new Random().nextBoolean());
			courseProgress = courseProgressRepository.save(courseProgress);
			course.addCourseProgress(courseProgress);

			helper.add(student);
			i++;
		}
		return course;
	}
	public List<Comment> createComments(List<String> students){
		List<Comment> comments = new ArrayList<>();
		int numOfLessonNeeded = new Random().nextInt(100);

		for (int i = 0 ; i < numOfLessonNeeded ;i ++){
			String studentID = students.get(new Random().nextInt(students.size()));
			Comment comment = new Comment(studentID, faker.lorem().paragraph(), faker.date().past(2, TimeUnit.DAYS),new Random().nextInt(5));
			comment = commentRepository.save(comment);
			System.out.println(comment.toString());
			comments.add(comment);
		}
		return comments;
	}
	private InputStream getFileAsIOStream(final String fileName)
	{
		// The class loader that loaded the class
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);

		// the stream holding the file content
		if (inputStream == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {
			return inputStream;
		}
	}

	@Override
	public void run(String... args) throws IOException {
		readFile();

	}
}
