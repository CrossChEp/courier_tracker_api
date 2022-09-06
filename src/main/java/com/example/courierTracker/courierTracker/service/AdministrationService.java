package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.entity.RoleEntity;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.model.AdminDataModel;
import com.example.courierTracker.courierTracker.reopsitory.RoleRepository;
import com.example.courierTracker.courierTracker.reopsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrationService {

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserRepository userRepo;

    public void provideAdmin(AdminDataModel userData) {
        UserEntity user = userRepo.findById(userData.getUserId()).orElseThrow();
        RoleEntity role = roleRepo.findByName(userData.getRoleName());
        user.addRoleToUserRoles(role);
        userRepo.save(user);
    }
}
