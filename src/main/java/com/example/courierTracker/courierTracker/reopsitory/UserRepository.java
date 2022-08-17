package com.example.courierTracker.courierTracker.reopsitory;

import com.example.courierTracker.courierTracker.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
