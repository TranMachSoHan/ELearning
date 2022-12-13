package course_eLearning.course_eLearning.service;


import course_eLearning.course_eLearning.model.Course;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface CourseService{
    public abstract Course createCourse(Course course);
    public abstract void updateCourse(String id, Course course);
    public abstract void deleteCourse(String id);
    public abstract Collection<Course> getCourses();
    public abstract Page<Course> getAllCourseBySkill(int pageNum, int pageSize, String skill);
}
