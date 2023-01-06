package course_eLearning.course_eLearning.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModulePostDTO {
    private String title;
    private boolean canViewed;
}
