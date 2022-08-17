package com.example.courierTracker.courierTracker.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(
            mappedBy = "role_entity",
            cascade = CascadeType.ALL
    )
    private List<UserEntity> users = new ArrayList<>();

    public RoleEntity() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
