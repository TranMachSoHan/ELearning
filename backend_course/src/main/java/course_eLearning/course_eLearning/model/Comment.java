package course_eLearning.course_eLearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    private String studentID ;
    private String details;
    private Date time;
    private String star;

    public Comment(String studentID, String details, Date time, String star) {
        this.studentID = studentID;
        this.details = details;
        this.time = time;
        this.star = star;
    }
}
