package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course,String> , PagingAndSortingRepository<Course, String> {
    Page<Course> findAllBySkill(String skill, Pageable pageable);

}
