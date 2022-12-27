package course_eLearning.course_eLearning.util;

import course_eLearning.course_eLearning.dto.CourseDetailDTO;
import course_eLearning.course_eLearning.dto.CourseListDTO;
import course_eLearning.course_eLearning.dto.ModuleListDTO;
import course_eLearning.course_eLearning.model.Course;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.Video;
import course_eLearning.course_eLearning.service.ModuleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//https://techmaster.vn/posts/37153/huong-dan-su-dung-modelmapper-trong-spring-boot
@Configuration
public class ModelMapperConfig {
    @Autowired
    static
    ModuleService moduleService;

    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Autowired
    private static ModelMapper modelMapper  = new ModelMapperConfig().modelMapper();

    public static CourseListDTO convertToCourseListDto(Course course){
        CourseListDTO courseListDTO = modelMapper.map(course, CourseListDTO.class);
        courseListDTO.setProfessor(course.getProfessorID());
        return courseListDTO;
    }

    public static CourseDetailDTO convertToCourseDetailDTO(Course course){
        CourseDetailDTO courseDetailDTO = modelMapper.map(course, CourseDetailDTO.class);

        List<Module> modules = course.getModules();
        if(modules != null){
            List<ModuleListDTO> moduleListDTOS = modules.stream().map(ModelMapperConfig::convertToModuleListDTO).collect(Collectors.toList());
            courseDetailDTO.setModules(moduleListDTOS);
        }else {
            courseDetailDTO.setModules(new ArrayList<>());
        }

        return courseDetailDTO;
    }

    public static ModuleListDTO convertToModuleListDTO(Module module){
        ModuleListDTO moduleListDTO = modelMapper.map(module, ModuleListDTO.class);
        List<Video> videoList= module.getVideoList();
        moduleListDTO.setVideoList(videoList);
        return moduleListDTO;
    }

}
