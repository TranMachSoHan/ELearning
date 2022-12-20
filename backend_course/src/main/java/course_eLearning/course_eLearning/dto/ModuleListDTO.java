package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleListDTO {
    private String moduleID;
    private String title;
    private boolean canViewed;
    private List<VideoPreviewDTO> videoList;

    public void setVideoList(List<Video> videoList) {
        if(videoList.size() > 0){
            this.videoList = videoList.stream().map(video -> new VideoPreviewDTO(video.getTitle(), video.getDuration()))
                    .collect(Collectors.toList());
        }

    }

    private class VideoPreviewDTO{
        protected VideoPreviewDTO(String title, double duration){
        }

    }
}
