package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.helper.CourseProgressType;
import course_eLearning.course_eLearning.repository.CourseProgressRepository;
import course_eLearning.course_eLearning.repository.CourseRepository;
import course_eLearning.course_eLearning.repository.ModuleRepository;
import course_eLearning.course_eLearning.service.CourseProgressService;
import course_eLearning.course_eLearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    private ModuleRepository moduleRepository;

    @Autowired
    private CourseProgressService progressService;

    @Override
    public Course createCourse(Course course) {
        Course newCourse = courseRepository.save(course);
        return newCourse;
    }

    @Override
    public Course createModule(String course_id, Module module) {
        Optional<Course> courseOptional = courseRepository.findById(course_id);
        if (courseOptional.isPresent()){

            Course course = courseOptional.get();
            module = moduleRepository.save(module);
            course.addModule(module);
            courseRepository.save(course);
            return course;
        }
        return null;
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
    @Cacheable(value = "courses")
    public List<Course> getCourses() {
        System.out.println("call get all courses service!!!");
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> pageableCoursesBySkill(int pageNum, int pageSize, String skill) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return courseRepository.findAllBySkill(skill.toUpperCase(), pageable);
    }

    @Override
    @Cacheable(value = "courses",key = "#skill")
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

            // check whether the course has the course progress of that student or not
            CourseProgress courseProgress = progressService.findExistedCourseProgress(course.getCourseProgresses(),student_id);

            // student does not enroll this course
            if (courseProgress == null){
                System.out.println("null in the enroll course");
                courseProgress = progressService.enrollCourseProgress(course, student_id);
                course.addCourseProgress(courseProgress);
                courseRepository.save(course);
            }
            // student has enroll this course, to make sure convert to the in progress type
            else{
                progressService.setInProgress(courseProgress);
            }

            // if not then return the exiting course progress
            return courseProgress;
        }
        else{
            return null;
        }
    }

    @Override
    public CourseProgress saveCourse(String course_id, String student_id) {
        Optional<Course> courseOptional = courseRepository.findById(course_id);
        if(courseOptional.isPresent()){
            Course course = courseOptional.get();

            // check whether the course has the course progress of that student or not
            CourseProgress courseProgress = progressService.findExistedCourseProgress(course.getCourseProgresses(),student_id);

            if(courseProgress == null){
                courseProgress = progressService.saveCourseProgress(course, student_id);
                course.addCourseProgress(courseProgress);
                courseRepository.save(course);
            }

            return courseProgress;
        }
        else{
            return null;
        }
    }

    @Override
    public Long countProfessorCourses(String professor_id) {
        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .filter(course -> course.getProfessorID().equals(professor_id))
                .count();
    }

    @Override
    public Long countInProgressCourse(Course course) {
        List<CourseProgress> courseProgresses = course.getCourseProgresses();
        return courseProgresses.stream()
                .filter(progress->progress.getCourseProgressType() == CourseProgressType.IN_PROGRESS)
                .count();
    }


}
