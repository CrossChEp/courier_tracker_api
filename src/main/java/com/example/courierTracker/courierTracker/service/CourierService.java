package com.example.courierTracker.courierTracker.service;

import com.example.courierTracker.courierTracker.config.Roles;
import com.example.courierTracker.courierTracker.entity.*;
import com.example.courierTracker.courierTracker.exception.Others.CourierHasNoSuchRole;
import com.example.courierTracker.courierTracker.exception.Others.IncorrectFormat;
import com.example.courierTracker.courierTracker.exception.alreadyExistsException.CourierTypeAlreadyExists;
import com.example.courierTracker.courierTracker.model.courier.*;
import com.example.courierTracker.courierTracker.model.region.RegionGetModel;
import com.example.courierTracker.courierTracker.reopsitory.CourierTypeRepository;
import com.example.courierTracker.courierTracker.reopsitory.RegionRepository;
import com.example.courierTracker.courierTracker.reopsitory.TimetableRepository;
import com.example.courierTracker.courierTracker.reopsitory.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CourierService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CourierTypeRepository courierTypeRepo;
    @Autowired
    private RegionRepository regionRepo;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Autowired
    private TimetableRepository timetableRepo;

    public void addCourier(DefineCourierModel courierData) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        UserEntity user = userRepo.findById(courierData.getId()).orElseThrow();
        UserTypeEntity courierType = courierTypeRepo.findById(courierData.getTypeId()).orElseThrow();
        RegionEntity region = regionRepo.findById(courierData.getRegionId()).orElseThrow();
        RoleEntity courier = roleService.getRoleByName(Roles.COURIER);
        if(!checkTimetableAccuracy(courierData)) {
            throw new IncorrectFormat("the format of timetable is incorrect");
        }
        user.addRegion(region);
        user.setUserType(courierType);
        user.addRoleToUserRoles(courier);
        addTimeTableToUser(user, courierData.getTimeTable());
        userRepo.save(user);
    }

    private boolean checkTimetableAccuracy(DefineCourierModel courierData) {
        Map<String, String> timeTableDict = convertTimeTableClassToHashMap(courierData.getTimeTable());
        for(var day: timeTableDict.keySet()) {
            String time = timeTableDict.get(day);
            if(time == null) {
                continue;
            }
            if(!checkTime(time)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkTime(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("k:m-k:m");
        try {
            dateFormat.parse(time.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    private void addTimeTableToUser(UserEntity user, TimeTableAddModel timeTableData) {
        Timetable timetable = setTimetable(timeTableData);
        timetableRepo.save(timetable);
        user.setTimetable(timetable);
        userRepo.save(user);
    }

    private Timetable setTimetable(TimeTableAddModel timeTableData) {
        Timetable timetable = new Timetable();
        timetable.setMonday(timeTableData.getMonday());
        timetable.setTuesday(timeTableData.getTuesday());
        timetable.setWednesday(timeTableData.getWednesday());
        timetable.setThursday(timeTableData.getThursday());
        timetable.setFriday(timeTableData.getFriday());
        timetable.setSaturday(timeTableData.getSaturday());
        timetable.setSunday(timeTableData.getSunday());
        return timetable;
    }

    private Map<String, String> convertTimeTableClassToHashMap(TimeTableAddModel timeTableData) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(timeTableData, Map.class);
    }

    public void addCourierType(AddCourierTypeModel courierTypeData) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        UserTypeEntity courierType = courierTypeRepo.findByType(courierTypeData.getTypeName());
        if(courierType != null) {
            throw new CourierTypeAlreadyExists("courier type with such name already exists");
        }
        courierType = new UserTypeEntity();
        courierType.setType(courierTypeData.getTypeName());
        courierType.setCapacity(courierTypeData.getCourierCapacity());
        courierTypeRepo.save(courierType);
    }

    public void updateCourier(CourierUpdateModel newCourierData) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        if(newCourierData.getPassword() != null) {
            newCourierData.setPassword(userService.hashPassword(newCourierData.getPassword()));
        }
        UserEntity courier = userRepo.findById(newCourierData.getCourierId()).orElseThrow();
        addRegionsToUserIfNotNull(courier, newCourierData);
        modelMapper.map(newCourierData, courier);
        userRepo.save(courier);
    }

    private void addRegionsToUserIfNotNull(UserEntity user, CourierUpdateModel newCourierData) {
        if(newCourierData.getRegions() != null) {
            addRegionsToUser(user, newCourierData);
        }
    }

    private void addRegionsToUser(UserEntity user, CourierUpdateModel newCourierData) {
        for(var regionId: newCourierData.getRegions()) {
            RegionEntity region = regionRepo.findById(regionId).orElseThrow();
            if(!user.getRegions().contains(region)) {
                user.addRegion(region);
            }
        }
    }

    public void deleteCourier(long id) {
        roleService.checkUserRoleOrElseThrow(Roles.ADMIN);
        UserEntity courier = userRepo.findById(id).orElseThrow();
        RoleEntity role = roleService.getRoleByName(Roles.COURIER);
        if(!courier.getRoles().contains(role)) {
            throw new CourierHasNoSuchRole("user has no role 'courier'");
        }
        courier.getRoles().remove(role);
        userRepo.save(courier);
    }

    public CourierGetModel getCourier(long id) {
        RoleEntity courierRole = roleService.getRoleByName(Roles.COURIER);
        UserEntity courier = userRepo.findById(id).orElseThrow();
        if(!courier.getRoles().contains(courierRole)) {
            throw new CourierHasNoSuchRole("user has no role courier");
        }
        return CourierGetModel.toModel(courier);
    }

    public List<CourierGetModel> getCouriers() {
        RoleEntity courier = roleService.getRoleByName(Roles.COURIER);
        List<UserEntity> users = userRepo.findAll().stream()
                .filter(x -> x.getRoles().contains(courier)).toList();
        return generateCourierGetModelList(users);
    }

    private List<CourierGetModel> generateCourierGetModelList(List<UserEntity> couriers) {
        List<CourierGetModel> courierModels = new ArrayList<>();
        for(var courier: couriers) {
            courierModels.add(CourierGetModel.toModel(courier));
        }
        return courierModels;
    }
    public static List<RegionGetModel> generateRegionGetModelList(List<RegionEntity> regions) {
        List<RegionGetModel> regionModels = new ArrayList<>();
        for(var region: regions) {
            regionModels.add(RegionGetModel.toModel(region));
        }
        return regionModels;
    }
}
