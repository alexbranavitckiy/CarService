package com.netcracker.repository;


import com.netcracker.breakdown.CarBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarBreakdownRepository extends JpaRepository<CarBreakdown, UUID> {

 @Query(value = "SELECT car_breakdown.id, car_breakdown.description, location, run_car_size, state, update_date, id_car FROM public.car_breakdown  right join public.clients c  on (c.login=?1)  order by update_date desc", nativeQuery = true)
 List<CarBreakdown> getAllByLogin(String login);

 @Query(value = "SELECT car_breakdown.id, car_breakdown.description, location, run_car_size, state, update_date, id_car FROM public.car_breakdown   left join public.clients  on ( public.car_breakdown.id_car =?1 and public.clients.login=?2 and public.car_breakdown.state=?3 ) order by update_date desc", nativeQuery = true)
 List<CarBreakdown> getAllByCarBreakdownByStateAndSortDesc(UUID uuidCar,String login,  String state);

 @Query(value = "SELECT car_breakdown.id, car_breakdown.description, location, run_car_size, state, update_date, id_car FROM public.car_breakdown  left join public.clients c  on (c.login=?1 and  car_breakdown.id_car =?2 ) order by update_date desc", nativeQuery = true)
 List<CarBreakdown> getAllByCarBreakdownByIdCarAndLoginSortDesc(String login, UUID uuidCar);

 @Query(value = "SELECT car_breakdown.id, car_breakdown.description, location, run_car_size, state, update_date, id_car FROM public.car_breakdown  left join public.clients  on (public.clients.login=?1 and  car_breakdown.id_car =?2 )", nativeQuery = true)
 List<CarBreakdown> getAllByCarBreakdownByIdCarAndLogin(String login, UUID uuidCar);

 List<CarBreakdown> getAllByCarClientId(UUID uuidCar);

 Optional<CarBreakdown> getAllById(UUID uuudCarBreakdown);

}
