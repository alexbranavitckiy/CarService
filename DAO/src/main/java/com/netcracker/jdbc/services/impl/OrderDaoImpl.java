package com.netcracker.jdbc.services.impl;

import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.OrderDao;
import com.netcracker.jdbc.services.TemplateJDBCDao;
import com.netcracker.order.Order;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
public class OrderDaoImpl extends TemplateJDBCDao<Order, UUID> implements OrderDao {

 @Override
 public String getSelectQuery() {
  return "SELECT * FROM public.orders;";
 }

 @Override
 public String getSelectByIdQuery() {
  return "SELECT * FROM public.orders where id=?;";
 }

 @Override
 public String getRequestQueryByRepairRequest() {
  return "INSERT INTO public.orders(id, descriptions, id_clients, id_masters, created_date,id_outfits, updated_date, id_state_order, price_sum) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
 }

 public String getCreateQuery() {
  return "INSERT INTO public.orders(id, descriptions, id_clients, id_masters, created_date, id_outfits, updated_date, id_state_order, price_sum) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
 }

 @Override
 public String getAllOrderByState() {
  return "SELECT * FROM public.orders where id_state_order=?;";
 }

 @Override
 public String getUpdateQuery() {
  return "UPDATE public.orders SET id=?, descriptions=?, id_clients=?, id_masters=?, created_date=?, id_outfits=?, updated_date=?, id_state_order=?, price_sum=? WHERE id=?;";
 }


 @Override
 public String getDeleteQuery() {
  return "DELETE FROM public.outfits WHERE id=?;";
 }

 @Override
 protected List<Order> parseResultSet(ResultSet rs) throws PersistException {
  List<Order> masters = new ArrayList<>();
  try {
   while (rs.next()) {
    Order master = Order.builder()
     .id(UUID.fromString(rs.getString("id")))
     .descriptions(rs.getString("descriptions"))
     .clientUUID((UUID) rs.getObject("id_clients"))
     .masterReceiver((UUID) rs.getObject("id_masters"))
     .createdDate((Date) rs.getObject("created_date"))
     .updatedDate((Date) rs.getObject("updated_date"))
     .stateOrder((UUID) rs.getObject("id_state_order"))
     .priceSum(rs.getDouble("price_sum"))
     .build();
    if (rs.getObject("id_outfits")!=null){
     master.setOutfits(List.of((UUID) rs.getObject("id_outfits")));
    }

    masters.add(master);
   }
   return masters;
  } catch (Exception e) {
   log.warn("Entity parsing error:{}", e.getMessage());
   throw new PersistException(e);
  }
 }

 @Override
 protected void prepareStatementForInsert(PreparedStatement statement, Order outfit)
  throws PersistException {
  addQuery(statement, outfit);
 }

 private void addQuery(PreparedStatement statement, Order outfit) {
  try {
   statement.setObject(1, outfit.getId());
   statement.setObject(2, outfit.getDescriptions());
   statement.setObject(3, outfit.getClientUUID());
   statement.setObject(4, outfit.getMasterReceiver());
   statement.setDate(5, new java.sql.Date(outfit.getCreatedDate().getTime()));
   statement.setDate(7, new java.sql.Date(outfit.getUpdatedDate().getTime()));
   statement.setObject(8, outfit.getStateOrder());
   statement.setObject(9, outfit.getPriceSum());
   if (outfit.getOutfits() != null && !outfit.getOutfits().isEmpty()) {
    statement.setObject(6, outfit.getOutfits().get(0));
   }
   else {
    statement.setObject(6, null);
   }
  } catch (Exception e) {
   log.warn("Entity parsing error:{}", e.getMessage());
  }
 }

 @Override
 protected void prepareStatementForUpdate(PreparedStatement statement, Order order)
  throws PersistException {
  addQuery(statement, order);
  try {
   statement.setObject(10, order.getId());
  } catch (Exception e) {
   log.warn("Entity parsing error:{}", e.getMessage());
  }
 }


}
