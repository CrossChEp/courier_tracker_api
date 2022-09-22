package com.example.courierTracker.courierTracker.model.user;

import java.util.List;

import com.example.courierTracker.courierTracker.entity.RoleEntity;
import com.example.courierTracker.courierTracker.entity.UserEntity;
import com.example.courierTracker.courierTracker.entity.UserTypeEntity;
import com.example.courierTracker.courierTracker.model.order.OrderGetModel;
import com.example.courierTracker.courierTracker.model.region.RegionGetModel;
import com.example.courierTracker.courierTracker.service.CourierService;
import com.example.courierTracker.courierTracker.service.RegionService;
import org.springframework.ui.ModelMap;

public class UserGetModel {
	private long id;
	private String username;
	private String name;
	private String surname;
	private String email;
	private List<RegionGetModel> regions;
	private UserTypeEntity type;
	private List<RoleEntity> roles;
	private OrderGetModel currentOrder;
	
	public UserGetModel() {
		
	}

	public static UserGetModel toModel(UserEntity user) {
		UserGetModel model = new UserGetModel();
		model.setUsername(user.getUsername());
		model.setName(user.getName());
		model.setSurname(user.getSurname());
		model.setEmail(user.getEmail());
		model.setRegions(CourierService.generateRegionGetModelList(user.getRegions()));
		model.setType(user.getUserType());
		model.setId(user.getId());
		model.setRoles(user.getRoles());
		if(user.getCurrentOrder() != null) {
			model.setCurrentOrder(OrderGetModel.toModel(user.getCurrentOrder()));
		}
		return model;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OrderGetModel getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(OrderGetModel currentOrder) {
		this.currentOrder = currentOrder;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RegionGetModel> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionGetModel> regions) {
		this.regions = regions;
	}

	public UserTypeEntity getType() {
		return type;
	}

	public void setType(UserTypeEntity type) {
		this.type = type;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
}
