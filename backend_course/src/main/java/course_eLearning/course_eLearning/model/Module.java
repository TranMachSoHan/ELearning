package course_eLearning.course_eLearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "modules")
public class Module {
    private Long moduleID;
    private String title;
    private boolean canViewed;
    @DBRef
    private Collection<Video> courseVideo;
    @DBRef
    private Collection<Quiz> quizCollection;
    private boolean isFinished;
    private File file;
}
