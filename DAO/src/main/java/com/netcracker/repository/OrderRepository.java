package com.netcracker.repository;

import com.netcracker.order.Order;
import com.netcracker.order.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

 boolean existsById(UUID id);

 List<Order> getAllByState(State state);

 @Query(value = " SELECT * FROM public.orders where orders.id_car " +
  "in(select id from car_client where car_client.id_clients in(select id from clients    where clients.login =?1)) " +
  "and state =?2", nativeQuery = true)
 List<Order> getAllOrderClient(String login, String state);

 @Transactional
 @Modifying
 @Query(value = "UPDATE public.orders   SET state =?1,updated_date=?2 where orders.id = ?3 and orders.id_car" +
  " in( select car_client.id from car_client where car_client.id_clients  " +
  "in(select clients.id from clients where clients.login=?4))", nativeQuery = true)
 int updateStateOrder(String state, Date date, UUID uuidOrder, String login);

 @Transactional
 @Modifying
 @Query(
  value = "insert into orders(id,created_date,description,state,updated_date,id_car)" +
   " values (:id,:created_date,:description,:state,:updated_date,:id_car)", nativeQuery = true)
 int insertOrder(@Param("id") UUID id, @Param("created_date") Date created_date, @Param("state") String state,
                 @Param("updated_date") Date updated_date, @Param("id_car") UUID id_car, @Param("description") String description);

 @Transactional
 @Modifying
 @Query(
  value =
   "insert into outfit(id,date_end,date_start,id_master,description,name,state_outfit)" +
    "values(:id_outfit,:date_end,:date_start,:id_master,:description,:name,:state_outfit);" +
    "insert into orders(id,created_date,description,state,updated_date,id_car,id_outfits) " +
    "values (:id_orders,:created_date,:description,:state,:updated_date,:id_car,:id_outfits);" +
    "INSERT INTO public.orders_master_receiver (orders_id, master_receiver_id)" +
    "VALUES(:id_orders, (SELECT id FROM public.master where master.login=:login)); " +
    "insert into car_breakdown(id,id_car,state,run_car_size,description,update_date,id_outfit,price)" +
    "values(:breakdown,:car,:stateBreakdown,:run_car_size,:description,:update_dateBreakdown,:id_outfit,:price)",
  nativeQuery = true)
 int insertOrder(@Param("id_outfit") UUID id_outfit, @Param("id_orders") UUID id_orders, @Param("created_date")
  Date created_date, @Param("state") String state,
                 @Param("updated_date") Date updated_date, @Param("id_car") UUID id_car,
                 @Param("login") String login, @Param("car") UUID car, @Param("breakdown") UUID breakdown,
                 @Param("stateBreakdown") String stateBreakdown,
                 @Param("update_dateBreakdown") Date update_dateBreakdown, @Param("date_end") Date date_end, @Param("date_start") Date date_start,
                 @Param("id_master") UUID id_master, @Param("description") String description,
                 @Param("name") String name, @Param("state_outfit") String state_outfit,
                 @Param("id_outfits") UUID id_outfits, @Param("price") double price, @Param("run_car_size") int run_car_size);


 //UPDATE public.orders
 //SET created_date='', description='', state='', updated_date='', id_car=?, id_outfits=?
 //WHERE id=?;

 @Transactional
 @Modifying
 @Query(value = "UPDATE  public.orders SET description='', state='', updated_date='', id_car=?" +
  "WHERE id=?", nativeQuery = true)
 int updateOrderOnMaster(@Param("Id") UUID id, @Param("description") String description, @Param("state") String state,
                         @Param("updated_date") Date updated_date, @Param("id_car") UUID id_car);

}
