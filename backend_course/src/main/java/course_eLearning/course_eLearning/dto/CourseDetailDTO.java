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
    private ProfessorDTO professor;
    private String courseDescription;
    private Long numberOfStudent;
    private List<ModuleOverviewListDTO> modules;
    private List<Comment> comments;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ProfessorDTO{
        String professorID;
        String professorName;
        String professorDescription;
    }
}

