package com.example.courierTracker.courierTracker.model;

import com.example.courierTracker.courierTracker.entity.UserEntity;

public class UserRegisterModel {
    private String username;
    private String email;
    private String password;

    public static UserRegisterModel toModel(UserEntity user) {
        UserRegisterModel model = new UserRegisterModel();
        model.setUsername(user.getUsername());
        model.setEmail(user.getEmail());
        model.setPassword(user.getPassword());
        return model;
    }

    public UserRegisterModel() {
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
}
