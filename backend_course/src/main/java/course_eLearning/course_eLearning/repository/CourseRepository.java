package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String>, PagingAndSortingRepository<Course, String> {

    Page<Course> findAllBySkill(String skill, Pageable pageable);
    List<Course> findBySkill(String skill);
    Page<Course> findByCourseName(String courseName, Pageable pageable);

//    https://stackoverflow.com/questions/50714135/spring-mongo-paging-with-sorting-with-mongorepository
}
