package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.helper.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonListDTO {
    private String lessonID;

    private String title;
    private LessonType type;
    private double videoDuration;
}
