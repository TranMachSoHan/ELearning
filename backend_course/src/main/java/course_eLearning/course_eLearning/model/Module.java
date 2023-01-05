package course_eLearning.course_eLearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "modules")
public class Module {
    @Id
    private String moduleID;
    private String title;
    private boolean canViewed;
    @DBRef
    private List<Lesson> lessons = new ArrayList<>();
    @DBRef
    private FileMeta supportedFileMeta;

    public Module(String title, boolean canViewed, List<Lesson> lessons, FileMeta supportedFileMeta) {
        this.title = title;
        this.canViewed = canViewed;
        this.lessons = lessons;
        this.supportedFileMeta = supportedFileMeta;
    }

    public void addLesson(Lesson lesson){
        this.lessons.add(lesson);
    }
}
