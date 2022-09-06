package com.example.courierTracker.courierTracker.reopsitory;

import com.example.courierTracker.courierTracker.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
