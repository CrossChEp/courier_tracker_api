package com.example.courierTracker.courierTracker.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserTypeEntity {
    @Id
    @GeneratedValue
    private long id;
    private String type;

    @OneToMany(mappedBy = "user_type_entity", cascade = CascadeType.ALL)
    private List<UserEntity> users = new ArrayList<>();

    public UserTypeEntity() {
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
