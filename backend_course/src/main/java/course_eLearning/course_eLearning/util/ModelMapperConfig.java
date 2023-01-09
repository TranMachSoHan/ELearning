package course_eLearning.course_eLearning.util;

import course_eLearning.course_eLearning.dto.*;
import course_eLearning.course_eLearning.dto.RestAPI.ProfessorResponse;
import course_eLearning.course_eLearning.model.*;
import course_eLearning.course_eLearning.model.Module;
import course_eLearning.course_eLearning.model.helper.CourseProgressType;
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
    static ModuleService moduleService;

    @Bean
    public ModelMapper modelMapper() {
        // Tạo object và cấu hình
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Autowired
    private static ModelMapper modelMapper = new ModelMapperConfig().modelMapper();

    public static Course convertDTOtoCourse(CoursePostDTO coursePostDTO) {
        Course course = modelMapper.map(coursePostDTO, Course.class);
        course.setSkill(Skill.valueOf(coursePostDTO.getSkill().toUpperCase()));
        course.setCourseID(null);
        return course;
    }

    public static CourseListDTO convertToCourseListDto(Course course) {
        CourseListDTO courseListDTO = modelMapper.map(course, CourseListDTO.class);

        // ProfessorResponse professorResponse =
        // RestTemplateConfig.getProfessorDTO(course.getProfessorID());
        ProfessorResponse professorResponse = null;
        ProfessorDTO professorDTO = modelMapper.map(professorResponse, ProfessorDTO.class);

        courseListDTO.setProfessor(professorDTO);
        return courseListDTO;
    }

    public static CourseDetailDTO convertToCourseDetailDTO(Course course, Long numberCoursesOfProf,
            Long numberOfStudent) {
        CourseDetailDTO courseDetailDTO = modelMapper.map(course, CourseDetailDTO.class);

        /**
         * List of course modules
         */
        List<Module> modules = course.getModules();
        if (modules != null) {
            List<ModuleOverviewListDTO> moduleOverviewListDTOS = modules.stream()
                    .map(ModelMapperConfig::convertToModuleOverviewListDTO).collect(Collectors.toList());
            courseDetailDTO.setModules(moduleOverviewListDTOS);
            courseDetailDTO.setComments(course.getComments());
        } else {
            courseDetailDTO.setModules(new ArrayList<>());
        }

        /**
         * set number of student
         */
        courseDetailDTO.setNumberOfStudent(numberOfStudent);

        /**
         * get professor detail
         */
        // ProfessorResponse professorResponse =
        // RestTemplateConfig.getProfessorDTO(course.getProfessorID());
        ProfessorResponse professorResponse = null;
        ProfessorDTO professorDTO = modelMapper.map(professorResponse, ProfessorDTO.class);
        professorDTO.setNumberOfCourses(numberCoursesOfProf);
        courseDetailDTO.setProfessor(professorDTO);

        return courseDetailDTO;
    }

    public static CourseProgressDetailDTO convertToCourseProgressDetailDTO(CourseProgress courseProgress) {
        CourseProgressDetailDTO progressDetailDTO = modelMapper.map(courseProgress, CourseProgressDetailDTO.class);
        progressDetailDTO.setCourse_id(courseProgress.getCourse().getCourseID());
        List<Module> modules = courseProgress.getCourse().getModules();
        // always not null
        progressDetailDTO.setModuleProgresses(modules, courseProgress.getModuleProgresses());

        return progressDetailDTO;
    }

    public static ModuleOverviewListDTO convertToModuleOverviewListDTO(Module module) {
        ModuleOverviewListDTO moduleOverviewListDTO = modelMapper.map(module, ModuleOverviewListDTO.class);
        moduleOverviewListDTO.setLessonList(module.getLessons());
        return moduleOverviewListDTO;
    }

    public static LessonDetailDTO convertToLessonDetailDTO(Lesson lesson, Object material) {
        LessonDetailDTO lessonDetailDTO = modelMapper.map(lesson, LessonDetailDTO.class);
        lessonDetailDTO.setMaterial(material);
        return lessonDetailDTO;
    }

    public static Module convertDTOToModule(ModulePostDTO modulePostDTO) {
        return modelMapper.map(modulePostDTO, Module.class);
    }

    public static CourseSettingDTO convertCourseToCourseSettingDTO(Course course) {
        CourseSettingDTO courseSettingDTO = modelMapper.map(course, CourseSettingDTO.class);
        List<Module> modules = course.getModules();
        if (modules != null) {
            List<ModuleOverviewListDTO> moduleOverviewListDTOS = modules.stream()
                    .map(ModelMapperConfig::convertToModuleOverviewListDTO).collect(Collectors.toList());
            courseSettingDTO.setModules(moduleOverviewListDTOS);
        } else {
            courseSettingDTO.setModules(new ArrayList<>());
        }
        return courseSettingDTO;
    }

    public static Lesson convertDTOToLesson(LessonPostDTO lessonPostDTO) {
        return modelMapper.map(lessonPostDTO, Lesson.class);
    }

    public static CourseProgressOverviewListDTO convertToCourseProgressOverviewDTO(CourseProgress courseProgress) {
        CourseProgressOverviewListDTO dto = modelMapper.map(courseProgress, CourseProgressOverviewListDTO.class);
        dto.setCourseID(courseProgress.getCourse().getCourseID());
        dto.setCourseName(courseProgress.getCourse().getCourseName());
        return dto;
    }
}
