package com.netcracker.repository;

import com.netcracker.car.CarClient;
import com.netcracker.user.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarClientRepository extends PagingAndSortingRepository<CarClient, UUID> {

 Optional<CarClient> getAllById(UUID id);

 boolean existsById(UUID uuid);

 Page<CarClient> getAllByClientLogin(String login, Pageable nextPage);

 Optional<CarClient> getById(UUID uuid);

 Optional<CarClient> getAllByIdAndClient_Login(UUID uuid, String login);

 boolean existsByMetadataCar(String m);

 @Query(value = "SELECT * FROM car_client  WHERE (description like :searchCar) OR (metadata_car like :searchCar)" +
  " OR (summary like :searchCar)", nativeQuery = true)
 List<CarClient> getAllByLike(@Param("searchCar") String searchCar);

 Page<CarClient> getAllByDescriptionLike(String search, Pageable nextPage);

 List<CarClient> getAllBy();

 @Transactional
 @Modifying
 @Query(value = "UPDATE public.car_client   SET metadata_car=?1  where car_client.id = ?2 and" +
  "  car_client.id_clients in(select clients.id from clients where clients.login=?3)", nativeQuery = true)
 int updateCarClientById(String mata, UUID uuid, String login);


 @Transactional
 @Modifying
 @Query(value = "UPDATE public.car_client   SET description=?1,year=?2,run=?3  where car_client.id = ?4 and " +
  " car_client.id_clients in(select clients.id from clients where clients.login=?5)", nativeQuery = true)
 int updateCarClientByIdWithoutMachineNumber(String description, Date date, int run, UUID uuid, String login);


 @Transactional
 @Modifying
 @Query(
  value =
   "insert into car_client (id,description,year,metadata_car, run,summary,id_clients,id_mark) values" +
    " (:id,:description,:year, :metadata_car, :run, :summary, (SELECT public .clients.id " +
    "FROM public.clients where public .clients.login=:login), :id_mark)   ",
  nativeQuery = true)
 int createCarOnLogin(@Param("id") UUID id, @Param("description") String description, @Param("year") Date year,
                      @Param("metadata_car") String metadata_car, @Param("run") int run,
                      @Param("summary") String summary, @Param("login") String login, @Param("id_mark") UUID id_mark);

 @Transactional
 @Modifying
 @Query(
  value =
   "insert into car_client (id,description,year,metadata_car, run,summary,id_mark) values " +
    "(:id,:description,:year, :metadata_car, :run, :summary, :id_mark)   ",
  nativeQuery = true)
 int createCarOnMaster(@Param("id") UUID id, @Param("description") String description, @Param("year") Date year,
                       @Param("metadata_car") String metadata_car, @Param("run") int run,
                       @Param("summary") String summary, @Param("id_mark") UUID id_mark);


}
