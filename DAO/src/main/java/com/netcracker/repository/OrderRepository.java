package com.netcracker.repository;

import com.netcracker.order.Order;
import com.netcracker.order.State;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, UUID> {

 boolean existsById(UUID id);

 List<Order> getAllByState(State state);

 List<Order> getById(UUID state);

 Optional<Order> findById(UUID id);

 @Query(value = " SELECT * FROM public.orders where orders.id_car " +
  "in(select id from car_client where car_client.id_clients in(select id from clients    where clients.login =?1)) " +
  "and state =?2", nativeQuery = true)
 List<Order> getAllOrderClient(String login, String state, Pageable nextPage);

 @Transactional
 @Modifying
 @Query(value = "UPDATE public.orders   SET state ='CANCELED',updated_date=?1 where state='REQUEST' and id = ?2" +
  " and id_car in( select id from car_client where id_clients  " +
  "in(select id from clients where login=?3))", nativeQuery = true)
 int closeStateOrder(Date date, UUID uuidOrder, String login);

 @Transactional
 @Modifying
 @Query(
  value = " INSERT into orders(id,description,state,id_car,created_date,updated_date)" +
   " SELECT * FROM (select  CAST (:id AS UUID) as id, :description as description,:state as state ," +
   "CAST (:id_car  as UUID) id_car,CAST (:created_date AS TIMESTAMP WITH TIME ZONE) as created_date," +
   "CAST (:updated_date AS TIMESTAMP WITH TIME ZONE) as updated_date) AS temp WHERE NOT exists (" +
   "  SELECT * FROM orders WHERE(state = :state and id_car = CAST (:id_car AS UUID))) LIMIT 1;", nativeQuery = true)
 int insertOrder(@Param("id") UUID id, @Param("created_date") Date created_date,
                 @Param("state") String state, @Param("updated_date") Date updated_date,
                 @Param("id_car") UUID id_car, @Param("description") String description);


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
 int insertOrder(@Param("id_outfit") UUID id_outfit, @Param("id_orders") UUID id_orders,
                 @Param("created_date") Date created_date, @Param("state") String state,
                 @Param("updated_date") Date updated_date, @Param("id_car") UUID id_car,
                 @Param("login") String login, @Param("car") UUID car, @Param("breakdown") UUID breakdown,
                 @Param("stateBreakdown") String stateBreakdown,
                 @Param("update_dateBreakdown") Date update_dateBreakdown, @Param("date_end") Date date_end,
                 @Param("date_start") Date date_start, @Param("id_master") UUID id_master,
                 @Param("description") String description, @Param("name") String name,
                 @Param("state_outfit") String state_outfit,
                 @Param("id_outfits") UUID id_outfits, @Param("price") double price,
                 @Param("run_car_size") int run_car_size);

 @Transactional
 @Modifying
 @Query(
  value =
   "insert into outfit(id,date_end,date_start,id_master,description,name,state_outfit)" +
    "values(:id_outfits,:date_end,:date_start,:id_master,:description,:name,:state_outfit);" +
    "UPDATE orders set created_date=:created_date,description=:description,state=:state," +
    "updated_date=:updated_date,id_outfits=:id_outfits where id=:id_orders ; " +
    "INSERT INTO public.orders_master_receiver (orders_id, master_receiver_id) " +
    "VALUES(:id_orders, (SELECT id FROM public.master where master.login=:login)); " +
    "insert into car_breakdown(id, id_car ,state,run_car_size,description,update_date,id_outfit,price) " +
    "values(:breakdown,(SELECT id_car from orders where id=:id_orders),:stateBreakdown," +
    ":run_car_size,:description,:update_dateBreakdown,:id_outfits,:price)",
  nativeQuery = true)
 int updateOrderFromREQUEST(@Param("id_outfits") UUID id_outfits, @Param("id_orders") UUID id_orders,
                            @Param("created_date") Date created_date, @Param("state") String state,
                            @Param("updated_date") Date updated_date,
                            @Param("login") String login, @Param("breakdown") UUID breakdown,
                            @Param("stateBreakdown") String stateBreakdown,
                            @Param("update_dateBreakdown") Date update_dateBreakdown, @Param("date_end") Date date_end,
                            @Param("date_start") Date date_start, @Param("id_master") UUID id_master,
                            @Param("description") String description, @Param("name") String name,
                            @Param("state_outfit") String state_outfit,
                            @Param("price") double price,
                            @Param("run_car_size") int run_car_size);

 @Transactional
 @Modifying
 @Query(value = "UPDATE  public.orders SET description=:description, state=:state, updated_date=:updated_date," +
  " id_car=:id_car WHERE id=:Id", nativeQuery = true)
 int updateOrderOnMaster(@Param("Id") UUID id, @Param("updated_date") Date updated_date, @Param("id_car") UUID id_car);


 @Transactional
 @Modifying
 @Query(value = "UPDATE  public.orders SET description=:description, state=:state, updated_date=:updated_date" +
  " WHERE id=:Id", nativeQuery = true)
 int updateOrderOnMaster(@Param("Id") UUID id, @Param("description") String description, @Param("state") String state,
                         @Param("updated_date") Date updated_date);
}
