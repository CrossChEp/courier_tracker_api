package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.entity.OrderEntity;
import com.example.courierTracker.courierTracker.entity.RegionEntity;
import com.example.courierTracker.courierTracker.entity.Timetable;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.exception.IncorrectFormat;
import com.example.courierTracker.courierTracker.model.courier.TimeTableAddModel;
import com.example.courierTracker.courierTracker.model.order.OrderAddModel;
import com.example.courierTracker.courierTracker.model.order.OrderGetModel;
import com.example.courierTracker.courierTracker.reopsitory.OrderRepository;
import com.example.courierTracker.courierTracker.reopsitory.RegionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public List<OrderAddModel> getOrders() throws ParseException {
        List<OrderEntity> orders = orderRepo.findAll();
        return filterOrdersByCourierData(orders);
    }

    private List<OrderAddModel> filterOrdersByCourierData(List<OrderEntity> orders) throws ParseException {
        UserEntity courier = userService.getCurrentUser();
        String time = getTimeFromTimetable(courier);
        List<OrderAddModel> orderModels = new ArrayList<>();
        for(var order: orders) {
            if(isHourValid(order.getDeliveryHours(), time.split("-"))){
                orderModels.add(OrderAddModel.toModel(order));
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
}
