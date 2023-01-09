package course_eLearning.course_eLearning.service.serviceImpl;

import com.amazonaws.services.glue.model.EntityNotFoundException;
import com.amazonaws.services.s3.model.*;
import course_eLearning.course_eLearning.model.FileMeta;
import course_eLearning.course_eLearning.repository.FileMetaRepository;
import course_eLearning.course_eLearning.service.AmazonS3Service;
import course_eLearning.course_eLearning.service.MetaService;
// import course_eLearning.course_eLearning.service.kafka.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class MetaServiceImpl implements MetaService {
    @Autowired
    private AmazonS3Service amazonS3Service;

    @Autowired
    private FileMetaRepository fileMetaRepository;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.s3.bucket.video}")
    private String bucketVideo;

    private final Logger logger = LoggerFactory.getLogger(MetaService.class);

    @Override
    public FileMeta uploadFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Length", String.valueOf(multipartFile.getSize()));
        String fileName = String.format("%s", multipartFile.getOriginalFilename());
        metadata.put("Content-Type", multipartFile.getContentType());

        // Uploading file to s3
        String urlPath = amazonS3Service.upload(
                bucketName, fileName, Optional.of(metadata), multipartFile.getInputStream());

        // Saving metadata to db
        return fileMetaRepository.save(new FileMeta(fileName, urlPath));
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }


    public FileMeta upload(MultipartFile multipartFile) throws IOException{
        if (multipartFile.isEmpty())
            throw new IllegalStateException("Cannot upload empty file");

        try {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("Content-Length", String.valueOf(multipartFile.getSize()));
            String fileName = String.format("%s", multipartFile.getOriginalFilename());
            metadata.put("Content-Type", multipartFile.getContentType());

            String path = amazonS3Service.uploadFileTos3bucket(bucketName,fileName, Optional.of(metadata), multipartFile.getInputStream());

            return fileMetaRepository.save(new FileMeta(fileName, path));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // Saving metadata to db
    }
    @Override
    public S3Object download(String file_id) {
        FileMeta fileMeta = fileMetaRepository.findById(file_id).orElseThrow(() -> new EntityNotFoundException("Cannot find file meta"));
        return amazonS3Service.download(fileMeta.getFilePath(),fileMeta.getFileName());
    }

//    http://www.elrafact.com/upload-video-file-in-amazon-s3-storage-using-spring-boot/
    public String convertFilePath(String path){
        return amazonS3Service.convertWithS3UrlPath(bucketName, path);
    }
    @Override
    public List<FileMeta> list() {
        return null;
    }
}
