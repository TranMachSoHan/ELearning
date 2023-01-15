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

	@Override
	public void run(String... args) throws IOException {
		 readFile();

//	 	try{
//
//	 		List<String> professors = RestTemplateConfig.getProfessorList();
//	 		System.out.println(professors.size());
//	 		String professor_id1 = professors.get(0);
//	 		String professor_id2 = professors.get(1);
//	 		String professor_id3 = professors.get(2);
//	 		String professor_id4 = professors.get(3);
//	 		String professor_id5 = professors.get(4);
//	 		List<String> students = RestTemplateConfig.getStudentList();
//	 		String student_id1 = students.get(0);
//
//	 		courseRepository.deleteAll();
//	 		moduleRepository.deleteAll();
//	 		commentRepository.deleteAll();
//	 		courseProgressRepository.deleteAll();
//
//	 		//		--------------Create comments ----------------------------------------
//	 		Comment comment1_1 = new Comment("1", "details 1", new Date(), 1);
//	 		Comment comment5_1 = new Comment("1", "details 2", new Date(), 2);
//	 		Comment comment5_2 = new Comment("1", "details 3", new Date(), 5);
//	 		Comment comment7_1 = new Comment("1", "details 3", new Date(), 5);
//	 		Comment comment7_2 = new Comment("1", "details 3", new Date(), 5);
//	 		Comment comment7_3 = new Comment("1", "details 3", new Date(), 5);
//	 		comment1_1 = commentRepository.save(comment1_1);
//	 		comment5_1 = commentRepository.save(comment5_1);
//	 		comment5_2 = commentRepository.save(comment5_2);
//	 		comment7_1 = commentRepository.save(comment7_1);
//	 		comment7_2 = commentRepository.save(comment7_2);
//	 		comment7_3 = commentRepository.save(comment7_3);
//
//	 		FileMeta imageMeta1 = fileMetaRepository.save(new FileMeta("CryptShield-2.png", "https://elearning-sead-storage.s3.ap-south-1.amazonaws.com/36fd2194-ee0b-4f9d-bb36-28163fb7a947/CryptShield-2.png"));
//	 		FileMeta imageMeta2 = fileMetaRepository.save(new FileMeta("1672719510415-308958501_810385533538235_264186827225329196_n.jpg", "elearning-sead-storage/4e61e91a-e4be-463f-9df6-19aae4102d6c"));
//	 		FileMeta videoMeta1  = fileMetaRepository.save(new FileMeta("Discord Game Session.mp4","https://elearning-sead-storage.s3.ap-south-1.amazonaws.com/5529afd8-f388-4722-ba65-7148408ea237/Discord%20Game%20Session.mp4"));
//	 		FileMeta videoMeta2 = fileMetaRepository.save(new FileMeta("Discord Game Session.mp4","elearning-sead-storage/c4afa5a8-07eb-435a-bdd4-eab5e68371fe"));
//	 		FileMeta videoMeta3 = fileMetaRepository.save(new FileMeta("Ghi Màn hình 2022-11-06 lúc 16.20.29 (online-video-cutter.com).mp4","elearning-sead-storage/1085dfb9-ffc8-4318-99c6-193f158f1203"));
//
//	 		//		//Tao video
//	 		Lesson.Video video1_1_1 = new Lesson.Video(videoMeta1, 3600);
//	 		Lesson.Video video1_1_3 =new Lesson.Video(videoMeta2, 4500);
//	 		Lesson.Video video1_2_1 = new Lesson.Video(videoMeta3, 3600);
//	 		//		Lesson.Video video1_2_3 = new Lesson.Video(new FileMeta("file name", "file path"), 4500);
//	 		//		Lesson.Video video2_1_1 = new Lesson.Video(new FileMeta("file name", "file path"), 3600);
//	 		//		Lesson.Video video2_1_3 = new Lesson.Video(new FileMeta("file name", "file path"), 4500);
//
//
//	 		// will be removed
//
//	 		//Tao video
//	 		Lesson.Video video1_2_3 = null;
//	 		Lesson.Video video2_1_1 = null;
//	 		Lesson.Video video2_1_3 = null;
//
//
//	 		// Tao article
//	 		Lesson.Article article1 = new Lesson.Article("lorem ipsum",imageMeta1);
//	 		Lesson.Article article2 = new Lesson.Article("lorem ipsum",null);
//
//
//	 		// Tao lesson
//	 		Lesson lesson1_1_1 = lessonRepository.save(new Lesson("Intro", LessonType.VIDEO, video1_1_1, null, null));
//	 		Lesson lesson1_1_2 = lessonRepository.save(new Lesson("Reading", LessonType.ARTICLE, null, article1, null));
//	 		Lesson lesson1_1_3 = lessonRepository.save(new Lesson("Practice 1", LessonType.VIDEO, video1_1_3, null, null));
//	 		Lesson lesson1_1_4 = lessonRepository.save(new Lesson("Quiz", LessonType.QUIZ, null, null, null));
//	 		Lesson lesson1_2_1 = lessonRepository.save(new Lesson("Intro", LessonType.VIDEO, video1_2_1, null, null));
//	 		Lesson lesson1_2_2 = lessonRepository.save(new Lesson("Reading", LessonType.ARTICLE, null, article2, null));
//	 		Lesson lesson1_2_3 = lessonRepository.save(new Lesson("Practice 1", LessonType.VIDEO, video1_2_3, null, null));
//	 		Lesson lesson1_2_4 = lessonRepository.save(new Lesson("Quiz", LessonType.QUIZ, null, null, null));
//	 		Lesson lesson2_1_1 = lessonRepository.save(new Lesson("Intro", LessonType.VIDEO, video2_1_1, null, null));
//	 		Lesson lesson2_1_2 = lessonRepository.save(new Lesson("Reading", LessonType.ARTICLE, null, article1, null));
//	 		Lesson lesson2_1_3 = lessonRepository.save(new Lesson("Practice 1", LessonType.VIDEO, video2_1_3, null, null));
//	 		Lesson lesson2_1_4 = lessonRepository.save(new Lesson("Quiz", LessonType.QUIZ, null, null, null));
//	 		Lesson lesson2_2_1 = lessonRepository.save(new Lesson("Intro", LessonType.VIDEO, null, null, null));
//	 		Lesson lesson2_2_2 = lessonRepository.save(new Lesson("Reading", LessonType.ARTICLE, null, article2, null));
//	 		Lesson lesson2_2_3 = lessonRepository.save(new Lesson("Practice 1", LessonType.VIDEO, null, null, null));
//	 		Lesson lesson2_2_4 = lessonRepository.save(new Lesson("Quiz", LessonType.QUIZ, null, null, null));
//
//	 		// Create module
//	 		Module module1_1 = moduleRepository.save(new Module("title 1", true, Arrays.asList(lesson1_1_1, lesson1_1_2, lesson1_1_3, lesson1_1_4) ));
//	 		Module module1_2 = moduleRepository.save(new Module( "title 2", true, Arrays.asList(lesson1_2_1, lesson1_2_2, lesson1_2_3, lesson1_2_4)));
//	 		Module module2_1 = moduleRepository.save(new Module("title 3", true, Arrays.asList(lesson2_1_1, lesson2_1_2, lesson2_1_3, lesson2_1_4)));
//	 		Module module2_2 = moduleRepository.save(new Module( "title 4", true, Arrays.asList(lesson2_2_1, lesson2_2_2, lesson2_2_3, lesson2_2_4)));
//	 		Module module2_3 = moduleRepository.save(new Module( "title 5", false, null));
//	 		Module module4_1 = moduleRepository.save(new Module( "title 6", true, null));
//	 		Module module4_2 = moduleRepository.save(new Module( "title 7", false, null));
//	 		Module module4_3 = moduleRepository.save(new Module( "title 8", false, null));
//	 		Module module4_4 = moduleRepository.save(new Module( "title 9", true, null));
//	 		Module module5_1 = moduleRepository.save(new Module( "title 9", true, null));
//	 		Module module5_2 = moduleRepository.save(new Module( "title 10", true, null));
//	 		Module module5_3 = moduleRepository.save(new Module( "title 11", false, null));
//	 		Module module5_4 = moduleRepository.save(new Module( "title 12", true, null));
//	 		Module module5_5 = moduleRepository.save(new Module( "title 13", true, null));
//	 		Module module6_1 = moduleRepository.save(new Module( "title 14", false, null));
//	 		Module module6_2 = moduleRepository.save(new Module( "title 15", true, null));
//	 		Module module6_3 = moduleRepository.save(new Module( "title 16", true, null));
//	 		Module module7_1 = moduleRepository.save(new Module( "title 17", false, null));
//	 		Module module7_2 = moduleRepository.save(new Module( "title 18", true, null));
//	 		Module module8_1 = moduleRepository.save(new Module( "title 19", true, null));
//	 		Module module9_1 =  moduleRepository.save(new Module( "title 20", true, null));
//	 		Module module9_2 =  moduleRepository.save(new Module( "title 21", true, null));
//	 		Module module10_1 = moduleRepository.save(new Module( "title 22", true, null));
//	 		Module module10_2 = moduleRepository.save(new Module( "title 23", true, null));
//	 		Module module12_1 = moduleRepository.save(new Module( "title 24", true, null));
//	 		Module module12_2 = moduleRepository.save(new Module( "title 25", true, null));
//	 		Module module13_1 = moduleRepository.save(new Module( "title 26", true, null));
//	 		Module module13_2 = moduleRepository.save(new Module( "title 27", true,  null));
//	 		Module module15_1 = moduleRepository.save(new Module( "title 28", true, null));
//	 		Module module15_2 = moduleRepository.save(new Module( "title 29", true, null));
//	 		Module module16_1 = moduleRepository.save(new Module( "title 30", true,  null));
//	 		Module module16_2 = moduleRepository.save(new Module( "title 31", true,  null));
//	 		Module module17_1 = moduleRepository.save(new Module( "title 32", true, null));
//	 		Module module17_2 = moduleRepository.save(new Module( "title 33", true, null));
//	 		Module module20_1 = moduleRepository.save(new Module( "title 34", true, null));
//	 		Module module21_1 = moduleRepository.save(new Module( "title 35", true,  null));
//	 		Module module21_2 = moduleRepository.save(new Module( "title 36", true, null));
//	 		Module module22_1 = moduleRepository.save(new Module( "title 37", true, null));
//	 		Module module22_2 = moduleRepository.save(new Module( "title 38", true, null));
//	 		Module module23_1 = moduleRepository.save(new Module( "title 39", true,  null));
//	 		Module module23_2 = moduleRepository.save(new Module( "title 40", true, null));
//
//	 		// REACTJS
//	 		ArrayList<Module> contents1 = new ArrayList<>(Arrays.asList(module1_1,module1_2));
//	 		ArrayList<Comment> comments1 = new ArrayList<>();
//	 		comments1.add(comment1_1);
//	 		Course course1 = courseRepository.save(new Course( "Web Development 1", professor_id1, "description", comments1, Skill.REACTJS, 1, contents1));
//
//	 		ArrayList<Module> contents2 = new ArrayList<>(Arrays.asList(module2_1,module2_2,module2_3));
//	 		ArrayList<Comment> comments2 = new ArrayList<>();
//	 		Course course2 = courseRepository.save(new Course( "Web Development 2", professor_id2, "description", comments2, Skill.REACTJS, 2, contents2));
//
//	 		ArrayList<Module> contents3 = new ArrayList<>();
//	 		ArrayList<Comment> comments3 = new ArrayList<>();
//	 		Course course3 = courseRepository.save(new Course( "Web Development 3", professor_id3, "description", comments3, Skill.REACTJS, 2, contents3));
//
//	 		ArrayList<Module> contents4 = new ArrayList<>(Arrays.asList(module4_1, module4_2, module4_3, module4_4));
//	 		ArrayList<Comment> comments4= new ArrayList<>();
//	 		Course course4 = courseRepository.save(new Course( "Web Development 4", professor_id4, "description",comments4, Skill.REACTJS, 2, contents4));
//
//	 		ArrayList<Module> contents5 = new ArrayList<>(Arrays.asList(module5_1,module5_2,module5_3,module5_4,module5_5 ));
//	 		ArrayList<Comment> comments5= new ArrayList<>(Arrays.asList(comment5_1,comment5_2));
//	 		Course course5 = courseRepository.save(new Course( "Web Development 5", professor_id5, "description", comments5, Skill.REACTJS, 2, contents5));
//
//	 		ArrayList<Module> contents6 = new ArrayList<>(Arrays.asList(module6_1, module6_2, module6_3));
//	 		ArrayList<Comment> comments6 = new ArrayList<>();
//	 		Course course6 = courseRepository.save(new Course( "Web Development 6", professor_id1, "description", comments6, Skill.REACTJS, 2, contents6));
//
//	 		// PYTHON
//	 		ArrayList<Module> contents7 = new ArrayList<>(Arrays.asList(module7_1, module7_2));
//	 		ArrayList<Comment> comments7 = new ArrayList<>(Arrays.asList(comment7_1, comment7_2, comment7_3));
//	 		Course course7 = courseRepository.save(new Course("Data Science", professor_id2, "description", comments7, Skill.PYTHON, 2, contents7));
//
//	 		ArrayList<Module> contents8 = new ArrayList<>(Collections.singletonList(module8_1));
//	 		ArrayList<Comment> comments8 = new ArrayList<>();
//	 		Course course8 = courseRepository.save(new Course("Machine Learning", professor_id3, "description", comments8, Skill.PYTHON, 3, contents8));
//
//	 		ArrayList<Module> contents9 = new ArrayList<>(Arrays.asList(module9_1, module9_2));
//	 		ArrayList<Comment> comments9 = new ArrayList<>();
//	 		Course course9 = courseRepository.save(new Course("Data Analysis", professor_id4, "description", comments9, Skill.PYTHON, 4, contents9));
//
//	 		ArrayList<Module> contents10 = new ArrayList<>(Arrays.asList(module10_1,module10_2));
//	 		ArrayList<Comment> comments10 = new ArrayList<>();
//	 		Course course10 = courseRepository.save(new Course("2022 Complete Python Bootcamp From Zero to Hero in Python", professor_id2, "description", comments7, Skill.PYTHON, 1, contents10));
//
//	 		ArrayList<Module> contents11 = new ArrayList<>();
//	 		ArrayList<Comment> comments11 = new ArrayList<>();
//	 		Course course11 = courseRepository.save(new Course("Web Developer Bootcamp with Flask and Python in 2022", professor_id3, "description", comments11, Skill.PYTHON, 1, contents11));
//
//	 		// C language
//	 		ArrayList<Module> contents12 = new ArrayList<>(Arrays.asList(module12_1, module12_2));
//	 		ArrayList<Comment> comments12 = new ArrayList<>();
//	 		Course course12 = courseRepository.save(new Course("C Programming For Beginners - Master the C Language", professor_id4, "description", comments12, Skill.C, 2, contents12));
//
//	 		ArrayList<Module> contents13 = new ArrayList<>(Arrays.asList(module13_1, module13_2));
//	 		ArrayList<Comment> comments13 = new ArrayList<>();
//	 		Course course13 = courseRepository.save(new Course("Microcontroller Embedded C Programming: Absolute Beginners", professor_id5, "description", comments13, Skill.C, 4, contents13));
//
//	 		ArrayList<Module> contents14 = new ArrayList<>();
//	 		ArrayList<Comment> comments14 = new ArrayList<>();
//	 		Course course14 = courseRepository.save(new Course("Mastering Data Structures & Algorithms using C", professor_id1, "description", comments14, Skill.C, 5, contents14));
//
//	 		ArrayList<Module> contents15 = new ArrayList<>(Arrays.asList(module15_1, module15_2));
//	 		ArrayList<Comment> comments15 = new ArrayList<>();
//	 		Course course15 = courseRepository.save(new Course("Embedded Systems Programming on ARM Cortex-M3/M4 Processor", professor_id2, "description", comments15, Skill.C, 1, contents15));
//
//	 		ArrayList<Module> contents16 = new ArrayList<>(Arrays.asList(module16_1, module16_2));
//	 		ArrayList<Comment> comments16 = new ArrayList<>();
//	 		Course course16 = courseRepository.save(new Course("Embedded Systems Programming on ARM Cortex-M3/M4 Processor", professor_id3, "description", comments16, Skill.C, 1, contents16));
//
//	 		// NODEJS
//	 		ArrayList<Module> contents17 = new ArrayList<>(Arrays.asList(module17_1, module17_2));
//	 		ArrayList<Comment> comments17 = new ArrayList<>();
//	 		Course course17 = courseRepository.save(new Course("NodeJS Tutorial and Projects Course", professor_id4, "description", comments17, Skill.NODEJS, 3, contents17));
//
//	 		ArrayList<Module> contents18 = new ArrayList<>();
//	 		ArrayList<Comment> comments18 = new ArrayList<>();
//	 		Course course18 = courseRepository.save(new Course("Node.js, Express, MongoDB & More: The Complete Bootcamp 2023", professor_id5, "description", comments18, Skill.NODEJS, 3, contents18));
//
//	 		ArrayList<Module> contents19 = new ArrayList<>();
//	 		ArrayList<Comment> comments19 = new ArrayList<>();
//	 		Course course19 = courseRepository.save(new Course("Node.js, Express, MongoDB & More: The Complete Bootcamp 2023", professor_id1, "description", comments19, Skill.NODEJS, 2, contents19));
//
//	 		ArrayList<Module> contents20 = new ArrayList<>(Collections.singletonList(module20_1));
//	 		ArrayList<Comment> comments20 = new ArrayList<>();
//	 		Course course20 = courseRepository.save(new Course("Node.js, Express, MongoDB & More: The Complete Bootcamp 2023", professor_id2, "description", comments20, Skill.NODEJS, 1, contents20));
//
//	 		ArrayList<Module> content21 = new ArrayList<>(Arrays.asList(module21_1, module21_2));
//	 		ArrayList<Comment> comments21 = new ArrayList<>();
//	 		Course course21 = courseRepository.save(new Course("Node.js, Express, MongoDB & More: The Complete Bootcamp 2023", professor_id3, "description", comments21, Skill.NODEJS, 2, content21));
//
//	 		// JAVA course
//	 		ArrayList<Module> content22 = new ArrayList<>(Arrays.asList(module22_1, module22_2));
//	 		ArrayList<Comment> comments22 = new ArrayList<>();
//	 		Course course22 = courseRepository.save(new Course("Android Application", professor_id4, "description", comments22, Skill.JAVA, 4, content22));
//
//	 		ArrayList<Module> content23 = new ArrayList<>(Arrays.asList(module23_1, module23_2));
//	 		ArrayList<Comment> comments23 = new ArrayList<>();
//	 		Course course23 = courseRepository.save(new Course("Spring boot for Web Application", professor_id5, "description", comments23, Skill.JAVA, 4, content23));
//
//	 		// Join courses
//	 		// Save course progress
//	 		CourseProgress courseProgress1 = courseProgressRepository.save(new CourseProgress(course1, student_id1, true));
//	 		course1.addCourseProgress(courseProgress1);
//	 		courseRepository.save(course1);
//
//	 		CourseProgress courseProgress2 = courseProgressRepository.save(new CourseProgress(course3, student_id1, true));
//	 		course3.addCourseProgress(courseProgress2);
//	 		courseRepository.save(course3);
//
//	 		CourseProgress courseProgress3 = courseProgressRepository.save(new CourseProgress(course7, student_id1, true));
//	 		course7.addCourseProgress(courseProgress3);
//	 		courseRepository.save(course7);
//
//	 		CourseProgress courseProgress4 = courseProgressRepository.save(new CourseProgress(course15, student_id1, false));
//	 		course15.addCourseProgress(courseProgress4);
//	 		courseRepository.save(course15);
//
//	 		CourseProgress courseProgress5 = courseProgressRepository.save(new CourseProgress(course23, student_id1, false));
//	 		course23.addCourseProgress(courseProgress5);
//	 		courseRepository.save(course23);
//	 	}
//	 	catch (NullPointerException exception){
//	 		System.out.println("Cannot get list of professor or students");
//	 	}
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
			if (skillEnum == Skill.DESIGN) break;
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

			List<String> professors = RestTemplateConfig.getProfessorList();
			List<String> students = RestTemplateConfig.getStudentList();

//			List<String> professors = createProffesorTemps();
//			List<String> students = createStudentTemps();

			for (CSVRecord csvRecord : csvRecords) {
				//Create faker comment
				Course course = createCourse(csvRecord, skill, professors, students);
				System.out.println("Skill: "+ skill );
				System.out.println(course.getCourseName());
				courses.add(course);
				if (courses.size() > 10) break;
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
			course.addCourseProgress(courseProgress.getCourseProgressID());

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


}
