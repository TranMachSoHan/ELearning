package course_eLearning.course_eLearning.service;

import course_eLearning.course_eLearning.model.FileMeta;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface MetaService {
    public abstract FileMeta uploadFile(MultipartFile file) throws IOException;
    public FileMeta upload(MultipartFile multipartFile) throws IOException;
    public String convertFilePath(String path);
    public abstract S3Object download(String file_id);
    public abstract List<FileMeta> list();
}
