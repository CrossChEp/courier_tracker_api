package com.example.courierTracker.courierTracker.model.region;

import com.example.courierTracker.courierTracker.entity.RegionEntity;

public class RegionGetModel {
    private long id;
    private String name;

    public RegionGetModel() {
    }

    public static RegionGetModel toModel(RegionEntity region) {
        RegionGetModel model = new RegionGetModel();
        model.setId(region.getId());
        model.setName(region.getName());
        return model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
