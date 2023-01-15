package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Comment;
import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private String courseID;
    private String courseName;
    private Collection<String> courseProgresses;
}

