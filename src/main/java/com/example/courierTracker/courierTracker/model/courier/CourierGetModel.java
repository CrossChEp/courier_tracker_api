package com.example.courierTracker.courierTracker.model.courier;

import com.example.courierTracker.courierTracker.entity.RegionEntity;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.entity.UserTypeEntity;

import java.util.List;

public class CourierGetModel {
    private long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private UserTypeEntity type;
    private List<RegionEntity> regions;

    public CourierGetModel() {
    }

    public static CourierGetModel toModel(UserEntity user) {
        CourierGetModel model = new CourierGetModel();
        model.setUsername(user.getUsername());
        model.setId(user.getId());
        model.setType(user.getUserType());
        model.setRegion(user.getRegions());
        return model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserTypeEntity getType() {
        return type;
    }

    public void setType(UserTypeEntity type) {
        this.type = type;
    }

    public List<RegionEntity> getRegion() {
        return regions;
    }

    public void setRegion(List<RegionEntity> region) {
        this.regions = region;
    }
}
