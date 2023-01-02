package course_eLearning.course_eLearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "videos")
public class Video {
    @Id
    private String videoID;
    private String url;
    private double duration ;

    public Video(String url, double duration) {
        this.url = url;
        this.duration = duration;
    }
}
