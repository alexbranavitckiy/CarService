package com.netcracker.repository;


import com.netcracker.breakdown.CarBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarBreakdownRepository extends JpaRepository<CarBreakdown, UUID> {
}
