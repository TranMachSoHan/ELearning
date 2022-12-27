package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Comment;
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
    private String professorID;
    private String courseDescription;
    private Collection<Comment> comments;
    private Skill skill;
    private Collection<Module> contents;
    private Collection<String> courseProgresses;
}

