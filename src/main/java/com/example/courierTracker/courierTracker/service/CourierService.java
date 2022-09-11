package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.config.Roles;
import com.example.courierTracker.courierTracker.entity.RegionEntity;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.entity.UserTypeEntity;
import com.example.courierTracker.courierTracker.exception.alreadyExistsException.CourierTypeAlreadyExists;
import com.example.courierTracker.courierTracker.model.UserModels.DefineCourierModel;
import com.example.courierTracker.courierTracker.model.courierTypeModels.AddCourierTypeModel;
import com.example.courierTracker.courierTracker.reopsitory.CourierTypeRepository;
import com.example.courierTracker.courierTracker.reopsitory.RegionRepository;
import com.example.courierTracker.courierTracker.reopsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourierService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CourierTypeRepository courierTypeRepo;
    @Autowired
    private RegionRepository regionRepo;
    @Autowired
    private RoleService roleService;

    public void addCourier(DefineCourierModel courierData) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        UserEntity user = userRepo.findById(courierData.getId()).orElseThrow();
        UserTypeEntity courierType = courierTypeRepo.findById(courierData.getTypeId()).orElseThrow();
        RegionEntity region = regionRepo.findById(courierData.getRegionId()).orElseThrow();
        user.addRegion(region);
        user.setUserType(courierType);
        userRepo.save(user);
    }

    public void addCourierType(AddCourierTypeModel courierTypeData) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        UserTypeEntity courierType = courierTypeRepo.findByType(courierTypeData.getTypeName());
        if(courierType != null) {
            throw new CourierTypeAlreadyExists("courier type with such name already exists");
        }
        courierType = new UserTypeEntity();
        courierType.setType(courierTypeData.getTypeName());
        courierType.setCapacity(courierTypeData.getCourierCapacity());
        courierTypeRepo.save(courierType);
    }
}
