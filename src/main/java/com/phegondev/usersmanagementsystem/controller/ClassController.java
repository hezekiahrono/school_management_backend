package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.dto.ReqClass;
import com.phegondev.usersmanagementsystem.dto.ReqRole;
import com.phegondev.usersmanagementsystem.entity.Classes;
import com.phegondev.usersmanagementsystem.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping("admin/class/create")
    public ResponseEntity<ReqClass> create(@RequestBody ReqClass reqClass){
        return ResponseEntity.ok(classService.create(reqClass));
    }
    @PutMapping("admin/class/update/{classId}")
    public ResponseEntity<ReqClass> updateUser(@PathVariable Integer classId, @RequestBody Classes classes){
        return ResponseEntity.ok(classService.updateClass(classId, classes));
    }

    @GetMapping("admin/class/get-all-classes")
    public ResponseEntity<ReqClass> getAllClasses(){
        return ResponseEntity.ok(classService.getAllClasses());

    }
    @GetMapping("admin/class/{classId}")
    public ResponseEntity<ReqClass> getClassByID(@PathVariable Integer classId){
        return ResponseEntity.ok(classService.getClassById(classId));

    }
    @DeleteMapping("admin/class/delete/{classId}")
    public ResponseEntity<ReqClass> deleteClass(@PathVariable Integer classId){
        return ResponseEntity.ok(classService.deleteClass(classId));
    }

}
