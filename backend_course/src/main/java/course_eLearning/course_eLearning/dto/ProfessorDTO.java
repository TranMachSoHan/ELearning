package course_eLearning.course_eLearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    public String id;
    public String name;
    public String avatar;
    public String description;
    public Long numberOfCourses;
}
