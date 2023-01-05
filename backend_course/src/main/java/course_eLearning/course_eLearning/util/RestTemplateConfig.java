package course_eLearning.course_eLearning.util;

import course_eLearning.course_eLearning.dto.ProfessorDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {
    private static RestTemplate restTemplate = new RestTemplate();

    @Value("api.base.url")
    private static String BASE_URL_API;

    public static ProfessorDTO getProfessorDTO(String professorID){
        ResponseEntity<String> response
                = restTemplate.getForEntity(BASE_URL_API + "/1", String.class);
        return null;
    }
}
