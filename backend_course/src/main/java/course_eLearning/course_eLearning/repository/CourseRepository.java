package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course,String> {

}
