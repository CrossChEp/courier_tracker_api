package com.example.courierTracker.courierTracker.model.UserModels;

public class AdminDataModel {
    private long userId;
    private String roleName;

    public AdminDataModel() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
