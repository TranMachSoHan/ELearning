package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.repository.CourseRepository;
import course_eLearning.course_eLearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseSereviceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        System.out.println(course.getCourseName());
        Course newCourse = courseRepository.save(course);
        return newCourse;
    }

    @Override
    public Course updateCourse(String id, Course updatedCourse) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isPresent()){
            Course course = courseOptional.get();
            return course;
        }
        return null;
    }

    @Override
    public void deleteCourse(String id) {

    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> pageableCoursesBySkill(int pageNum, int pageSize, String skill) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return courseRepository.findAllBySkill(skill, pageable);
    }

    @Override
    public List<Course> getCoursesBySkill(String skill) {
        return courseRepository.findBySkill(skill);
    }

    @Override
    public Course getCourseById(String courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public Page<Course> getAllCourseByName(int pageNum, int pageSize, String courseName) {
        Pageable pageable = PageRequest.of(pageNum  - 1, pageSize);
        courseRepository.findAll(pageable);
        return null;
    }


}
