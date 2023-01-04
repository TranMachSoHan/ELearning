package course_eLearning.course_eLearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseProgressOverviewListDTO {
    private String courseProgressID;
    private String courseName;
    private double finishedPercentage;

}
