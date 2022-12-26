package course_eLearning.course_eLearning.service.serviceImpl;

import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.repository.ModuleRepository;
import course_eLearning.course_eLearning.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;
    @Override
    public Module findById(String moduleId) {
        Optional<Module> optionalModule =  moduleRepository.findById(moduleId);
        return optionalModule.orElse(null);
    }
}
