package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.config.Roles;
import com.example.courierTracker.courierTracker.entity.RoleEntity;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.exception.RoleAlreadyExists;
import com.example.courierTracker.courierTracker.exception.UserAlreadyExistsException;
import com.example.courierTracker.courierTracker.exception.UserHasNoPermission;
import com.example.courierTracker.courierTracker.model.AddRoleModel;
import com.example.courierTracker.courierTracker.reopsitory.RoleRepository;
import com.example.courierTracker.courierTracker.reopsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserService userService;

    public void addRoleToDatabase(AddRoleModel roleData) throws RoleAlreadyExists {
        checkUserRoleOrElseThrow(Roles.ADMIN);
        RoleEntity role = roleRepo.findByName(roleData.getName());
        if(role != null) {
            throw new RoleAlreadyExists("role with such name already exists");
        }
        role = new RoleEntity();
        role.setName(roleData.getName());
        roleRepo.save(role);
    }

    public void checkUserRoleOrElseThrow(String roleName) {
        if(!isUserPrincipalContainsRole(roleName)) {
            throw new UserHasNoPermission("this function available only for admin users");
        }
    }

    public boolean isUserPrincipalContainsRole(String roleName) {
        UserEntity user = userService.getCurrentUser();
        return user.getRoles().stream().anyMatch(x -> x.getName().equals(roleName));
    }
}
