package com.phegondev.usersmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.phegondev.usersmanagementsystem.entity.Classes;
import com.phegondev.usersmanagementsystem.entity.Role;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqClass {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String className;
    private String classCode;
    private Classes classes;
    private List<Classes> classList;
}
