package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.CourseProgress;
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
    public List<CourseProgress> getInProgress(String student_id) {
        List<CourseProgress> courseProgresses = progressRepository.findAllByStudent(student_id);
        courseProgresses.removeIf(courseProgress -> courseProgress.getFinishedPercentage() <= 0);
        return courseProgresses;
    }
}
