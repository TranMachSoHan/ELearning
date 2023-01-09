package com.example.templatesample.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StudentMajorResponse {
    private long countSE;
    private long countDM;
    private long countBM;
    private long countIT;
}
