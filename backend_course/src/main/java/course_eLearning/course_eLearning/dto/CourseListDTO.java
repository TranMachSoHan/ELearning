package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.util.ModelMapperConfig;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseListDTO {
    private String courseID;
    private String courseName;
    private ProfessorDTO professor;
    private String courseDescription;


}
