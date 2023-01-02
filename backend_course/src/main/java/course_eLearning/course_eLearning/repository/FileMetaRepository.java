package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.FileMeta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileMetaRepository extends MongoRepository<FileMeta, String> {
}
