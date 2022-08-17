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
}
