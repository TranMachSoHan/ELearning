package course_eLearning.course_eLearning.repository;

import course_eLearning.course_eLearning.model.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillRepository extends MongoRepository<Skill,String> {
}
