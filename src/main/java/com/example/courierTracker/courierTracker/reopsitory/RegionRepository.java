package com.example.courierTracker.courierTracker.reopsitory;

import com.example.courierTracker.courierTracker.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
    RegionEntity findByName(String name);
}
