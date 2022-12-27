package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailDTO {

    private String courseID;
    private String courseName;
    private String professorID;
    private String courseDescription;
    private List<ModuleListDTO> modules;

}

