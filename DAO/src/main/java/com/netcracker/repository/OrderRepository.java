package com.netcracker.repository;

import com.netcracker.order.Order;
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
public interface OrderRepository extends JpaRepository<Order, UUID> {

 boolean existsById(UUID id);

 Optional<Order> getAllById(UUID id);

 @Query(value = " SELECT orders.id, created_date, orders.description, state, updated_date, id_car, id_outfits FROM public.orders as orders  left join public.clients c  on c.login =?1 and orders.state =?2", nativeQuery = true)
 List<Order> getAllOrderClient(String login, String state);

 //UPDATE public.orders
 //SET created_date='', description='', state='', updated_date='', id_car=?, id_outfits=?
 //WHERE id=?;

//SELECT id, description, metadata_car, run, summary, "year", id_clients, id_mark
//FROM public.car_client;


 @Transactional
 @Modifying
 @Query(value = "UPDATE public.orders   SET state =?1,updated_date=?2  where orders.id = ?3 and orders.id_car in( select car_client.id from car_client where car_client.id_clients  in(select clients.id from clients where clients.login=?4))", nativeQuery = true)
 int updateStateOrder(String state, Date date, UUID uuidOrder, String login);

 @Transactional
 @Modifying
 @Query(
  value =
   "insert into orders(id,created_date,description,state,updated_date,id_car) values (:id,:created_date,:description,:state,:updated_date,:id_car)",
  nativeQuery = true)
 int insertOrder(@Param("id") UUID id, @Param("created_date") Date created_date, @Param("state") String state,
                 @Param("updated_date") Date updated_date, @Param("id_car") UUID id_car, @Param("description") String description);

}
