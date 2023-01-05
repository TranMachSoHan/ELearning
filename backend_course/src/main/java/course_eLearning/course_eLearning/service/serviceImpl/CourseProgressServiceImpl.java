package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.model.helper.CourseProgressType;
import course_eLearning.course_eLearning.repository.CourseProgressRepository;
import course_eLearning.course_eLearning.service.CourseProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseProgressServiceImpl implements CourseProgressService {
    @Autowired
    private CourseProgressRepository progressRepository;

    @Override
    public List<CourseProgress> getAll() {
        return progressRepository.findAll();
    }

    @Override
    public CourseProgress getById(String course_progress_id) {
        return progressRepository.findById(course_progress_id).orElse(null);
    }

    @Override
    public List<CourseProgress> getInProgress(String student_id) {
        List<CourseProgress> courseProgresses = progressRepository.findAllByStudent(student_id);
        courseProgresses.removeIf(courseProgress -> courseProgress.getCourseProgressType() != CourseProgressType.IN_PROGRESS);
        return courseProgresses;
    }

    @Override
    public List<CourseProgress> getSavedProgress(String student_id) {
        List<CourseProgress> courseProgresses = progressRepository.findAllByStudent(student_id);
        courseProgresses.removeIf(courseProgress -> courseProgress.getCourseProgressType() != CourseProgressType.SAVED_PROGRESS);
        return courseProgresses;
    }

    @Override
    public List<CourseProgress> getCompletedProgress(String student_id) {
        List<CourseProgress> courseProgresses = progressRepository.findAllByStudent(student_id);
        courseProgresses.removeIf(courseProgress -> courseProgress.getCourseProgressType() != CourseProgressType.COMPLETED);
        return courseProgresses;
    }
    @Override
    public CourseProgress enrollCourseProgress(Course course, String student_id) {
        // enroll new course progress
        CourseProgress courseProgress = new CourseProgress(course, student_id);
        courseProgress.enrollLesson();
        courseProgress = progressRepository.save(courseProgress);
        return courseProgress;
    }

    @Override
    public CourseProgress saveCourseProgress(Course course, String student_id) {
        CourseProgress courseProgress = new CourseProgress(course, student_id);
        courseProgress.saveCourseProgress();
        courseProgress = progressRepository.save(courseProgress);
        return courseProgress;
    }

    @Override
    public CourseProgress findExistedCourseProgress(List<CourseProgress> courseProgresses, String student_id) {

        return courseProgresses.stream()
                .filter(progress ->
                        progress.getStudent().equals(student_id)
                )
                .findAny()
                .orElse(null);
    }

    @Override
    public CourseProgress setInProgress(CourseProgress courseProgress) {
        if (courseProgress.getCourseProgressType().equals(CourseProgressType.SAVED_PROGRESS)){
            courseProgress.enrollLesson();
            courseProgress = progressRepository.save(courseProgress);
        }
        return courseProgress;
    }
}
