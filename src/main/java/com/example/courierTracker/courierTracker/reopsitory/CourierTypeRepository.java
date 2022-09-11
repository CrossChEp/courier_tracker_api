package com.example.courierTracker.courierTracker.reopsitory;

import com.example.courierTracker.courierTracker.entity.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierTypeRepository extends JpaRepository<UserTypeEntity, Long> {
    UserTypeEntity findByType(String name);
}
