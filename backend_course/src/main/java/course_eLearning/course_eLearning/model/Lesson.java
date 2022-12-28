package course_eLearning.course_eLearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
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
    private List<Article> articles;
    @DBRef
    private List<Quiz> quizzes;

    public Lesson(String title, LessonType type, Video video, List<Article> articles, List<Quiz> quizzes) {
        this.title = title;
        this.type = type;
        this.video = video;
        this.articles = articles;
        this.quizzes = quizzes;
    }
}



