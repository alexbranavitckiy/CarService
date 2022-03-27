package com.netcracker.jdbc.services.impl;

import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.TemplateJDBCDao;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class MasterReceiverDaoImpl extends TemplateJDBCDao<MasterReceiver, UUID> {

 @Override
 public String getSelectQuery() {
  return "SELECT master_receivers.id,employers.education,employers.name,employers.qualification_id,employers.password,employers.login,employers.home_address,employers.phone,employers.mail,employers.descriptions FROM public.master_receivers,public.employers where master_receivers.id=employers.id; ";
 }

 @Override
 public String getSelectByIdQuery() {
  return "SELECT master_receivers.id,employers.education,employers.name,employers.qualification_id,employers.password,employers.login,employers.home_address,employers.phone,employers.mail,employers.descriptions, FROM public.master_receivers,public.employers where master_receivers.id=employers.id AND master_receivers.id=?;";
 }

 @Override
 public String getCreateQuery() {
  return "INSERT INTO public.master_receivers(id, name, education, qualification_id, password, login, home_address, phone, mail, descriptions) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?); INSERT INTO public.master_receivers(id)VALUES (?);";
 }

 @Override
 public String getUpdateQuery() {
  return "UPDATE public.employers SET id=?, name=?, education=?, qualification_id=?, password=?, login=?, home_address=?, phone=?, mail=?, descriptions=? WHERE id=?;";
 }


 @Override
 public String getDeleteQuery() {
  return "DELETE FROM public.master_receivers WHERE id=?;";
 }


 @Override
 protected List<MasterReceiver> parseResultSet(ResultSet rs) throws PersistException {
  List<MasterReceiver> masters = new ArrayList<>();
  try {
   while (rs.next()) {
    MasterReceiver master = MasterReceiver.builder()
     .id(UUID.fromString(rs.getString("id")))
     .education(rs.getString("education"))
     .description(rs.getString("descriptions"))
     .qualificationEnum((UUID) rs.getObject("qualification_id"))
     .phone(rs.getString("phone"))
     .login(rs.getString("login"))
     .mail(rs.getString("mail"))
     .name(rs.getString("name"))
     .password(rs.getString("password"))
     .homeAddress(rs.getString("home_address"))
     .build();
    masters.add(master);
   }
   return masters;
  } catch (Exception e) {
   throw new PersistException(e);
  }
 }


 @Override
 protected void prepareStatementForInsert(PreparedStatement statement, MasterReceiver master)
  throws PersistException {
  addQuery(statement, master);
 }

 private void addQuery(PreparedStatement statement, MasterReceiver master) throws PersistException {
  try {
   statement.setObject(1, master.getId());
   statement.setObject(2, master.getName());
   statement.setObject(3, master.getEducation());
   statement.setObject(4, master.getQualificationEnum());
   statement.setObject(5, master.getPassword());
   statement.setObject(6, master.getLogin());
   statement.setObject(7, master.getHomeAddress());
   statement.setObject(8, master.getPhone());
   statement.setObject(9, master.getMail());
   statement.setObject(10, master.getDescription());
   statement.setObject(11, master.getId());
  } catch (Exception e) {
   throw new PersistException(e);
  }
 }

 @Override
 protected void prepareStatementForUpdate(PreparedStatement statement, MasterReceiver master)
  throws PersistException {
  addQuery(statement, master);
 }

}
