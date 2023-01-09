package course_eLearning.course_eLearning.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import course_eLearning.course_eLearning.constants.KafkaConstants;
import course_eLearning.course_eLearning.model.Comment;
import course_eLearning.course_eLearning.service.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)

public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private KafkaTemplate<String, Comment> kafkaTemplate;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void sendComment(@RequestBody Comment Comment) {
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, Comment).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
