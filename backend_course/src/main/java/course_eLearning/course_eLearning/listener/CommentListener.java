package course_eLearning.course_eLearning.listener;

import course_eLearning.course_eLearning.dto.CommentDTO;
import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import course_eLearning.course_eLearning.constants.KafkaConstants;
import course_eLearning.course_eLearning.model.Comment;
import course_eLearning.course_eLearning.repository.CommentRepository;

@Component
public class CommentListener {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void listen(CommentDTO commentDTO) {
        Course course = courseRepository.findById(commentDTO.getCourse_id()).orElse(null);
        if (course == null) {
            System.out.println("Course is not found");
            return;
        }
        System.out.println(commentDTO.toString());
        Comment comment = commentRepository.save(commentDTO.getComment());
        course.addComment(comment);
        courseRepository.save(course);
        System.out.println(comment.getId());
    }
}