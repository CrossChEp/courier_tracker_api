package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.config.Roles;
import com.example.courierTracker.courierTracker.entity.RegionEntity;
import com.example.courierTracker.courierTracker.entity.RoleEntity;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.entity.UserTypeEntity;
import com.example.courierTracker.courierTracker.exception.CourierHasNoSuchRole;
import com.example.courierTracker.courierTracker.exception.alreadyExistsException.CourierTypeAlreadyExists;
import com.example.courierTracker.courierTracker.model.courier.CourierGetModel;
import com.example.courierTracker.courierTracker.model.courier.CourierUpdateModel;
import com.example.courierTracker.courierTracker.model.courier.DefineCourierModel;
import com.example.courierTracker.courierTracker.model.courier.AddCourierTypeModel;
import com.example.courierTracker.courierTracker.model.region.RegionGetModel;
import com.example.courierTracker.courierTracker.reopsitory.CourierTypeRepository;
import com.example.courierTracker.courierTracker.reopsitory.RegionRepository;
import com.example.courierTracker.courierTracker.reopsitory.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private UserService userService;

    public void addCourier(DefineCourierModel courierData) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        UserEntity user = userRepo.findById(courierData.getId()).orElseThrow();
        UserTypeEntity courierType = courierTypeRepo.findById(courierData.getTypeId()).orElseThrow();
        RegionEntity region = regionRepo.findById(courierData.getRegionId()).orElseThrow();
        RoleEntity courier = roleService.getRoleByName(Roles.COURIER);
        user.addRegion(region);
        user.setUserType(courierType);
        user.addRoleToUserRoles(courier);
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

    public void updateCourier(CourierUpdateModel newCourierData) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        if(newCourierData.getPassword() != null) {
            newCourierData.setPassword(userService.hashPassword(newCourierData.getPassword()));
        }
        UserEntity courier = userRepo.findById(newCourierData.getCourierId()).orElseThrow();
        addRegionsToUserIfNotNull(courier, newCourierData);
        modelMapper.map(newCourierData, courier);
        userRepo.save(courier);
    }

    private void addRegionsToUserIfNotNull(UserEntity user, CourierUpdateModel newCourierData) {
        if(newCourierData.getRegions() != null) {
            addRegionsToUser(user, newCourierData);
        }
    }

    private void addRegionsToUser(UserEntity user, CourierUpdateModel newCourierData) {
        for(var regionId: newCourierData.getRegions()) {
            RegionEntity region = regionRepo.findById(regionId).orElseThrow();
            if(!user.getRegions().contains(region)) {
                user.addRegion(region);
            }
        }
    }

    public void deleteCourier(long id) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        UserEntity courier = userRepo.findById(id).orElseThrow();
        RoleEntity role = roleService.getRoleByName(Roles.COURIER);
        if(!courier.getRoles().contains(role)) {
            throw new CourierHasNoSuchRole("user has no role 'courier'");
        }
        courier.getRoles().remove(role);
        userRepo.save(courier);
    }

    public List<CourierGetModel> getCouriers() {
        RoleEntity courier = roleService.getRoleByName(Roles.COURIER);
        List<UserEntity> users = userRepo.findAll().stream()
                .filter(x -> x.getRoles().contains(courier)).toList();
        return generateCourierGetModelList(users);
    }

    private List<CourierGetModel> generateCourierGetModelList(List<UserEntity> couriers) {
        List<CourierGetModel> courierModels = new ArrayList<>();
        for(var courier: couriers) {
            courierModels.add(CourierGetModel.toModel(courier));
        }
        return courierModels;
    }
    public static List<RegionGetModel> generateRegionGetModelList(List<RegionEntity> regions) {
        List<RegionGetModel> regionModels = new ArrayList<>();
        for(var region: regions) {
            regionModels.add(RegionGetModel.toModel(region));
        }
        return regionModels;
    }
}
