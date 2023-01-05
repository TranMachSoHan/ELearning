package course_eLearning.course_eLearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    public String id;
    public String name;
    public String avatar;
    public String description;

}
