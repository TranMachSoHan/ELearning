package course_eLearning.course_eLearning.model;

import course_eLearning.course_eLearning.model.helper.LessonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courseProgresses")
public class CourseProgress {
    @Id
    private String courseProgressID;

    @DBRef
    private Course course;
    private String student;
    private double finishedPercentage;
    private int numOfModuleFinished ;
    private List<Submission> submissions ;

    private List<ModuleProgress> moduleProgresses;
    private String lastLessonOpened;

    public CourseProgress(Course course, String student, boolean isEnrolled) {
        this.course = course;
        this.student = student;
        this.finishedPercentage = isEnrolled ? 1 : 0;
        this.numOfModuleFinished = 0;
        this.submissions = new ArrayList<>();
        moduleProgresses = new ArrayList<>();
    }

    public void setModuleProgresses(Module module , String prevLesson, String newLesson) {
        ModuleProgress foundModuleProgress = moduleProgresses.stream()
                .filter(moduleProgress -> moduleProgress.getModuleID().equals(module.getModuleID()))
                .findAny()
                .orElse(null);
        this.lastLessonOpened = newLesson;
        if(foundModuleProgress != null){
            // the previous lesson front end give is not null
            // this will not be null if the user click next , meaning this lesson had finished
            if(prevLesson != null){
                foundModuleProgress.setLessonCompleted(prevLesson);

                // TODO: check module is Finished

            }
            if(newLesson != null){
                // when user hits next, the new article will be clicked
                foundModuleProgress.addLessonLearned(newLesson);
            }

        }
        else {
            ModuleProgress moduleProgress = new ModuleProgress(module.getModuleID());
            moduleProgress.addLessonLearned(newLesson);
            this.moduleProgresses.add(moduleProgress);
        }
    }

    @Data
    public static class ModuleProgress{
        String moduleID;
        HashMap<String, LessonStatus> lessonLearned;

        public ModuleProgress(String moduleID) {
            this.moduleID = moduleID;
            this.lessonLearned = new HashMap<>();
        }

        public void setLessonCompleted(String lesson_id){
            this.lessonLearned.put(lesson_id, LessonStatus.FINISHED);
        }
        public void addLessonLearned(String lesson_id) {
            this.lessonLearned.putIfAbsent(lesson_id, LessonStatus.IN_PROGRESS);
        }
    }

}

