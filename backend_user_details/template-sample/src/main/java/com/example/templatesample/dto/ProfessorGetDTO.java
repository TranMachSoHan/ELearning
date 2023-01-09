package com.example.templatesample.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProfessorGetDTO implements Serializable {
    public String id;
    public String name;
    public String avatar;
    public String description;
}
