package com.example.templatesample.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class StudentMajorResponse implements Serializable {
    private long countSE;
    private long countDM;
    private long countBM;
    private long countIT;
}
