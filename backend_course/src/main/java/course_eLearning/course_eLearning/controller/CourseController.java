package course_eLearning.course_eLearning.controller;

import course_eLearning.course_eLearning.dto.*;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.Skill;
import course_eLearning.course_eLearning.service.SkillService;
import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.service.CourseService;
import course_eLearning.course_eLearning.util.ModelMapperConfig;
import course_eLearning.course_eLearning.util.RestTemplateConfig;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SkillService skillService;

    @GetMapping("/getAllSkills")
    public ResponseEntity<Skill[]> getAllSkills(){
        return new ResponseEntity<>(Skill.values(), HttpStatus.OK);
    }
    
    @GetMapping("/pageableBySkill")
    public ResponseEntity<Map<String, Object>> pageableCourseBySkill(
            @RequestParam int pageNum,
            @RequestParam int pageSize,
            @RequestParam String skill){
        Page<Course> pageCourses =  courseService.pageableCoursesBySkill(pageNum, pageSize, skill) ;
        List<Course> courses = pageCourses.getContent();
        List<CourseListDTO> courseListDTOS = courses.stream().map(ModelMapperConfig::convertToCourseListDto).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courseListDTOS);
        response.put("currentPage", pageCourses.getNumber());
        response.put("totalItems", pageCourses.getTotalElements());
        response.put("totalPages", pageCourses.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getCourseSetting")
    public ResponseEntity<CourseSettingDTO> getCourseSetting(
            @PathVariable("courseId") String course_id,
            @RequestParam("professorId") String professor_id
    ){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/getAllBySkill")
    public ResponseEntity<List<CourseListDTO>> getCourseBySkill(@RequestParam String skill){

        List<Course> courses = courseService.getCoursesBySkill(skill);
        List<CourseListDTO> courseListDTOS = courses.stream().map(ModelMapperConfig::convertToCourseListDto).collect(Collectors.toList());

        return new ResponseEntity<>(courseListDTOS, HttpStatus.OK);
    }


    @GetMapping("/getAllGroupingBySkill")
    public ResponseEntity<Map<String, List<CourseListDTO>>> getAllGroupingBySkill(){

        List<Skill> skills = skillService.getAllSkills();
        Map<String, List<CourseListDTO>> courseGroupedBySkill = new HashMap<>();
        for (Skill skill: skills){
            List<Course> courses = courseService.getCoursesBySkill(skill.name()).stream()
                    .limit(3)
                    .collect(Collectors.toList());
            List<CourseListDTO> courseListDTOS = courses.stream().map(ModelMapperConfig::convertToCourseListDto).collect(Collectors.toList());
            courseGroupedBySkill.put(skill.toString(),courseListDTOS );
        }

        return new ResponseEntity<>(courseGroupedBySkill, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseListDTO>> getAllCourses(){

        List<Course> courses = courseService.getCourses();
        List<CourseListDTO> courseListDTOS = courses.stream().map(ModelMapperConfig::convertToCourseListDto).collect(Collectors.toList());

        return new ResponseEntity<>(courseListDTOS, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody CoursePostDTO coursePostDTO){
        Course course = ModelMapperConfig.convertDTOtoCourse(coursePostDTO);
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PostMapping("/id/{courseId}/createModule")
    public ResponseEntity<CourseSettingDTO> createModule(
            @RequestBody ModulePostDTO modulePostDTO,
            @PathVariable("courseId") String course_id
    ){
        Module module = ModelMapperConfig.convertDTOToModule(modulePostDTO);
        Course course = courseService.createModule(course_id, module);
        if (course != null){
            CourseSettingDTO courseSettingDTO = ModelMapperConfig.convertCourseToCourseSettingDTO(course);
            return new ResponseEntity<>(courseSettingDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PutMapping("/updateCourse")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @GetMapping("/overview/id/{courseId}")
    public ResponseEntity<CourseDetailDTO> getCourseOverviewById(@PathVariable("courseId") String course_id){
        Course course = courseService.getCourseById(course_id);
        if(course == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            Long numberCoursesOfProf = courseService.countProfessorCourses(course.getProfessorID());
            Long numberOfStudent = courseService.countInProgressCourse(course);
            CourseDetailDTO courseDetailDTO = ModelMapperConfig.convertToCourseDetailDTO(course,numberCoursesOfProf,numberOfStudent);
            return new ResponseEntity<>(courseDetailDTO,  HttpStatus.OK);
        }
    }

    @GetMapping("/id/{courseId}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("courseId") String course_id){
        Course course = courseService.getCourseById(course_id);
        if(course == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            CourseDTO courseDTO = ModelMapperConfig.convertToCourseDTO(course);
            return new ResponseEntity<>(courseDTO,  HttpStatus.OK);
        }
    }


    /**
     * http://localhost:8080/course/id/63b5151a7e7b23000f83a709/enroll?studentId=studentId
     * this will handle the save progress to enroll course
     * this will handle the course already enrolled and the student click on enroll
     * @param course_id
     * @param student_id
     * @return
     */
    @PostMapping("/id/{courseId}/enroll")
    public ResponseEntity<CourseProgressDetailDTO> enrollCourse(
            @PathVariable("courseId") String course_id,
            @RequestParam("studentId") String student_id
    ){
        CourseProgress progress = courseService.enrollCourse(course_id, student_id);
        if (progress == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        CourseProgressDetailDTO progressDTO = ModelMapperConfig.convertToCourseProgressDetailDTO(progress);
        return new ResponseEntity<>(progressDTO, HttpStatus.OK);
    }

    @PostMapping("/id/{courseId}/save")
    public ResponseEntity<CourseProgressDetailDTO> savedCourse(
            @PathVariable("courseId") String course_id,
            @RequestParam("studentId") String student_id
    ){
        CourseProgress progress = courseService.saveCourse(course_id, student_id);
        if (progress == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        CourseProgressDetailDTO progressDTO = ModelMapperConfig.convertToCourseProgressDetailDTO(progress);
        return new ResponseEntity<>(progressDTO, HttpStatus.OK);
    }
}
