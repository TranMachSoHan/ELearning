package course_eLearning.course_eLearning.model;

import course_eLearning.course_eLearning.model.helper.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "lessons")
public class Lesson implements Serializable {
    @Id
    private String lessonID;

    private String title;
    private LessonType type;
    private Video video;
    private Article article;
    @DBRef
    private List<Quiz> quizzes;

    public Lesson(String title, LessonType type, Video video, Article article, List<Quiz> quizzes) {
        this.title = title;
        this.type = type;
        this.video = video == null ? new Video(null, 0) : video;
        this.article = article == null ? new Article(null, null) : article;
        this.quizzes = quizzes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Article implements Serializable {
        String paragraph;
        FileMeta imageFile;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Video implements Serializable  {
        FileMeta videoFile = null;
        double duration = 0;

        public Video(String fileName, String filePath, double duration) {
            this.videoFile = new FileMeta(fileName, filePath);
            this.duration = duration;
        }
    }
}



