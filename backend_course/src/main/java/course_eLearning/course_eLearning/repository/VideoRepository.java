package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video,String> {
}
