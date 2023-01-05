package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,String> {
}
