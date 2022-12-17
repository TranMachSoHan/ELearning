package course_eLearning.course_eLearning.controller;

import course_eLearning.course_eLearning.dto.CourseListDTO;
import course_eLearning.course_eLearning.model.Skill;
import course_eLearning.course_eLearning.service.kafka.Producer;
import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.service.CourseService;
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
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    Producer producer;

    @GetMapping("/pageableBySkill")
    public ResponseEntity<Map<String, Object>> pageableCourseBySkill(
            @RequestParam int pageNum,
            @RequestParam int pageSize,
            @RequestParam String skill){
        Page<Course> pageCourses =  courseService.pageableCoursesBySkill(pageNum, pageSize, skill) ;
        List<Course> courses = pageCourses.getContent();
        List<CourseListDTO> courseListDTOS = courses.stream().map(CourseListDTO::convertToCourseListDto).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courseListDTOS);
        response.put("currentPage", pageCourses.getNumber());
        response.put("totalItems", pageCourses.getTotalElements());
        response.put("totalPages", pageCourses.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllBySkill")
    public ResponseEntity<List<CourseListDTO>> getCourseBySkill(@RequestParam String skill){
        List<Course> courses = courseService.getCoursesBySkill(skill);
        List<CourseListDTO> courseListDTOS = courses.stream().map(CourseListDTO::convertToCourseListDto).collect(Collectors.toList());

        return new ResponseEntity<>(courseListDTOS, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<CourseListDTO>> getAllCourses(){
        List<Course> courses = courseService.getCourses();
        List<CourseListDTO> courseListDTOS = courses.stream().map(CourseListDTO::convertToCourseListDto).collect(Collectors.toList());

        return ResponseEntity.ok(courseListDTOS);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        producer.sendMessage("create course");
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @GetMapping("/id")
    public ResponseEntity<Course> getCourseById(@RequestParam String course_id){
        return ResponseEntity.ok(null);
    }
}
