package com.example.courierTracker.courierTracker.model.order;

import com.example.courierTracker.courierTracker.entity.OrderEntity;

public class OrderGetModel extends OrderAddModel {
    private long id;

    public OrderGetModel() {
    }
    public static OrderGetModel toModel(OrderEntity order) {
        OrderGetModel model = new OrderGetModel();
        model.setId(order.getId());
        model.setWeight(order.getWeight());
        model.setRegion(order.getRegion().getId());
        model.setDeliveryHours(order.getDeliveryHours());
        return model;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
