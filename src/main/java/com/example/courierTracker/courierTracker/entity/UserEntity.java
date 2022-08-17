package com.example.courierTracker.courierTracker.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_entity_id")
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "user_type_entity_id")
    private UserTypeEntity userType;

    @ManyToMany
    @JoinTable(
            name = "couirier_regions",
            joinColumns = @JoinColumn(name = "user_entity_id"),
            inverseJoinColumns = @JoinColumn(name = "region_entity_id")
    )
    private List<RegionEntity> regions = new ArrayList<>();

    public UserEntity() {
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

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public UserTypeEntity getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEntity userType) {
        this.userType = userType;
    }

    public List<RegionEntity> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionEntity> regions) {
        this.regions = regions;
    }
}
