package com.phegondev.usersmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "classes")
@Data
public class Classes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String className;
    private String classCode;

}
