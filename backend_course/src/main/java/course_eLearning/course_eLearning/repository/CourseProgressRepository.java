package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.CourseProgress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseProgressRepository extends MongoRepository<CourseProgress, String> {
    public List<CourseProgress> findAllByStudent(String student);
}
