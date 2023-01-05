package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Lesson;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.repository.LessonRepository;
import course_eLearning.course_eLearning.repository.ModuleRepository;
import course_eLearning.course_eLearning.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Module createLesson(String moduleId, Lesson lesson) {
        Optional<Module> optionalModule =  moduleRepository.findById(moduleId);

        if(optionalModule.isPresent()){
            Module module = optionalModule.get();
            lesson = lessonRepository.save(lesson);
            module.addLesson(lesson);
            return moduleRepository.save(module);
        }

        return null;
    }
}
