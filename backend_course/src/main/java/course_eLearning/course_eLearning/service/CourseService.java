package course_eLearning.course_eLearning.service;


import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.Skill;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CourseService{
    public abstract Course createCourse(Course course);
    public abstract Course createModule(String course_id, Module module);
    public abstract Course updateCourse(String id, Course course);
    public abstract void deleteCourse(String id);
    public abstract List<Course> getCourses();
    public abstract Page<Course> pageableCoursesBySkill(int pageNum, int pageSize, String skill);
    public abstract List<Course> getCoursesBySkill(String skill);
    public abstract Course getCourseById(String courseId);
    public abstract Page<Course> getAllCourseByName(int pageNum, int pageSize, String courseName);
    public abstract CourseProgress enrollCourse(String course_id, String student_id);
    public abstract CourseProgress saveCourse(String course_id, String student_id);
    public abstract Long countProfessorCourses(String professor_id);
    public abstract Long countInProgressCourse(Course course);
}
