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
@Document(collection = "courseProgresses")
public class CourseProgress {
    @Id
    private String courseProgressID;

    @DBRef
    private Course course;
    private String student;
    private double finishedPercentage;
    private int numOfModuleFinished ;
    private List<Submission> submissions ;

    public CourseProgress(Course course, String student, boolean isEnrolled) {
        this.course = course;
        this.student = student;
        this.finishedPercentage = isEnrolled ? 1 : 0;
        this.numOfModuleFinished = 0;
        this.submissions = new ArrayList<>();
    }

}
