package com.example.courierTracker.courierTracker.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "users", schema = "public")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    @Column(nullable = true)
    private Long finishOrder;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<RoleEntity> roles;

    @ManyToOne
    @JoinColumn(name = "user_type_id")

    private UserTypeEntity userType;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_regions",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "regions_id")
    )

    private List<RegionEntity> regions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private OrderEntity currentOrder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "timetables_id")
    private Timetable timetable;

    private Double rate;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getFinishOrder() {
        return finishOrder;
    }

    public void setFinishOrder(Long finishOrder) {
        this.finishOrder = finishOrder;
    }

    public void incrementFinishOrder() {
        if(finishOrder == null) {
            finishOrder = 0L;
        }
        finishOrder++;
    }

    public UserEntity() {
        if(finishOrder == null) {
            finishOrder = 0L;
        }
    }


    public OrderEntity getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(OrderEntity currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void addRegion(RegionEntity region) {
        regions.add(region);
    }

    public void addRoleToUserRoles(RoleEntity role) {
        roles.add(role);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @JsonIgnore
    public UserTypeEntity getUserType() {
        return userType;
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

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public void setUserType(UserTypeEntity userType) {
        this.userType = userType;
    }
    @JsonIgnore

    public List<RegionEntity> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionEntity> regions) {
        this.regions = regions;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }

    public Timetable getTimetable() {
        return timetable;
    }

}
