package course_eLearning.course_eLearning.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import course_eLearning.course_eLearning.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import course_eLearning.course_eLearning.constants.KafkaConstants;
import course_eLearning.course_eLearning.model.Comment;
import course_eLearning.course_eLearning.repository.CommentRepository;
import course_eLearning.course_eLearning.service.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*", maxAge = 3600)

public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private KafkaTemplate<String, CommentDTO> kafkaTemplate;

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public void sendComment(@RequestBody CommentDTO commentDTO) {
        try {
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, commentDTO).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComment(){

        List<Comment> comments = commentRepository.findAll();

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
