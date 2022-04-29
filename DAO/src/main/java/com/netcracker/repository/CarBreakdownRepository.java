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

 @Query(value = "SELECT car_breakdown.id, car_breakdown.description, location, run_car_size, state, update_date, id_car FROM public.car_breakdown  right join public.clients c  on (c.login=?1)  where  car_breakdown.id is not null  order by update_date desc", nativeQuery = true)
 List<CarBreakdown> getAllByLogin(String login);

 @Query(value = "SELECT car_breakdown.id, car_breakdown.description, location, run_car_size, state, update_date, id_car FROM public.car_breakdown   right join public.clients  on ( public.car_breakdown.id_car =?1 and public.clients.login=?2 and public.car_breakdown.state=?3 ) where  car_breakdown.id is not null order by update_date desc  ", nativeQuery = true)
 List<CarBreakdown> getAllByCarBreakdownByStateAndSortDesc(UUID uuidCar, String login, String state);

 @Query(value = "SELECT car_breakdown.id, car_breakdown.description, location, run_car_size, state, update_date, id_car FROM public.car_breakdown  right join public.clients c  on (c.login=?1 and  car_breakdown.id_car =?2 )  where  car_breakdown.id is not null order by update_date desc", nativeQuery = true)
 List<CarBreakdown> getAllByCarBreakdownByIdCarAndLoginSortDesc(String login, UUID uuidCar);

 @Query(value = "SELECT car_breakdown.id, car_breakdown.description, location, run_car_size, state, update_date, id_car FROM public.car_breakdown  right join public.clients  on (public.clients.login=?1 and  car_breakdown.id_car =?2 )  where  car_breakdown.id is not null", nativeQuery = true)
 List<CarBreakdown> getAllByCarBreakdownByIdCarAndLogin(String login, UUID uuidCar);

 List<CarBreakdown> getAllByCarClientId(UUID uuidCar);

 @Transactional
 @Modifying
 @Query("update car_breakdown c set c.description = ?1, c.runCarSize = ?2 , c.updateDate = ?3, c.state = ?4, c.location = ?4  where c.id = ?5")
 int updateCarBreakDownById(String description, int runCarSize, Date updateDate, String state, String location, String idCarBreak);

 @Modifying
 @Query(
  value =
   "insert into car_breakdown (id,location,description,run_car_size , update_date, state,id_car) values (:id,:location,:description, :run_car_size, :update_date, :state,:id_car)",
  nativeQuery = true)
 void insertCarBreakDown(@Param("id") String id, @Param("location") String location, @Param("description") String description, @Param("run_car_size") int run_car_size,
                         @Param("update_date") Date update_date, @Param("state") String state, @Param("id_car") String id_car);

}
