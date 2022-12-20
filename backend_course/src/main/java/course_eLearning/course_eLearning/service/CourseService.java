package course_eLearning.course_eLearning.service;


import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.Skill;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface CourseService{
    public abstract Course createCourse(Course course);
    public abstract void updateCourse(String id, Course course);
    public abstract void deleteCourse(String id);
    public abstract List<Course> getCourses();
    public abstract Page<Course> pageableCoursesBySkill(int pageNum, int pageSize, String skill);
    public abstract List<Course> getCoursesBySkill(String skill);
    public abstract Page<Course> getAllCourseByName(int pageNum, int pageSize, String courseName);

}
