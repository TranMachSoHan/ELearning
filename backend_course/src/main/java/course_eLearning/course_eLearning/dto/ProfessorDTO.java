package course_eLearning.course_eLearning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

@Data
@AllArgsConstructor
public class ProfessorDTO {
    private String professorID;
    private String professorName;
    private String professorDescription;

    public ProfessorDTO(String professorID){

        RestTemplate restTemplate = new RestTemplate();

        // get professor name by id

//        String response = restTemplate.getForObject(USER_API_URL+"?user_id="+professorId, String.class);
//        if( response.getStatusCode().is2xxSuccessful()) {
//            LOG.info("Todos sent to dashboard successfully!");
//        }

        this.professorID = professorID;
        this.professorName = "professorName";
    }
}
