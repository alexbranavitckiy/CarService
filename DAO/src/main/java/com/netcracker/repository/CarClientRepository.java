package com.netcracker.repository;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.car.CarClient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarClientRepository extends CrudRepository<CarClient, UUID> {

 Optional<CarClient> getAllById(UUID id);

 List<CarClient> getAllByClientLogin(String login);

 Optional<CarClient> getCarClientByMetadataCar(String str);

 Optional<CarClient> getById(UUID uuid);

 Optional<CarClient> getAllByIdAndClient_Login(UUID uuid, String login);

 @Transactional
 @Modifying
 @Query("update car_client c set c.description = ?1, c.year = ?2 , c.metadataCar = ?3, c.run = ?4, c.summary = ?5  where c.id = ?6")
 int updateCarClientById(String description, Date year, String metadata_car, int run, String summary, String id);


 @Modifying
 @Query(
  value =
   "insert into car_client (id,description,year,metadataCar, run,summary,id_clients,id_mark) values (:id,:description,:year, :metadataCar, :run, :summary, :id_clients, :id_mark)",
  nativeQuery = true)
 void insertCarClient(@Param("id") String id, @Param("description") String description, @Param("year") Date year, @Param("metadataCar") String metadataCar, @Param("run") int run,
                      @Param("summary") String summary, @Param("id_clients") String id_clients, @Param("id_mark") String id_mark);


}
