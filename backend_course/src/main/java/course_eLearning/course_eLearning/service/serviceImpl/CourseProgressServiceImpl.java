package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.helper.CourseProgressType;
import course_eLearning.course_eLearning.repository.CourseProgressRepository;
import course_eLearning.course_eLearning.service.CourseProgressService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseProgressServiceImpl implements CourseProgressService {
    @Autowired
    private CourseProgressRepository progressRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

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
    public void dropCourseProgress(String courseProgress_id) {
        Optional<CourseProgress> courseProgressOptional = progressRepository.findById(courseProgress_id);
        if (courseProgressOptional.isPresent()){
            CourseProgress courseProgress = courseProgressOptional.get();

            // remove from course
            Query query= Query.query(Criteria.where("$id").is(new ObjectId(courseProgress_id)));
            Update update = new Update().pull("courseProgresses", query);
            mongoTemplate.updateMulti(new Query(), update, Course.class);

            progressRepository.delete(courseProgress);
        }
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

    @Override
    public CourseProgress setNextLesson(Module module , String course_progress_id, String new_lesson_id) {
        CourseProgress courseProgress = getById(course_progress_id);

        courseProgress.setLessonProgressed(module , new_lesson_id);
        courseProgress= progressRepository.save(courseProgress);

        return courseProgress;
    }

    @Override
    public CourseProgress setLessonCompleted(Module module , String course_progress_id, String completed_lesson_id) {
        CourseProgress courseProgress = getById(course_progress_id);

        courseProgress.setLessonCompleted(module , completed_lesson_id);
        courseProgress= progressRepository.save(courseProgress);

        return courseProgress;
    }

}
