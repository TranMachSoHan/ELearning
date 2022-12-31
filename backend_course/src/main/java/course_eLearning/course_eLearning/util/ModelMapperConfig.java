package course_eLearning.course_eLearning.util;

import course_eLearning.course_eLearning.dto.*;
import course_eLearning.course_eLearning.model.*;
import course_eLearning.course_eLearning.model.Module;
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

    public static Course convertDTOtoCourse(CoursePostDTO coursePostDTO){
        Course course = modelMapper.map(coursePostDTO, Course.class);
        course.setSkill(Skill.valueOf(coursePostDTO.getSkill().toUpperCase()));
        course.setCourseID(null);
        return course;
    }
    public static CourseListDTO convertToCourseListDto(Course course){
        CourseListDTO courseListDTO = modelMapper.map(course, CourseListDTO.class);
        courseListDTO.setProfessor(course.getProfessorID());
        return courseListDTO;
    }

    public static CourseDetailDTO convertToCourseDetailDTO(Course course){
        CourseDetailDTO courseDetailDTO = modelMapper.map(course, CourseDetailDTO.class);

        List<Module> modules = course.getModules();
        if(modules != null){
            List<ModuleOverviewListDTO> moduleOverviewListDTOS = modules.stream().map(ModelMapperConfig::convertToModuleOverviewListDTO).collect(Collectors.toList());
            courseDetailDTO.setModules(moduleOverviewListDTOS);
            courseDetailDTO.setComments(course.getComments());
        }else {
            courseDetailDTO.setModules(new ArrayList<>());
        }

        return courseDetailDTO;
    }

    public static CourseProgressDetailDTO convertToCourseProgressDetailDTO(CourseProgress courseProgress){
        CourseProgressDetailDTO progressDetailDTO = modelMapper.map(courseProgress, CourseProgressDetailDTO.class);
        progressDetailDTO.setCourse_id(courseProgress.getCourse().getCourseID());
        List<Module> modules = courseProgress.getCourse().getModules();
        // always not null
        if(modules != null){
            progressDetailDTO.setModuleProgresses(modules, courseProgress.getModuleProgresses());
        }
        else {
            return null;
        }
        return progressDetailDTO;
    }

    public static ModuleOverviewListDTO convertToModuleOverviewListDTO(Module module){
        ModuleOverviewListDTO moduleOverviewListDTO = modelMapper.map(module, ModuleOverviewListDTO.class);
        moduleOverviewListDTO.setLessonList(module.getLessons());
        return moduleOverviewListDTO;
    }

}
