package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.entity.OrderEntity;
import com.example.courierTracker.courierTracker.entity.RegionEntity;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.exception.Others.IncorrectFormat;
import com.example.courierTracker.courierTracker.exception.Others.UserAlreadyHasOrder;
import com.example.courierTracker.courierTracker.exception.Others.UserHasNoPermission;
import com.example.courierTracker.courierTracker.model.courier.TimeTableAddModel;
import com.example.courierTracker.courierTracker.model.order.OrderAddModel;
import com.example.courierTracker.courierTracker.model.order.OrderGetModel;
import com.example.courierTracker.courierTracker.reopsitory.OrderRepository;
import com.example.courierTracker.courierTracker.reopsitory.RegionRepository;
import com.example.courierTracker.courierTracker.reopsitory.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private RegionRepository regionRepo;
    @Autowired
    private CourierService courierService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

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
        order.setWeight(orderData.getWeight());
        return order;
    }

    public List<OrderGetModel> getOrders() throws ParseException {
        List<OrderEntity> orders = orderRepo.findAll();
        return filterOrdersByCourierData(orders);
    }

    private List<OrderGetModel> filterOrdersByCourierData(List<OrderEntity> orders) throws ParseException {
        UserEntity courier = userService.getCurrentUser();
        String time = getTimeFromTimetable(courier);
        List<OrderGetModel> orderModels = new ArrayList<>();
        for(var order: orders) {
            if(isHourValid(order.getDeliveryHours(), time.split("-")) && isWeightValid(courier, order)
            && courier.getRegions().contains(order.getRegion()) && order.getUser() == null){
                orderModels.add(OrderGetModel.toModel(order));
            }
        }
        return orderModels;
    }

    private String getTimeFromTimetable(UserEntity courier) {
        String dayOfWeek = LocalDate.now().getDayOfWeek().toString().toLowerCase();
        TimeTableAddModel timetable = TimeTableAddModel.toModel(courier.getTimetable());
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(timetable, Map.class);
        return (String) map.get(dayOfWeek);
    }

    private boolean isHourValid(List<String> times, String[] compareTime) throws ParseException {
        for(var time: times) {
            boolean firstPartOfTime = LocalTime.parse(time.split("-")[0]).isAfter(
                    LocalTime.parse(compareTime[0]));
            boolean secondPartOfTime = LocalTime.parse(time.split("-")[1]).isBefore(
                    LocalTime.parse(compareTime[1]));
            if(firstPartOfTime || secondPartOfTime) {
                return true;
            }
        }
        return false;
    }

    private boolean isWeightValid(UserEntity courier, OrderEntity order) {
        return order.getWeight() <= courier.getUserType().getCapacity();
    }

    public void acceptOrder(long orderId) {
        UserEntity courier = userService.getCurrentUser();
        OrderEntity order = orderRepo.findById(orderId).orElseThrow();
        if(courier.getCurrentOrder() != null) {
            throw new UserAlreadyHasOrder("user can't accept more than 1 order");
        }
        courier.setCurrentOrder(order);
        userRepo.save(courier);
    }

    public void finishOrder() {
        UserEntity courier = userService.getCurrentUser();
        if(courier.getCurrentOrder() == null) {
            throw new UserHasNoPermission("user hasn't any orders");
        }
        long id = courier.getCurrentOrder().getId();
        courier.setCurrentOrder(null);
        deleteOrder(id);
        courier.incrementFinishOrder();
        userRepo.save(courier);
    }

    private void deleteOrder(long id) {
        OrderEntity order = orderRepo.findById(id).orElseThrow();
        order.setUser(null);
        orderRepo.delete(order);
        orderRepo.save(order);
    }
}
