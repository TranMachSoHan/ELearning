package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Lesson;
import course_eLearning.course_eLearning.model.helper.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleOverviewListDTO {
    private String moduleID;
    private String title;
    private boolean canViewed;
    private List<LessonListDTO> lessonList;

    public void setLessonList(List<Lesson> lessonList){
        if(lessonList != null){
            this.lessonList = lessonList.stream()
                    .map( lesson -> {
                        return new LessonListDTO(lesson.getLessonID(), lesson.getTitle(), lesson.getType(), lesson.getVideo());
                    })
                    .collect(Collectors.toList());
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class LessonListDTO {
        private String lessonID;

        private String title;
        private LessonType type;
        private VideoOverviewDTO video;

        public LessonListDTO(String lessonID, String title, LessonType type, Lesson.Video video) {
            this.lessonID=lessonID;
            this.title = title;
            this.type = type;
            if (this.video != null){
                this.video.setDuration(video.getDuration());
            }
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class VideoOverviewDTO{
        private double duration;
    }
}
