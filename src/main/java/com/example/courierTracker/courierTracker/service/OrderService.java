package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.entity.OrderEntity;
import com.example.courierTracker.courierTracker.entity.RegionEntity;
import com.example.courierTracker.courierTracker.exception.IncorrectFormat;
import com.example.courierTracker.courierTracker.model.order.OrderAddModel;
import com.example.courierTracker.courierTracker.reopsitory.OrderRepository;
import com.example.courierTracker.courierTracker.reopsitory.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private RegionRepository regionRepo;
    @Autowired
    private CourierService courierService;

    public void addOrder(OrderAddModel orderData) {
        for(var time: orderData.getDeliveryHours()) {
            if(!courierService.checkTime(time)) {
                throw new IncorrectFormat("incorrect type of time");
            }
        }
        OrderEntity order = collectOrder(orderData);
        orderRepo.save(order);
    }

    private OrderEntity collectOrder(OrderAddModel orderData) {
        OrderEntity order = new OrderEntity();
        RegionEntity region = regionRepo.findById(orderData.getRegion()).orElseThrow();
        order.setRegion(region);
        order.setDeliveryHours(orderData.getDeliveryHours());
        order.setWeight(order.getWeight());
        return order;
    }
}
