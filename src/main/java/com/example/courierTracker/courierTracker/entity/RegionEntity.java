package com.example.courierTracker.courierTracker.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "regions")
@Entity
public class RegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany(mappedBy = "regions")
    @JsonIgnore
    private List<UserEntity> couriers;

    @OneToMany(mappedBy = "region")
    private List<OrderEntity> orders;

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

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
