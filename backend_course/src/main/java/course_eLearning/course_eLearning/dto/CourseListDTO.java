package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseListDTO {
    private String courseID;
    private String courseName;
    private String professorID;
    private String courseDescription;

    private static final ModelMapper modelMapper = new ModelMapper();

    public static CourseListDTO convertToCourseListDto(Course course){
        CourseListDTO courseListDTO = modelMapper.map(course, CourseListDTO.class);
        return courseListDTO;
    }
}
