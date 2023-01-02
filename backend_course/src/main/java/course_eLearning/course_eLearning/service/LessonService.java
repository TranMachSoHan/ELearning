package course_eLearning.course_eLearning.service;

import course_eLearning.course_eLearning.model.Lesson;
import org.springframework.data.util.Pair;

public interface LessonService {
    public Pair<Lesson,Object> getLessonMaterialById(String lesson_id);
}
