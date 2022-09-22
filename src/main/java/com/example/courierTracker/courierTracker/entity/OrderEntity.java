package com.example.courierTracker.courierTracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double weight;
    @ElementCollection
    private List<String> deliveryHours;
    @ManyToOne
    @JoinColumn(name = "regions_id")
    private RegionEntity region;

    public OrderEntity() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public List<String> getDeliveryHours() {
        return deliveryHours;
    }

    public void setDeliveryHours(List<String> deliveryHours) {
        this.deliveryHours = deliveryHours;
    }

    @JsonIgnore
    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }
}
