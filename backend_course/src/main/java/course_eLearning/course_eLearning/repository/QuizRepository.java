package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends MongoRepository<Quiz,String> {
}
