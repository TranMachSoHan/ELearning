package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.repository.CourseProgressRepository;
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

    @Autowired
    private CourseProgressRepository courseProgressRepository;

    @Override
    public Course createCourse(Course course) {
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
        return courseRepository.findAllBySkill(skill.toUpperCase(), pageable);
    }

    @Override
    public List<Course> getCoursesBySkill(String skill) {
        // Handle upper case
        return courseRepository.findBySkill(skill.toUpperCase());
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

    @Override
    public CourseProgress enrollCourse(String course_id, String student_id) {
        Optional<Course> courseOptional = courseRepository.findById(course_id);
        if(courseOptional.isPresent()){
            Course course = courseOptional.get();

            // Save course progress
            CourseProgress courseProgress = new CourseProgress(course, student_id, true);
            courseProgress = courseProgressRepository.save(courseProgress);

            course.addCourseProgress(courseProgress.getCourseProgressID());
            courseRepository.save(course);

            return courseProgress;
        }
        else{
            return null;
        }
    }


}
