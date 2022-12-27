package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePostDTO {
    private String courseName;
    private String professorID;
    private String courseDescription;
    private String skill;
}
