package course_eLearning.course_eLearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courses")
public class Course {
    @Id
    private String courseID;
    private String courseName;
    private String professorID;
    private String courseDescription;
    private Collection<Comment> comments;
    private Skill skill;
    private Collection<Module> contents;
    private Collection<String> courseProgresses;

}
