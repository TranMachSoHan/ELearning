package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.CourseProgress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseProgressDetailDTO {
    private String courseProgressID;
    private String courseID;
    private List<ModuleListDTO> modules;
    private List<CourseProgress.ModuleProgress> moduleProgresses;
    private String lastLessonOpened;

}
