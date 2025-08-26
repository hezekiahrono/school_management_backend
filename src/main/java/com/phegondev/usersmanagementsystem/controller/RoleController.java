package com.phegondev.usersmanagementsystem.controller;


import com.phegondev.usersmanagementsystem.dto.ReqRes;
import com.phegondev.usersmanagementsystem.dto.ReqRole;
import com.phegondev.usersmanagementsystem.entity.OurUsers;
import com.phegondev.usersmanagementsystem.entity.Role;
import com.phegondev.usersmanagementsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;


    @PostMapping("/admin/role/create")
    public ResponseEntity<ReqRole> create(@RequestBody ReqRole role){
        return ResponseEntity.ok(roleService.create(role));
    }
    @PutMapping("/admin/role/update/{roleId}")
    public ResponseEntity<ReqRole> updateUser(@PathVariable Integer roleId, @RequestBody Role role){
        return ResponseEntity.ok(roleService.updateRole(roleId, role));
    }
    @GetMapping("/admin/role/get-all-roles")
    public ResponseEntity<ReqRole> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());

    }

    @GetMapping("/admin/role/{roleId}")
    public ResponseEntity<ReqRole> getRoleByID(@PathVariable Integer roleId){
        return ResponseEntity.ok(roleService.getRolesById(roleId));

    }
    @DeleteMapping("/admin/role/delete/{roleId}")
    public ResponseEntity<ReqRole> deleteRole(@PathVariable Integer roleId){
        return ResponseEntity.ok(roleService.deleteRole(roleId));
    }


}
