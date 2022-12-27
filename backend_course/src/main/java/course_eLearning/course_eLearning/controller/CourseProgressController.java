package course_eLearning.course_eLearning.controller;

import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.service.CourseProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseProgress")
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
public class CourseProgressController {
    @Autowired
    private CourseProgressService progressService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseProgress>> getAllCourseProgress(){
        List<CourseProgress> courseProgresses = progressService.getAll();
        return new ResponseEntity<>(courseProgresses, HttpStatus.OK);
    }
    @GetMapping("/student/getInProgress")
    public ResponseEntity<List<CourseProgress>> getInProgressCourses(@RequestParam("studentId") String student_id){
        List<CourseProgress> courseProgresses = progressService.getInProgress(student_id);
        return new ResponseEntity<>(courseProgresses,  HttpStatus.OK);
    }
}
