package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Module;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSettingDTO {
    private String courseID;
    private String courseName;
    private String courseDescription;
    private List<ModuleOverviewListDTO> modules;


}
