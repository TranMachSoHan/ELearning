package course_eLearning.course_eLearning.model;

import course_eLearning.course_eLearning.model.helper.CourseProgressType;
import course_eLearning.course_eLearning.model.helper.LessonStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courseProgresses")
public class CourseProgress implements Serializable {
    @Id
    private String courseProgressID;
    private Course course;
    private CourseProgressType courseProgressType;
    private String student;
    private double finishedPercentage;
    private List<Submission> submissions ;

    private HashMap<String, ModuleProgress> moduleProgresses;

    public CourseProgress(Course course, String student, boolean isEnrolled) {
        this.course = course;
        this.student = student;
        this.finishedPercentage = 0;
        this.submissions = new ArrayList<>();
        this.moduleProgresses = new HashMap<>();
        if (isEnrolled){
            this.courseProgressType = CourseProgressType.IN_PROGRESS;
            enrollLesson();
        }
        else {
            this.courseProgressType = CourseProgressType.SAVED_PROGRESS;
        }
    }


    public CourseProgress(Course course, String student) {
        this.course = course;
        this.student = student;
        this.finishedPercentage = 0;
        this.submissions = new ArrayList<>();
        this.moduleProgresses = new HashMap<>();
    }

    public void saveCourseProgress(){
        this.courseProgressType = CourseProgressType.SAVED_PROGRESS;
    }

    public void enrollLesson(){
        this.courseProgressType = CourseProgressType.IN_PROGRESS;
    }
    public void setLessonCompleted(Module module , String prevLesson){
        ModuleProgress foundModuleProgress = this.moduleProgresses.get(module.getModuleID());
        if(foundModuleProgress != null){
            if(prevLesson != null){
                foundModuleProgress.setLessonCompleted(prevLesson);

                // TODO: check module is Finished

            }
        }
    }

    public void setLessonProgressed(Module module , String newLesson){
        ModuleProgress foundModuleProgress = this.moduleProgresses.get(module.getModuleID());
        if(foundModuleProgress == null){

            ModuleProgress moduleProgress = new ModuleProgress();
            moduleProgress.addLessonLearned(newLesson);
            this.moduleProgresses.put(module.getModuleID(),moduleProgress);
        }
        else {
            foundModuleProgress.addLessonLearned(newLesson);
            this.moduleProgresses.put(module.getModuleID(),foundModuleProgress);
        }
    }

    public void setModuleProgresses(Module module , String prevLesson, String newLesson) {
        ModuleProgress foundModuleProgress = this.moduleProgresses.get(module.getModuleID());
        if(foundModuleProgress != null){
            // the previous lesson front end give is not null
            // this will not be null if the user click next , meaning this lesson had finished
            if(prevLesson != null){
                foundModuleProgress.setLessonCompleted(prevLesson);

                // TODO: check module is Finished
                this.finishedPercentage = foundModuleProgress.getNumLessonCompleted()/module.getLessons().size()*100;
            }
            if(newLesson != null){
                // when user hits next, the new article will be clicked
                foundModuleProgress.addLessonLearned(newLesson);
            }

        }
        else {
            ModuleProgress moduleProgress = new ModuleProgress();
            moduleProgress.addLessonLearned(newLesson);
            this.moduleProgresses.put(module.getModuleID(),moduleProgress);
        }
    }

    @Data
    public static class ModuleProgress implements Serializable {
        HashMap<String, LessonStatus> lessonLearned;

        public ModuleProgress() {
            this.lessonLearned = new HashMap<>();
        }

        public void setLessonCompleted(String lesson_id){
            this.lessonLearned.put(lesson_id, LessonStatus.FINISHED);
        }
        public void addLessonLearned(String lesson_id) {
            this.lessonLearned.putIfAbsent(lesson_id, LessonStatus.IN_PROGRESS);
        }
        public long getNumLessonCompleted(){
            return this.lessonLearned.values().stream()
                    .filter(s -> s.equals(LessonStatus.FINISHED)).count();
        }
    }

}

