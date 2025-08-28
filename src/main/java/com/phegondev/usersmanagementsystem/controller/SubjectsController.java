package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.dto.ReqClass;
import com.phegondev.usersmanagementsystem.dto.ReqSubjects;
import com.phegondev.usersmanagementsystem.entity.Subjects;
import com.phegondev.usersmanagementsystem.service.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectsController {

    @Autowired
    private SubjectsService subjectsService;

    @PostMapping("auth/admin/class/create")
    public ResponseEntity<ReqSubjects> createSubjects(@RequestBody ReqSubjects reqSubjects){
        return  ResponseEntity.ok(subjectsService.create(reqSubjects));
    }

    @PutMapping("auth/admin/subjects/{subId}")
    public ResponseEntity<ReqSubjects> updateSubjects(@PathVariable Integer subId, @RequestBody Subjects subjects){
        return ResponseEntity.ok(subjectsService.updateSubjects(subId, subjects));
    }

    @GetMapping("auth/admin/subjects/get-all-subjects")
    public ResponseEntity<ReqSubjects> getAllSubjects(){
        return ResponseEntity.ok(subjectsService.getAllSubjects());
    }

    @GetMapping("auth/admin/subjects/getById/{subId}")
    public ResponseEntity<ReqSubjects> getSubjectById(@PathVariable Integer subId){
        return ResponseEntity.ok(subjectsService.getSubjectById(subId));
    }
}
