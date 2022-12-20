package course_eLearning.course_eLearning.dto;

import course_eLearning.course_eLearning.model.Course;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//https://techmaster.vn/posts/37153/huong-dan-su-dung-modelmapper-trong-spring-boot
@Configuration
public class ModelMapperConfig {
    private static final ModelMapper modelMapper = new ModelMapper();

}
