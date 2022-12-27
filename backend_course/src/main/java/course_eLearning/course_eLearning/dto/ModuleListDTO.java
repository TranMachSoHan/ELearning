package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleListDTO {
    private String moduleID;
    private String title;
    private boolean canViewed;
    private List<VideoPreviewDTO> videoList = new ArrayList<>();

    public void setVideoList(List<Video> videoList) {
        if(videoList != null ){
            this.videoList = videoList.stream().map(video -> new VideoPreviewDTO(video.getTitle(), video.getDuration()))
                    .collect(Collectors.toList());
        }
    }

    private class VideoPreviewDTO{
        protected VideoPreviewDTO(String title, double duration){
        }

    }
}
