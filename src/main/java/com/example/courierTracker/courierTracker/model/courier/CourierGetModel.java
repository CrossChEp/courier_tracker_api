package com.example.courierTracker.courierTracker.model.courier;

import com.example.courierTracker.courierTracker.entity.RegionEntity;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.entity.UserTypeEntity;
import com.example.courierTracker.courierTracker.model.order.OrderGetModel;
import com.example.courierTracker.courierTracker.model.region.RegionGetModel;
import com.example.courierTracker.courierTracker.model.user.UserGetModel;
import com.example.courierTracker.courierTracker.service.CourierService;
import org.modelmapper.ModelMapper;

import java.util.List;

public class CourierGetModel extends UserGetModel {
    public CourierGetModel() {
    }

    public static CourierGetModel toModel(UserEntity user) {
        CourierGetModel model = new CourierGetModel();
        model.setUsername(user.getUsername());
        model.setName(user.getName());
        model.setSurname(user.getSurname());
        model.setEmail(user.getEmail());
        model.setRegions(CourierService.generateRegionGetModelList(user.getRegions()));
        model.setType(user.getUserType());
        model.setId(user.getId());
        model.setRoles(user.getRoles());
        model.setTimetable(TimeTableAddModel.toModel(user.getTimetable()));
        if(user.getCurrentOrder() != null) {
            model.setCurrentOrder(OrderGetModel.toModel(user.getCurrentOrder()));
        }
        model.setFinishedOrders(user.getFinishOrder());
        if (user.getFinishOrder() != null) {
            model.setFinishedOrders(user.getFinishOrder());
        }
        return model;
    }
}
