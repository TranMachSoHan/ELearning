package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Lesson;
import course_eLearning.course_eLearning.repository.LessonRepository;
import course_eLearning.course_eLearning.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    public Pair<Lesson, Object> getLessonMaterialById(String lesson_id){
        Lesson lesson = lessonRepository.findById(lesson_id).orElse(null);
        if(lesson != null){
            switch (lesson.getType()){
                case ARTICLE:
                    return Pair.of(lesson,lesson.getArticle());
                case VIDEO:
                    return Pair.of(lesson,lesson.getVideo());
                case QUIZ:
                    return Pair.of(lesson,lesson.getQuizzes());
            }
        }
        return null;
    }
}
