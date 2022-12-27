package course_eLearning.course_eLearning.controller;

import course_eLearning.course_eLearning.dto.CourseDetailDTO;
import course_eLearning.course_eLearning.dto.CourseListDTO;
import course_eLearning.course_eLearning.dto.CoursePostDTO;
import course_eLearning.course_eLearning.model.Skill;
import course_eLearning.course_eLearning.service.SkillService;
import course_eLearning.course_eLearning.service.kafka.Producer;
import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.service.CourseService;
import course_eLearning.course_eLearning.util.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    SkillService skillService;

    private ModelMapper mapper = new ModelMapper();

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

    @GetMapping("/getAllBySkill")
    public ResponseEntity<List<CourseListDTO>> getCourseBySkill(@RequestParam String skill){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        List<Course> courses = courseService.getCoursesBySkill(skill);
        List<CourseListDTO> courseListDTOS = courses.stream().map(ModelMapperConfig::convertToCourseListDto).collect(Collectors.toList());

        return new ResponseEntity<>(courseListDTOS, HttpStatus.OK);
    }


    @GetMapping("/getAllGroupingBySkill")
    public ResponseEntity<Map<String, List<CourseListDTO>>> getAllGroupingBySkill(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        List<Skill> skills = skillService.getAllSkills();
        Map<String, List<CourseListDTO>> courseGroupedBySkill = new HashMap<>();
        for (Skill skill: skills){
            List<Course> courses = courseService.getCoursesBySkill(skill.name()).stream()
                    .limit(3)
                    .collect(Collectors.toList());
            List<CourseListDTO> courseListDTOS = courses.stream().map(ModelMapperConfig::convertToCourseListDto).collect(Collectors.toList());
            courseGroupedBySkill.put(skill.toString(),courseListDTOS );
        }

        return new ResponseEntity<>(courseGroupedBySkill, headers, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseListDTO>> getAllCourses(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        List<Course> courses = courseService.getCourses();
        List<CourseListDTO> courseListDTOS = courses.stream().map(ModelMapperConfig::convertToCourseListDto).collect(Collectors.toList());

        return new ResponseEntity<>(courseListDTOS, headers, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody CoursePostDTO coursePostDTO){
        Course course = mapper.map(coursePostDTO, Course.class);
        course.setSkill(Skill.valueOf(coursePostDTO.getSkill().toUpperCase()));
        course.setCourseID(null);
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/updateCourse")
    public ResponseEntity<Course> updateCourse(@RequestBody Course course){
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @GetMapping("/id/{courseId}")
    public ResponseEntity<CourseDetailDTO> getCourseById(@PathVariable("courseId") String course_id){
        Course course = courseService.getCourseById(course_id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        if(course == null){
            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
        }
        else{
            CourseDetailDTO courseDetailDTO = ModelMapperConfig.convertToCourseDetailDTO(course);
            return new ResponseEntity<>(courseDetailDTO, headers, HttpStatus.OK);
        }
    }

    @PostMapping("/id/{courseId}/enroll")
    public ResponseEntity<Course> enrollCourse(
            @PathVariable("courseId") String course_id,
            @RequestParam("studentId") String student_id
    ){
        Course course = courseService.enrollCourse(course_id, student_id);
        if (course == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);

    }
}
