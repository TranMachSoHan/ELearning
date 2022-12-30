package course_eLearning.course_eLearning.controller;

import course_eLearning.course_eLearning.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/module")
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class ModuleController {
    @Autowired
    ModuleService moduleService;

//    @GetMapping("/getModuleBy")
//    public ResponseEntity<Map<String, Object>> pageableCourseBySkill(){
//
//    }
}
