package course_eLearning.course_eLearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courses")
public class Course {
    @Id
    private String courseID;
    private String courseName;
    private String professorID;
    private String courseDescription;
    @DBRef
    private List<Comment> comments = new ArrayList<>();
    private Skill skill = null;
    private String star = null;
    @DBRef
    private List<Module> modules;
    private List<String> courseProgresses;

    public Course(String courseName, String professorID, String courseDescription, List<Comment> comments, Skill skill, String star, List<Module> modules) {
        this.courseName = courseName;
        this.professorID = professorID;
        this.courseDescription = courseDescription;
        this.comments = comments;
        this.skill = skill;
        this.star = star;
        this.modules = modules;
        this.courseProgresses = new ArrayList<>();
    }

    public Course(String courseName, String professorID,  String courseDescription){
        this.courseName = courseName;
        this.professorID = professorID;
        this.courseDescription = courseDescription;
    }

    public void addCourseProgress(String courseProgress) {
        this.courseProgresses.add(courseProgress);
    }

    public void updateCourse(Course course){
        this.courseName = course.courseName;
        this.courseDescription = course.courseDescription;
    }
}
