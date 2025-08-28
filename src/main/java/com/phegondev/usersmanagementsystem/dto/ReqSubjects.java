package com.phegondev.usersmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.phegondev.usersmanagementsystem.entity.Subjects;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqSubjects {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String subjectName;
    private String subjectCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    private Subjects subjects;
    private List<Subjects> subjectsList;


}
