package course_eLearning.course_eLearning.util;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
