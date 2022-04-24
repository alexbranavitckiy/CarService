package com.netcracker.repository;


import com.netcracker.order.Order;
import com.netcracker.order.State;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface OrderRepository extends JpaRepository<Order, UUID> {

 Optional<Order> getAllById(UUID id);


 @Query(value = " SELECT orders.id, created_date, orders.description, state, updated_date, id_car, id_outfits FROM public.orders as orders  left join public.clients c  on c.login =?1 and orders.state =?2", nativeQuery = true)
 List<Order> getAllOrderClient(String login, State state);


 @Modifying
 @Query(
  value =
   "insert into order (id,created_date,description,state,updated_date,id_car) values (:id,:created_date,:description, :state, :updated_date, :id_car)",
  nativeQuery = true)
 void insertOrder(@Param("id") String id, @Param("created_date") Date created_date, @Param("state") String state,
                  @Param("updated_date") Date updated_date, @Param("id_car") String id_car, @Param("description") String description);



}
