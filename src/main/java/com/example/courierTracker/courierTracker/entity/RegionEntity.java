package com.example.courierTracker.courierTracker.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany(mappedBy = "regions")
    private List<UserEntity> couriers;

    public RegionEntity() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<UserEntity> couriers) {
        this.couriers = couriers;
    }
}
