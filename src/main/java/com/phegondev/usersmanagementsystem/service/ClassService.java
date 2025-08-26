package com.phegondev.usersmanagementsystem.service;


import com.phegondev.usersmanagementsystem.dto.ReqClass;
import com.phegondev.usersmanagementsystem.entity.Classes;
import com.phegondev.usersmanagementsystem.repository.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
    @Autowired
    private ClassRepo classRepo;

    ReqClass reqClass = new ReqClass();

    public ReqClass create(ReqClass classRequest){
        ReqClass reqClass = new ReqClass();
        try{
            Classes classes = new Classes();
            classes.setClassName(classRequest.getClassName());
            classes.setClassCode(classRequest.getClassCode());

            Classes classResult = classRepo.save(classes);

            if (classResult.getId() > 0) {
                reqClass.setClasses((classResult));
                reqClass.setMessage("Class Saved Successfully");
                reqClass.setStatusCode(200);
            }

        } catch (Exception e) {
            reqClass.setStatusCode(500);
            reqClass.setError(e.getMessage());
        }
        return reqClass;
    }
    public ReqClass updateClass(Integer classId, Classes updatedClass){
        ReqClass reqClass = new ReqClass();
        try{
            Optional<Classes> classesOptional = classRepo.findById(classId);
            if(classesOptional.isPresent()){
                Classes existingClass = classesOptional.get();
                existingClass.setClassName(updatedClass.getClassName());
                existingClass.setClassCode(updatedClass.getClassCode());

                Classes savedRole = classRepo.save(existingClass);
                reqClass.setClasses(savedRole);
                reqClass.setStatusCode(200);
                reqClass.setMessage("Class updated successfully");

            } else{
                reqClass.setStatusCode(404);
                reqClass.setMessage("Not found");
            }
        } catch (Exception e) {
            reqClass.setStatusCode(500);
            reqClass.setMessage("Error occurred while updating Class: " + e.getMessage());
        }
        return reqClass;

    }
    public ReqClass getAllClasses(){
        ReqClass reqClass = new ReqClass();
        try{
            List<Classes> result = classRepo.findAll();
            if(!result.isEmpty()){
                reqClass.setClassList(result);
                reqClass.setStatusCode(200);
                reqClass.setMessage("List of classes successfully found");
            } else {
                reqClass.setStatusCode(404);
                reqClass.setMessage("Classes not found");

            }
        }catch (Exception e){
            reqClass.setStatusCode(500);
            reqClass.setMessage("internal server error finding classes"+ e.getMessage());
        }
        return reqClass;
    }

    public ReqClass getClassById(Integer classId){

        ReqClass reqClass = new ReqClass();
        try{
            Classes classById = classRepo.findById(classId).orElseThrow(() -> new RuntimeException("User Not found"));
            reqClass.setClasses(classById);
            reqClass.setStatusCode(200);
            reqClass.setMessage("Class with id '" + classId + "' found successfully");
        } catch (Exception e) {
            reqClass.setStatusCode(500);
            reqClass.setMessage("Error occurred: " + e.getMessage());
        }
        return reqClass;
    }
    public ReqClass deleteClass(Integer classId){

        ReqClass reqClass = new ReqClass();

        try{
            Optional<Classes> classesOptional = classRepo.findById(classId);
            if(classesOptional.isPresent()){
                classRepo.deleteById(classId);
                reqClass.setStatusCode(200);
                reqClass.setMessage("Class deleted successfully");
            }else{
                reqClass.setStatusCode(404);
                reqClass.setMessage("Class not found");
            }
        } catch (Exception e) {
            reqClass.setStatusCode(500);
            reqClass.setMessage("Error deleting class "+ e.getMessage());
        }
        return reqClass;
    }


}
