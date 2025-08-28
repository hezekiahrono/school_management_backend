package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.dto.ReqSubjects;
import com.phegondev.usersmanagementsystem.entity.Subjects;
import com.phegondev.usersmanagementsystem.repository.SubjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectsService {

    @Autowired
    SubjectsRepo subjectsRepo;

    ReqSubjects reqSubjects = new ReqSubjects();

    public ReqSubjects create(ReqSubjects reqSubjects){

       ReqSubjects req = new ReqSubjects();

        try{
            Subjects subjects = new Subjects();
            subjects.setSubjectName(reqSubjects.getSubjectName());
            subjects.setSubjectCode(reqSubjects.getSubjectCode());
            subjects.setCreatedAt(reqSubjects.getCreatedAt());

            Subjects subjectsResult = subjectsRepo.save(subjects);

            if(subjectsResult.getId()>0){

                req.setSubjects(subjectsResult);
                req.setMessage("Subject saved successfully");
                req.setStatusCode(200);

            }
        }catch (Exception e){
            req.setStatusCode(500);
            req.setMessage("Error occurred " + e.getMessage());
        }

        return req;
    }
    public ReqSubjects updateSubjects(Integer subId, Subjects updatedSubjects){

        ReqSubjects reqSubjects = new ReqSubjects();
        try {
            Optional<Subjects> subjectsOptional = subjectsRepo.findById(subId);
            if(subjectsOptional.isPresent()){
                Subjects existingSubjects = subjectsOptional.get();
                existingSubjects.setSubjectName(updatedSubjects.getSubjectName());
                existingSubjects.setSubjectCode(updatedSubjects.getSubjectCode());

                Subjects savedSubjects = subjectsRepo.save(existingSubjects);
                reqSubjects.setSubjects(savedSubjects);
                reqSubjects.setStatusCode(200);
                reqSubjects.setMessage("Subject successfully Updated");
            } else {
                reqSubjects.setStatusCode(404);
                reqSubjects.setMessage("Subject not found");
            }
        } catch (Exception e){
            reqSubjects.setStatusCode(500);
            reqSubjects.setMessage("Error occurred updating "+e.getMessage());
        }
        return reqSubjects;
    }
    public ReqSubjects getAllSubjects(){

        ReqSubjects reqSubjects = new ReqSubjects();
        try{
            List<Subjects> result = subjectsRepo.findAll();
            if(!result.isEmpty()){
                reqSubjects.setSubjectsList(result);
                reqSubjects.setStatusCode(200);
                reqSubjects.setMessage("Successfully found subjects");
            } else{
                reqSubjects.setStatusCode(404);
                reqSubjects.setMessage("No subjects were found");
            }
        } catch (Exception e) {
            reqSubjects.setStatusCode(500);
            reqSubjects.setMessage("Error occurred finding subjects "+ e.getMessage());
        }
        return reqSubjects;
    }
    public  ReqSubjects getSubjectById(Integer subId){

        ReqSubjects reqSubjects = new ReqSubjects();
        try{
            Subjects subjectById = subjectsRepo.findById(subId).orElseThrow(() -> new RuntimeException("Subject Not found"));
            reqSubjects.setSubjects(subjectById);
            reqSubjects.setStatusCode(200);
            reqSubjects.setMessage("Subject successfully found  with "+subId+ " id");
        } catch (Exception e) {
            reqSubjects.setStatusCode(500);
            reqSubjects.setMessage("Error occurred "+ e.getMessage());
        }
        return reqSubjects;
    }
}
