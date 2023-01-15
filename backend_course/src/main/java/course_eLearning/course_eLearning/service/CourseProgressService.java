package course_eLearning.course_eLearning.service;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.model.Module;
import org.springframework.stereotype.Service;

import java.util.List;
public interface CourseProgressService {
    public List<CourseProgress> getAll();

    public CourseProgress getById(String course_progress_id);

    public List<CourseProgress> getInProgress(String student_id);
    public List<CourseProgress> getSavedProgress(String student_id);
    public List<CourseProgress> getCompletedProgress(String student_id);
    public CourseProgress enrollCourseProgress(Course course, String student_id);
    public CourseProgress saveCourseProgress(Course course, String student_id);
    public void dropCourseProgress(String courseProgress_id);
    public CourseProgress findExistedCourseProgress(List<CourseProgress> courseProgresses, String student_id);

    public CourseProgress setInProgress(CourseProgress courseProgress);
    public CourseProgress setNextLesson(Module module , String course_progress_id, String new_lesson_id);
    public CourseProgress setLessonCompleted(Module module , String course_progress_id, String pre_lesson_id);
}
