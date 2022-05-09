package com.netcracker.repository;


import com.netcracker.breakdown.CarBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarBreakdownRepository extends JpaRepository<CarBreakdown, UUID> {

 @Query(value = "SELECT * FROM public.car_breakdown  right join public.clients c  on (c.login=?1)  where  car_breakdown.id is not null  order by update_date desc", nativeQuery = true)
 List<CarBreakdown> getAllByLogin(String login);

 @Query(value = "SELECT * FROM public.car_breakdown   right join public.clients  on ( public.car_breakdown.id_car =?1 and public.clients.login=?2 and public.car_breakdown.state=?3 ) where  car_breakdown.id is not null order by update_date desc  ", nativeQuery = true)
 List<CarBreakdown> getAllByCarBreakdownByStateAndSortDesc(UUID uuidCar, String login, String state);

 @Query(value = "SELECT * FROM public.car_breakdown  right join public.clients c  on (c.login=?1 and  car_breakdown.id_car =?2 )  where  car_breakdown.id is not null order by update_date desc", nativeQuery = true)
 List<CarBreakdown> getAllByCarBreakdownByIdCarAndLoginSortDesc(String login, UUID uuidCar);

 @Query(value = "SELECT * FROM public.car_breakdown  right join public.clients  on (clients.login=?1 and  car_breakdown.id_car =?2 )  where  car_breakdown.id is not null", nativeQuery = true)
 List<CarBreakdown> getAllByCarBreakdownByIdCarAndLogin(String login, UUID uuidCar);

 List<CarBreakdown> getAllByCarClientId(UUID uuidCar);

 @Transactional
 @Modifying
 @Query(value = "update car_breakdown  set description =:description, run_car_size = :runCarSize ,update_date = :updateDate,state = :state,location = :location  where id = :idCarBreak and id_outfit in (  SELECT id FROM outfit where state_outfit='WORK' and id_master in( SELECT id FROM master where login=:login))", nativeQuery = true)
 int updateCarBreakDownByIdAndMasterLogin(@Param("description") String description, @Param("runCarSize") int runCarSize, @Param("updateDate") Date updateDate, @Param("state") String state, @Param("location") String location, @Param("idCarBreak") UUID idCarBreak, @Param("login") String login);

 @Transactional
 @Modifying
 @Query(value = "update car_breakdown  set price=:price description =:description, run_car_size = :runCarSize ,update_date = :updateDate,state = :state,location = :location  where id = :idCarBreak and id_outfit in (  SELECT id FROM outfit where state_outfit='WORK' and id_master in( SELECT id FROM master where login=:login))", nativeQuery = true)
 int updateCarBreakDownByIdAndMasterOnR(@Param("description") double price,@Param("description") String description, @Param("runCarSize") int runCarSize, @Param("updateDate") Date updateDate, @Param("state") String state, @Param("location") String location, @Param("idCarBreak") UUID idCarBreak, @Param("login") String login);


 @Transactional
 @Modifying
 @Query(value = "update car_breakdown  set  run_car_size = :runCarSize ,update_date = :updateDate where id = :idCarBreak and id_outfit in (  SELECT id FROM outfit where state_outfit='WORK' and id_master in( SELECT id FROM master where login=:login))", nativeQuery = true)
 int updateCarBreakDownByIdAndMasterLogin(@Param("runCarSize") int runCarSize, @Param("updateDate") Date updateDate, @Param("idCarBreak") UUID idCarBreak, @Param("login") String login);

 @Transactional
 @Modifying
 @Query(value = "update car_breakdown  set description =:description ,update_date = :updateDate,state = :state  where id = :idCarBreak and id_outfit in (  SELECT id FROM outfit where state_outfit='WORK' and id_master in( SELECT id FROM master where login=:login))", nativeQuery = true)
 int updateCarBreakDownByIdAndMasterLogin(@Param("description") String description, @Param("updateDate") Date updateDate, @Param("idCarBreak") UUID idCarBreak, @Param("login") String login);

 @Transactional
 @Modifying
 @Query(value = "update car_breakdown  set update_date = :updateDate,state = :state  where id = :idCarBreak and id_outfit in (  SELECT id FROM outfit where state_outfit='WORK' and id_master in( SELECT id FROM master where login=:login))", nativeQuery = true)
 int updateCarBreakDownByIdAndMasterLogin(@Param("updateDate") Date updateDate, @Param("state") String state, @Param("idCarBreak") UUID idCarBreak, @Param("login") String login);

 @Query(value = "SELECT * FROM public.car_breakdown   where car_breakdown.id_outfit   in (  SELECT id FROM public.outfit where outfit.state_outfit='WORK' and outfit.id_master in( SELECT id FROM public.master where login=:login))", nativeQuery = true)
 List<CarBreakdown> getAllByMaster(String login);

 @Query(value = "SELECT * FROM public.car_breakdown   where car_breakdown.id=:id and car_breakdown.id_outfit   in (  SELECT id FROM public.outfit where outfit.state_outfit='WORK' and outfit.id_master in( SELECT id FROM public.master where login=:login))", nativeQuery = true)
 List<CarBreakdown> getAllByIdOnMaster(String login, UUID id);


 @Transactional
 @Modifying
 @Query(
  value =
   "insert into car_breakdown (id,price,location,description,run_car_size,update_date, state,id_car,id_outfit) values (:id,:price,:location,:description, :run_car_size, :update_date, :state,(SELECT  id_car FROM public.orders where id =:id_orders),(SELECT  id_outfits FROM public.orders where id =:id_orders))",
  nativeQuery = true)
 void insertCarBreakDownOnMaster(@Param("id") UUID id, @Param("price") double price, @Param("location") String location, @Param("description") String description, @Param("run_car_size") int run_car_size,
                                 @Param("update_date") Date update_date, @Param("state") String state, @Param("id_orders") UUID id_orders);

}
