package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.repository.CourseRepository;
import course_eLearning.course_eLearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CourseSereviceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course createCourse(Course course) {
        Course newCourse = courseRepository.save(course);
        return newCourse;
    }

    @Override
    public void updateCourse(String id, Course course) {

    }

    @Override
    public void deleteCourse(String id) {

    }

    @Override
    public Collection<Course> getCourses() {
        return null;
    }

    @Override
    public Page<Course> getAllCourseBySkill(int pageNum, int pageSize, String skill) {
        Pageable pageable = PageRequest.of(pageNum  - 1, pageSize);
        courseRepository.findAllBySkill(skill, pageable);
        return null;
    }
}
