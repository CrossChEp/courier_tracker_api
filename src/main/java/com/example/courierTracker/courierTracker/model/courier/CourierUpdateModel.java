package com.example.courierTracker.courierTracker.model.courier;

import java.util.List;

public class CourierUpdateModel {
    private long courierId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private long typeId;
    private List<Long> regions;

    public CourierUpdateModel() {
    }

    public long getCourierId() {
        return courierId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public List<Long> getRegions() {
        return regions;
    }

    public void setRegions(List<Long> regions) {
        this.regions = regions;
    }
}
