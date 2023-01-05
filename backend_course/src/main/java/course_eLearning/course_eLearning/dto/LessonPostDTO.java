package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Lesson;
import course_eLearning.course_eLearning.model.Quiz;
import course_eLearning.course_eLearning.model.helper.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonPostDTO {
    private String title;
    private LessonType type;
    private Lesson.Video video;
    private Lesson.Article article;
    private List<Quiz> quizzes;

//    {
//        "title": "Apple Overview",
//            "type": "ARTICLE",
//            "article": {
//        "paragraph": "intro to apple",
//                "imageFile":{
//            "fileID": "63b46b51aeecb1289f158a93",
//                    "fileName": "apple-employee-70.webp",
//                    "filePath": "1652e90f-7c02-49dc-b0a8-8bfe3b6d1c42/apple-employee-70.webp"
//        }
//    },
//        "video":null,
//            "quizzes":null
//    }
}
