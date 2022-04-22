package com.netcracker.repository;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.car.CarClient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarClientRepository extends CrudRepository<CarClient, UUID> {

 Optional<CarClient> getAllById(UUID id);

 List<CarClient> getAllByClientLogin(String login);

 Optional<CarClient> getCarClientByMetadataCar(String str);

 boolean save(CarClientDto c);

 Optional<CarClient> getById(UUID uuid);

 Optional<CarClient> getAllByIdAndClient_Login(UUID uuid,String login);


}
