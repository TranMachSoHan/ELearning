package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Skill;
import course_eLearning.course_eLearning.service.SkillService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Override
    public List<Skill> getAllSkills() {
        return new ArrayList<Skill>(Arrays.asList(Skill.values()));
    }
}
