package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.FileMeta;
import course_eLearning.course_eLearning.model.Lesson;
import course_eLearning.course_eLearning.repository.LessonRepository;
import course_eLearning.course_eLearning.service.LessonService;
import course_eLearning.course_eLearning.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private MetaService metaService;

    public Pair<Lesson, Object> getLessonMaterialById(String lesson_id){
        Lesson lesson = lessonRepository.findById(lesson_id).orElse(null);
        if(lesson != null){
            switch (lesson.getType()){
                case ARTICLE:
                    Lesson.Article newArticle = lesson.getArticle();
                    FileMeta newImageFile = newArticle.getImageFile();
                    newImageFile.setFilePath(metaService.convertFilePath(newImageFile.getFilePath()));
                    newArticle.setImageFile(newImageFile);
                    return Pair.of(lesson,newArticle);
                case VIDEO:
                    Lesson.Video newVideo = lesson.getVideo();
                    FileMeta newVideoFile = newVideo.getVideoFile();
                    newVideoFile.setFilePath(metaService.convertFilePath(newVideoFile.getFilePath()));
                    newVideo.setVideoFile(newVideoFile);
                    return Pair.of(lesson,newVideo);
                case QUIZ:
                    return Pair.of(lesson,lesson.getQuizzes());
            }
        }
        return null;
    }
}
