package com.example.templatesample.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMetaResponse {
    private String fileID;
    private String fileName;
    private String filePath;
}
