package course_eLearning.course_eLearning.controller;

import course_eLearning.course_eLearning.dto.CourseProgressDetailDTO;
import course_eLearning.course_eLearning.dto.CourseProgressOverviewListDTO;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.service.CourseProgressService;
import course_eLearning.course_eLearning.service.ModuleService;
import course_eLearning.course_eLearning.util.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courseProgress")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseProgressController {
    @Autowired
    private CourseProgressService progressService;

    @Autowired
    private ModuleService moduleService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseProgress>> getAllCourseProgress(){
        List<CourseProgress> courseProgresses = progressService.getAll();
        return new ResponseEntity<>(courseProgresses, HttpStatus.OK);
    }

    /**
     * http://localhost:8080/courseProgress/student/getInProgress?studentId=studentId1
     * @param student_id
     * @return
     */
    @GetMapping("/student/getInProgress")
    public ResponseEntity<List<CourseProgressOverviewListDTO>> getInProgressCourses(
            @RequestParam("studentId") String student_id
    ){
        List<CourseProgress> courseProgresses = progressService.getInProgress(student_id);
        List<CourseProgressOverviewListDTO> courseProgressDetailDTOS = courseProgresses.stream().map(ModelMapperConfig::convertToCourseProgressOverviewDTO).collect(Collectors.toList());

        return new ResponseEntity<>(courseProgressDetailDTOS,  HttpStatus.OK);
    }

    @GetMapping("/student/getSavedProgress")
    public ResponseEntity<List<CourseProgressOverviewListDTO>> getSavedProgressCourses(
            @RequestParam("studentId") String student_id
    ){
        List<CourseProgress> courseProgresses = progressService.getSavedProgress(student_id);
        List<CourseProgressOverviewListDTO> courseProgressDetailDTOS = courseProgresses.stream().map(ModelMapperConfig::convertToCourseProgressOverviewDTO).collect(Collectors.toList());

        return new ResponseEntity<>(courseProgressDetailDTOS,  HttpStatus.OK);
    }

    @GetMapping("/student/getCompletedProgress")
    public ResponseEntity<List<CourseProgressOverviewListDTO>> getCompletedProgressCourses(
            @RequestParam("studentId") String student_id
    ){
        List<CourseProgress> courseProgresses = progressService.getCompletedProgress(student_id);
        List<CourseProgressOverviewListDTO> courseProgressDetailDTOS = courseProgresses.stream().map(ModelMapperConfig::convertToCourseProgressOverviewDTO).collect(Collectors.toList());

        return new ResponseEntity<>(courseProgressDetailDTOS,  HttpStatus.OK);
    }

    @DeleteMapping("/id/{courseProgressId}/student/dropCourseInProgress")
    public ResponseEntity<List<CourseProgressOverviewListDTO>> dropCourseInProgress(
            @PathVariable("courseProgressId") String course_progress_id,
            @RequestParam("studentId") String student_id
    ){
        progressService.dropCourseProgress(course_progress_id);

        List<CourseProgress> courseProgresses = progressService.getInProgress(student_id);
        List<CourseProgressOverviewListDTO> courseProgressDetailDTOS = courseProgresses.stream().map(ModelMapperConfig::convertToCourseProgressOverviewDTO).collect(Collectors.toList());

        return new ResponseEntity<>(courseProgressDetailDTOS,  HttpStatus.OK);
    }
    @GetMapping("/id/{courseProgressId}/study")
    public ResponseEntity<CourseProgressDetailDTO> getCourse(
            @PathVariable("courseProgressId") String course_progress_id
    ){
        CourseProgress courseProgress = progressService.getById(course_progress_id);
        if(courseProgress != null){
            CourseProgressDetailDTO courseProgressDetailDTO = ModelMapperConfig.convertToCourseProgressDetailDTO(courseProgress);

            return new ResponseEntity<>(courseProgressDetailDTO,  HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{courseProgressId}/nextLesson")
    public ResponseEntity<CourseProgressDetailDTO> nextLesson(
            @PathVariable("courseProgressId") String course_progress_id,
            @RequestParam("moduleID") String currentModule_id,
            @RequestParam("newLessonID") String new_lesson_id
    ){
        Module module = moduleService.getModuleById(currentModule_id);

        if(module != null){
            CourseProgress courseProgress = progressService.setNextLesson(module, course_progress_id, new_lesson_id);
            CourseProgressDetailDTO courseProgressDetailDTO = ModelMapperConfig.convertToCourseProgressDetailDTO(courseProgress);

            return new ResponseEntity<>(courseProgressDetailDTO,  HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{courseProgressId}/markedLessonCompleted")
    public ResponseEntity<CourseProgressDetailDTO> markedLessonCompleted(
            @PathVariable("courseProgressId") String course_progress_id,
            @RequestParam("moduleID") String currentModule_id,
            @RequestParam("completedLessonID") String completed_lesson_id
    ){
        Module module = moduleService.getModuleById(currentModule_id);

        if(module != null){
            CourseProgress courseProgress = progressService.setLessonCompleted(module, course_progress_id, completed_lesson_id);
            CourseProgressDetailDTO courseProgressDetailDTO = ModelMapperConfig.convertToCourseProgressDetailDTO(courseProgress);

            return new ResponseEntity<>(courseProgressDetailDTO,  HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
