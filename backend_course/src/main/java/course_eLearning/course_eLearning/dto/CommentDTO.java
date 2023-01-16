package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Comment comment;
    private String course_id;
}
