package com.example.templatesample.dto;

import com.example.templatesample.model.enums.Role;
import lombok.Data;


@Data
public class StudentUpdateDTO {
    public String name;
    public Integer age;
    public String education;
    public String avatar;
    public String major;
    public String minor;
}
