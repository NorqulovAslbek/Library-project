package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private LocalDateTime createdDate;
    private Integer age;
}
