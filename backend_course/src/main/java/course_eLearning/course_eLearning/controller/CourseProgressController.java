package course_eLearning.course_eLearning.controller;

import course_eLearning.course_eLearning.dto.CourseDetailDTO;
import course_eLearning.course_eLearning.dto.CourseProgressDetailDTO;
import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.service.CourseProgressService;
import course_eLearning.course_eLearning.util.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseProgress")
@CrossOrigin(origins = "http://example.com", maxAge = 3600)
public class CourseProgressController {
    @Autowired
    private CourseProgressService progressService;
    private ModelMapper mapper = new ModelMapper();

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

    @GetMapping("/study/id/{courseProgressId}")
    public ResponseEntity<CourseProgressDetailDTO> getCourse(
            @PathVariable("courseProgressId") String course_progress_id
    ){
        CourseProgress courseProgress = progressService.getById(course_progress_id);
        CourseProgressDetailDTO courseProgressDetailDTO = mapper.map(courseProgress, CourseProgressDetailDTO.class);
        if(courseProgress == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(courseProgressDetailDTO,  HttpStatus.OK);
        }
    }
}
