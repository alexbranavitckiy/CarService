package com.netcracker.jdbc.services.impl.login;

import com.netcracker.*;
import com.netcracker.jdbc.services.ClientDao;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.impl.ClientDaoImpl;
import com.netcracker.jdbc.services.impl.MasterDaoImpl;
import com.netcracker.jdbc.services.impl.MasterReceiverDaoImpl;
import com.netcracker.jdbc.services.impl.car.CarDaoServicesImpl;
import com.netcracker.jdbc.services.impl.client.ClientDaoServicesImpl;
import com.netcracker.jdbc.services.impl.master.MasterDaoServicesImpl;
import com.netcracker.jdbc.services.impl.masterReceiver.MasterReceiverDaoServicesImpl;
import com.netcracker.marka.CarClient;
import com.netcracker.session.UserSession;
import com.netcracker.user.Client;
import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class LoginDaoServicesImpl implements LoginServices {

 private final ClientDao clientDao = new ClientDaoImpl();
 private final CrudDao<Master, UUID> masterDao = new MasterDaoImpl();
 private final CrudDao<MasterReceiver, UUID> masterReceiverDao = new MasterReceiverDaoImpl();
 private final CarServices carServices = new CarDaoServicesImpl();


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
     List<CarClient> carClients = carServices.getCarByIdClient(client.get().getId());
     if (!carClients.isEmpty()) {
      carClients.forEach(x -> {
       client.get().getCarClients().add(x.getId());
      });
     }
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
    Optional<MasterReceiver> masters = masterReceiverDao.getAll().stream().
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