package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LessonRepository extends MongoRepository<Lesson, String> {

}
