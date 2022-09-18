package com.example.courierTracker.courierTracker.reopsitory;

import com.example.courierTracker.courierTracker.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
