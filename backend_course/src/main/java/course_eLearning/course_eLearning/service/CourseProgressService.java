package course_eLearning.course_eLearning.service;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.CourseProgress;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CourseProgressService {
    public List<CourseProgress> getAll();

    public List<CourseProgress> getInProgress(String student_id);
}
