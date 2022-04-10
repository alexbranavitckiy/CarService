package com.netcracker.repository;

import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.car.CarClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarClientRepository extends CrudRepository<CarClient, UUID> {

 Optional<CarClient> getAllById(UUID id);


}
