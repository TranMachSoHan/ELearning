package course_eLearning.course_eLearning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class FileMeta {
    private static final long serialVersionUID = 1L;
    @Id
    private String fileID;

    private String fileName;
    private String filePath;
    private String version;

    public FileMeta(String fileName, String filePath, String version) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.version = version;
    }
}
