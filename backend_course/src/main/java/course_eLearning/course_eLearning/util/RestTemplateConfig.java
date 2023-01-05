package course_eLearning.course_eLearning.util;

import course_eLearning.course_eLearning.dto.ProfessorDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestTemplateConfig {
    private static RestTemplate restTemplate = new RestTemplate();

    private static String BASE_URL_API = "http://localhost:8080";


    public static ProfessorDTO getProfessorDTO(String professorID){
        ResponseEntity<ProfessorDTO> response
                = restTemplate.getForEntity(BASE_URL_API + "/profile/professor/"+professorID, ProfessorDTO.class);
        if( response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        else {
            throw new NullPointerException();
        }
    }

    public static List<String> getProfessorList() throws NullPointerException{
        return getProfileList("professors");
    }
    public static List<String> getStudentList() throws NullPointerException{
        return getProfileList("students");
    }

    private static List<String> getProfileList(String role) throws NullPointerException{
        ResponseEntity<List<String>> response = restTemplate.exchange(BASE_URL_API + "/profile/all-"+role+"-string",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
                });
        if( response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        else {
            throw new NullPointerException();
        }
    }
}
