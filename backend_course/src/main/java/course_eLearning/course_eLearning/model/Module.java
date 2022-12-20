package course_eLearning.course_eLearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

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
    private Collection<Video> videoList;
    @DBRef
    private Collection<Quiz> quizList;
    private boolean isFinished;
    private File supportedFile;

    public Module(String title, boolean canViewed, Collection<Video> videoList, Collection<Quiz> quizList, boolean isFinished, File supportedFile) {
        this.title = title;
        this.canViewed = canViewed;
        this.videoList = videoList;
        this.quizList = quizList;
        this.isFinished = isFinished;
        this.supportedFile = supportedFile;
    }
}
