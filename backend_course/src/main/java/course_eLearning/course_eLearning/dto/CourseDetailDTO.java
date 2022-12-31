package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {

    private String courseID;
    private String courseName;
    private String star;
    private String professorID;
    private String courseDescription;
    private List<ModuleOverviewListDTO> modules;
    private List<Comment> comments;
}

