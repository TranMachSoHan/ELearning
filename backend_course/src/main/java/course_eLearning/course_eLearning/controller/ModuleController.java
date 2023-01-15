package course_eLearning.course_eLearning.controller;

import course_eLearning.course_eLearning.dto.LessonPostDTO;
import course_eLearning.course_eLearning.dto.ModuleOverviewListDTO;
import course_eLearning.course_eLearning.model.Lesson;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.service.ModuleService;
import course_eLearning.course_eLearning.util.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/module")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    /**
     * This url to help create lesson
     * First thing first, you need to create module and embed module id in
     * Then, if you have file upload, please find FileMetaController and upload file, the result will give object FileMeta
     * And finally, the lesson post DTO , there will be image and video need filemeta, you can directly put and send request
     * The sample of lesson post dto is displayed in the lesson post dto, please find it
     *
     * @param lessonPostDTO
     * @param module_id
     * @return
     * @throws IOException
     */
    @PostMapping("id/{moduleId}/createLesson")
    public ResponseEntity createLesson(
            @RequestBody LessonPostDTO lessonPostDTO,
            @PathVariable("moduleId") String module_id
    ) throws IOException {
        switch (lessonPostDTO.getType()){
            case ARTICLE:
                lessonPostDTO.setVideo(null);
                lessonPostDTO.setQuizzes(null);
                break;
            case VIDEO:
                lessonPostDTO.setQuizzes(null);
                lessonPostDTO.setArticle(null);
                break;
            default:
                return new ResponseEntity(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Lesson lesson = ModelMapperConfig.convertDTOToLesson(lessonPostDTO);
        Module module = moduleService.createLesson(module_id, lesson);
        if (module != null){
            ModuleOverviewListDTO dto = ModelMapperConfig.convertToModuleOverviewListDTO(module);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
