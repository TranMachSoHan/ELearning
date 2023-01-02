package course_eLearning.course_eLearning.model;

import course_eLearning.course_eLearning.model.helper.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "lessons")
public class Lesson {
    @Id
    private String lessonID;

    private String title;
    private LessonType type;
    @DBRef
    private Video video;
    private Article article;
    @DBRef
    private List<Quiz> quizzes;

    public Lesson(String title, LessonType type, Video video, Article article, List<Quiz> quizzes) {
        this.title = title;
        this.type = type;
        this.video = video;
        this.article = article;
        this.quizzes = quizzes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Article {
        String paragraph;
        String image;
    }
}


