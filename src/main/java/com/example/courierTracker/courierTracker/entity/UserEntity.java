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
}
