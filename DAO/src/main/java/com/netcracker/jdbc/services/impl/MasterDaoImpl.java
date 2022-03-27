package com.netcracker.jdbc.services.impl;

import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.TemplateJDBCDao;
import com.netcracker.user.Master;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class MasterDaoImpl extends TemplateJDBCDao<Master, UUID> {

 @Override
 public String getSelectQuery() {
  return "SELECT masters.id,masters.id_outfits,employers.education,employers.name,employers.qualification_id,employers.password,employers.login,employers.home_address,employers.phone,employers.mail,employers.descriptions FROM public.masters,public.employers where masters.id=employers.id; ";
 }

 @Override
 public String getSelectByIdQuery() {
  return "SELECT masters.id,employers.education,employers.name,employers.qualification_id,employers.password,employers.login,employers.home_address,employers.phone,employers.mail,employers.descriptions,masters.id_outfits FROM public.masters,public.employers where masters.id=employers.id AND masters.id=?;";
 }

 @Override
 public String getCreateQuery() {
  return "INSERT INTO public.employers(id, name, education, qualification_id, password, login, home_address, phone, mail, descriptions) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?); INSERT INTO public.masters(id, id_outfits)VALUES (?, ?);";
 }

 @Override
 public String getUpdateQuery() {
  return "UPDATE public.employers SET id=?, name=?, education=?, qualification_id=?, password=?, login=?, home_address=?, phone=?, mail=?, descriptions=? WHERE id=?; UPDATE public.masters SET id=?, id_outfits=? WHERE id=?;";
 }

 @Override
 public String getDeleteQuery() {
  return "DELETE FROM public.masters WHERE id=?;";
 }

 @Override
 protected List<Master> parseResultSet(ResultSet rs) throws PersistException {
  List<Master> masters = new ArrayList<>();
  try {
   while (rs.next()) {
    Master master = Master.builder()
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
    if (rs.getString("id_outfits") != null) {
     master.setOutfits(List.of(UUID.fromString(rs.getString(rs.getString("id_outfits")))));
    }
    masters.add(master);
   }
   return masters;
  } catch (Exception e) {
   throw new PersistException(e);
  }
 }

 @Override
 protected void prepareStatementForInsert(PreparedStatement statement, Master master)
  throws PersistException {
  try {
   addQuery(statement, master);
   if (master.getOutfits() != null && !master.getOutfits().isEmpty()) {
    statement.setObject(12, master.getOutfits().get(0));
   } else
    statement.setObject(12, null);
  } catch (Exception e) {
   throw new PersistException(e);
  }
 }


 private void addQuery(PreparedStatement statement, Master master) throws PersistException {
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
 protected void prepareStatementForUpdate(PreparedStatement statement, Master master)
  throws PersistException {
  addQuery(statement, master);
  try {
   addQuery(statement, master);
   statement.setObject(12, master.getId());
   addQuery(statement, master);
   if (master.getOutfits() != null && !master.getOutfits().isEmpty()) {
    statement.setObject(13, master.getOutfits().get(0));
   } else
    statement.setObject(13, null);
   statement.setObject(14, master.getId());
  } catch (Exception e) {
   throw new PersistException(e);
  }
 }

}
