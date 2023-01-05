package course_eLearning.course_eLearning.controller;

import course_eLearning.course_eLearning.model.FileMeta;
import course_eLearning.course_eLearning.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/fileMeta")
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class FileMetaController {

    @Autowired
    private MetaService metaService;

    // handle all uploaded file , including image and video
    @PostMapping("/uploadFile")
    public ResponseEntity<FileMeta> uploadFile(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        FileMeta fileMeta = metaService.upload(file);
        return new ResponseEntity<>(fileMeta, HttpStatus.OK);
    }





}
