package com.example.courierTracker.courierTracker.model.order;

import com.example.courierTracker.courierTracker.entity.OrderEntity;

import java.util.List;

public class OrderAddModel {
    private double weight;
    private long region;
    private List<String> deliveryHours;

    public OrderAddModel() {
    }

    public static OrderAddModel toModel(OrderEntity order) {
        OrderAddModel model = new OrderAddModel();
        model.setWeight(order.getWeight());
        model.setRegion(order.getRegion().getId());
        model.setDeliveryHours(order.getDeliveryHours());
        return model;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public long getRegion() {
        return region;
    }

    public void setRegion(long region) {
        this.region = region;
    }

    public List<String> getDeliveryHours() {
        return deliveryHours;
    }

    public void setDeliveryHours(List<String> deliveryHours) {
        this.deliveryHours = deliveryHours;
    }
}
