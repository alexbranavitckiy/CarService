package com.netcracker.jdbc.services.impl.login;

import com.netcracker.LoginServices;
import com.netcracker.jdbc.services.ClientDao;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.impl.ClientDaoImpl;
import com.netcracker.jdbc.services.impl.MasterDaoImpl;
import com.netcracker.jdbc.services.impl.MasterReceiverImpl;
import com.netcracker.session.UserSession;
import com.netcracker.user.Client;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
public class LoginDaoServicesImpl implements LoginServices {

 private final ClientDao clientDao = new ClientDaoImpl();
 private final CrudDao<Master, UUID> masterDao = new MasterDaoImpl();
 private final CrudDao<MasterReceiver, UUID> crudServices = new MasterReceiverImpl();

 public LoginDaoServicesImpl() {
 }

 @Override
 public boolean searchByUserLoginAndPassword(String login, String password) {
  try {
   {
    Optional<Client> client = clientDao.getAll().stream().filter(x ->
     {
      if (x != null) {
       return x.getLogin().equalsIgnoreCase(login) && x.getPassword()
        .equalsIgnoreCase(password);
      }
      return false;
     }
    ).findFirst();
    if (client.isPresent()) {
     client.ifPresent(UserSession::openSession);
     return true;
    }
   }
   {
    Optional<Master> master = masterDao.getAll().stream().filter(x ->
     {
      if (x != null) {
       return x.getLogin().equalsIgnoreCase(login) && x.getPassword()
        .equalsIgnoreCase(password);
      } else {
       return false;
      }
     }
    ).findFirst();
    if (master.isPresent()) {
     master.ifPresent(UserSession::openSession);
     return true;
    }
   }
   {
    Optional<MasterReceiver> masters = crudServices.getAll().stream().
     filter(x ->
      {
       if (x != null) {
        return x.getLogin().equalsIgnoreCase(login) && x.getPassword()
         .equalsIgnoreCase(password);
       } else {
        return false;
       }
      }
     ).findFirst();
    if (masters.isPresent()) {
     masters.ifPresent(UserSession::openSession);
     return true;
    }
   }
  } catch (Exception e) {
   log.warn("User search failed:{}", e.getMessage());
  }
  return false;
 }
}