package com.example.templatesample.model;

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

    public FileMeta(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}

