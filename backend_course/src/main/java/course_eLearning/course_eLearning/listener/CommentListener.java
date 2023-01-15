package course_eLearning.course_eLearning.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import course_eLearning.course_eLearning.constants.KafkaConstants;
import course_eLearning.course_eLearning.model.Comment;
import course_eLearning.course_eLearning.repository.CommentRepository;

@Component
public class CommentListener {
//    @Autowired
//    private CommentRepository commentRepository;
//
//    @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC, groupId = KafkaConstants.GROUP_ID)
//    public void listen(Comment comment) {
//        System.out.println(comment);
//        comment = commentRepository.save(comment);
//        System.out.println(comment.getId());
//    }
}