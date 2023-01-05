package course_eLearning.course_eLearning.controller;


import course_eLearning.course_eLearning.dto.LessonDetailDTO;
import course_eLearning.course_eLearning.model.Lesson;
import course_eLearning.course_eLearning.service.LessonService;
import course_eLearning.course_eLearning.util.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class LessonController {
    @Autowired
    private LessonService lessonService;


    @GetMapping("/id/{lessonId}/material")
    public ResponseEntity<LessonDetailDTO> getLessonArticle(@PathVariable("lessonId") String lesson_id){

        Pair<Lesson, Object> lessonMaterial = lessonService.getLessonMaterialById(lesson_id);
        if (lessonMaterial != null){
            LessonDetailDTO detailDTO = ModelMapperConfig.convertToLessonDetailDTO(lessonMaterial.getFirst(), lessonMaterial.getSecond());
            return new ResponseEntity<>(detailDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
