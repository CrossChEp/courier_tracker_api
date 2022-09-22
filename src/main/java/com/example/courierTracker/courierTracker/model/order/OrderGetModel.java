package com.example.courierTracker.courierTracker.model.order;

import com.example.courierTracker.courierTracker.model.region.RegionGetModel;

import java.util.List;

public class OrderGetModel {
    private long id;
    private RegionGetModel region;
    private double weight;
    private List<String> deliveryHours;

    public OrderGetModel() {
    }

    public long getId() {
        return id;
    }

    public RegionGetModel getRegion() {
        return region;
    }

    public void setRegion(RegionGetModel region) {
        this.region = region;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<String> getDeliveryHours() {
        return deliveryHours;
    }

    public void setDeliveryHours(List<String> deliveryHours) {
        this.deliveryHours = deliveryHours;
    }
}
