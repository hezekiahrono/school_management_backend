package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.dto.ReqRes;
import com.phegondev.usersmanagementsystem.dto.ReqRole;
import com.phegondev.usersmanagementsystem.entity.OurUsers;
import com.phegondev.usersmanagementsystem.entity.Role;
import com.phegondev.usersmanagementsystem.repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepo roleRepo;

    ReqRole reqRole = new ReqRole();

    public ReqRole create(ReqRole roleRequest) {

        ReqRole reqRole = new ReqRole();
        try {
            Role role = new Role();
            role.setRole(roleRequest.getRole());
            role.setDescription(roleRequest.getDescription());

            Role roleResult = roleRepo.save(role);

            if (roleResult.getId() > 0) {
                reqRole.setRoles((roleResult));
                reqRole.setMessage("Role Saved Successfully");
                reqRole.setStatusCode(200);
            }

        } catch (Exception e) {
            reqRole.setStatusCode(500);
            reqRole.setError(e.getMessage());
        }
        return reqRole;
    }

    public ReqRole updateRole(Integer roleId, Role updatedRole) {

        ReqRole reqRole = new ReqRole();

        try {
            Optional<Role> roleOptional = roleRepo.findById(roleId);
            if (roleOptional.isPresent()) {
                Role existingRole = roleOptional.get();
                existingRole.setRole(updatedRole.getRole());
                existingRole.setDescription(updatedRole.getDescription());

                Role savedRole = roleRepo.save(existingRole);
                reqRole.setRoles(savedRole);
                reqRole.setStatusCode(200);
                reqRole.setMessage("Role updated successfully");
            } else {
                reqRole.setStatusCode(404);
                reqRole.setMessage("Role not found for update");
            }
        } catch (Exception e) {
            reqRole.setStatusCode(500);
            reqRole.setMessage("Error occurred while updating Role: " + e.getMessage());
        }
        return reqRole;
    }

    public ReqRole getAllRoles() {
        ReqRole reqRole = new ReqRole();
        try {
            List<Role> result = roleRepo.findAll();
            if (!result.isEmpty()) {
                reqRole.setRolesList(result);
                reqRole.setStatusCode(200);
                reqRole.setMessage("Successfully loaded roles");
            } else {
                reqRole.setStatusCode(404);
                reqRole.setMessage("Roles not found");

            }
            return reqRole;

        } catch (Exception e) {
            reqRole.setStatusCode(500);
            reqRole.setMessage("Error occurred: " + e.getMessage());
            return reqRole;
        }


    }

    public ReqRole getRolesById(Integer id) {
        ReqRole reqRole = new ReqRole();
        try {
            Role roleById = roleRepo.findById(id).orElseThrow(() -> new RuntimeException("Role Not found"));
            reqRole.setRoles(roleById);
            reqRole.setStatusCode(200);
            reqRole.setMessage("Role with id '" + id + "' found successfully");
        } catch (Exception e) {
            reqRole.setStatusCode(500);
            reqRole.setMessage("Error occurred: " + e.getMessage());
        }
        return reqRole;
    }

//    public Role RoleById(Integer roleId) {
//        Optional<Role> optionalRole = roleRepo.findById(roleId);
//        return optionalRole.orElse(null); // Or throw an exception if not found
//    }

    public ReqRole deleteRole(Integer roleId) {
        ReqRole reqRole = new ReqRole();
        try {
            Optional<Role> roleOptional = roleRepo.findById(roleId);
            if (roleOptional.isPresent()) {
                roleRepo.deleteById(roleId);
                reqRole.setStatusCode(200);
                reqRole.setMessage("Role deleted successfully");
            } else {
                reqRole.setStatusCode(404);
                reqRole.setMessage("Role not found for deletion");
            }
        } catch (Exception e) {
            reqRole.setStatusCode(500);
            reqRole.setMessage("Error occurred while deleting Role: " + e.getMessage());
        }
        return reqRole;
    }
}
