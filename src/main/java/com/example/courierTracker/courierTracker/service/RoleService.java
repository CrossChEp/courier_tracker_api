package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.entity.RoleEntity;
import com.example.courierTracker.courierTracker.exception.RoleAlreadyExists;
import com.example.courierTracker.courierTracker.model.AddRoleModel;
import com.example.courierTracker.courierTracker.reopsitory.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;

    public void addRoleToDatabase(AddRoleModel roleData) throws RoleAlreadyExists {
        RoleEntity role = roleRepo.findByName(roleData.getName());
        if(role != null) {
            throw new RoleAlreadyExists("role with such name already exists");
        }
        role = new RoleEntity();
        role.setName(roleData.getName());
        roleRepo.save(role);
    }
}
