package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.CourseProgress;
import course_eLearning.course_eLearning.model.Lesson;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.helper.LessonStatus;
import course_eLearning.course_eLearning.model.helper.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseProgressDetailDTO {
    private String courseProgressID;
    private String course_id;
    private List<ModuleProgressDTO> moduleProgresses;
    private String lastLessonOpened;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ModuleProgressDTO{
        private String moduleID;
        private String title;
        private boolean canViewed;
        private List<LessonProgressListDTO> lessonProgressList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class LessonProgressListDTO{
        private String lessonID;
        private String title;
        private LessonType type;
        private double videoDuration;
        private LessonStatus lessonStatus;
    }

    public void setModuleProgresses(List<Module> modules, HashMap<String, CourseProgress.ModuleProgress> moduleProgressHashMap){

        if(modules != null){
            this.moduleProgresses = modules.stream()
                    .map(module -> {
                        CourseProgress.ModuleProgress moduleProgress =moduleProgressHashMap.get(module.getModuleID());
                        List<LessonProgressListDTO> lessonProgressListDTOS ;
                        if (moduleProgress != null){
                            lessonProgressListDTOS = setLessonProgressList(module.getLessons(), moduleProgress.getLessonLearned());
                        }else {
                            lessonProgressListDTOS = setLessonProgressList(module.getLessons(), null);
                        }
                        return new ModuleProgressDTO(module.getModuleID(), module.getTitle(), module.isCanViewed(),lessonProgressListDTOS);
                    })
                    .collect(Collectors.toList());
        }
        else{
            this.moduleProgresses = new ArrayList<>();
        }
    }
    private List<LessonProgressListDTO> setLessonProgressList(List<Lesson> lessonList, HashMap<String, LessonStatus> lessonLearned){
        if(lessonList != null){
            return lessonList.stream()
                    .map( lesson -> {
                        double duration = lesson.getVideo() != null ? lesson.getVideo().getDuration() : 0;
                        if (lessonLearned == null){
                            return new LessonProgressListDTO(lesson.getLessonID(), lesson.getTitle(), lesson.getType(), duration, LessonStatus.NOT_STARTED);
                        }
                        if(lessonLearned.get(lesson.getLessonID()) != null ){
                            return new LessonProgressListDTO(lesson.getLessonID(), lesson.getTitle(), lesson.getType(), duration, lessonLearned.get(lesson.getLessonID()));
                        }
                        else {
                            return new LessonProgressListDTO(lesson.getLessonID(), lesson.getTitle(), lesson.getType(), duration, LessonStatus.NOT_STARTED);
                        }
                    })
                    .collect(Collectors.toList());
        }

        return null;
    }

}
