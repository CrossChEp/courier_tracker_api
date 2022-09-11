package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.config.Roles;
import com.example.courierTracker.courierTracker.entity.RegionEntity;
import com.example.courierTracker.courierTracker.exception.RegionAlreadyExistsException;
import com.example.courierTracker.courierTracker.reopsitory.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepo;
    @Autowired
    private RoleService roleService;

    public void addRegion(String name) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        RegionEntity region = regionRepo.findByName(name);
        if(region != null) {
            throw new RegionAlreadyExistsException("region with such name already exists");
        }
        region = new RegionEntity();
        region.setName(name);
        regionRepo.save(region);
    }
}
