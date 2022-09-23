package com.example.courierTracker.courierTracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double weight;
    @ElementCollection
    private List<String> deliveryHours;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalTime orderExecutionTimeInSeconds;
    private String state;
    @ManyToOne
    @JoinColumn(name = "regions_id")
    private RegionEntity region;

    @OneToOne(mappedBy = "currentOrder")
    private UserEntity user;

    public OrderEntity() {
    }

    public LocalTime getOrderExecutionTimeInSeconds() {
        return orderExecutionTimeInSeconds;
    }

    public void setOrderExecutionTimeInSeconds(LocalTime orderExecutionTimeInSeconds) {
        this.orderExecutionTimeInSeconds = orderExecutionTimeInSeconds;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getDeliveryHours() {
        return deliveryHours;
    }

    public void setDeliveryHours(List<String> deliveryHours) {
        this.deliveryHours = deliveryHours;
    }

    @JsonIgnore
    public RegionEntity getRegion() {
        return region;
    }

    public void setRegion(RegionEntity region) {
        this.region = region;
    }
}
