package course_eLearning.course_eLearning.dto.RestAPI;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorResponse {
    public String id;
    public String name;
    public String avatar;
    public String description;
}
