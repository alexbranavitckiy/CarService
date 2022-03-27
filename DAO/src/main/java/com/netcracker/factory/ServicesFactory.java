package com.netcracker.factory;


import com.netcracker.*;
import com.netcracker.file.services.impl.login.LoginServicesImpl;
import com.netcracker.file.services.impl.car.CarServicesImpl;
import com.netcracker.file.services.impl.client.ClientServicesImpl;
import com.netcracker.file.services.impl.master.MasterServicesImpl;
import com.netcracker.file.services.impl.masterReceiver.MasterReceiverServicesImpl;
import com.netcracker.file.services.impl.order.OrderServicesImpl;
import com.netcracker.file.services.impl.outfit.OutfitsServicesImpl;
import com.netcracker.jdbc.services.impl.car.CarDaoServicesImpl;
import com.netcracker.jdbc.services.impl.client.ClientDaoServicesImpl;
import com.netcracker.jdbc.services.impl.login.LoginDaoServicesImpl;
import com.netcracker.jdbc.services.impl.master.MasterDaoServicesImpl;
import com.netcracker.jdbc.services.impl.masterReceiver.MasterReceiverDaoServicesImpl;
import com.netcracker.jdbc.services.impl.order.OrderDaoServicesImpl;
import com.netcracker.jdbc.services.impl.outfit.OutfitsDaoServicesImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.ResourceBundle;

@Slf4j
public class ServicesFactory {

 ResourceBundle resource = ResourceBundle.getBundle("persistent");
 String name_persistent = resource.getString("persistent");


 public ClientServices getClientServices() {
  if (name_persistent.equalsIgnoreCase("file")) {
   return new ClientServicesImpl();
  }
  return new ClientDaoServicesImpl();
 }

 public CarServices getCarServices() {
  if (name_persistent.equalsIgnoreCase("file")) {
   return new CarServicesImpl();
  }
  return new CarDaoServicesImpl();
 }

 public MasterServices getMasterServices() {
  if (name_persistent.equalsIgnoreCase("file")) {
   return new MasterServicesImpl();
  }
  return new MasterDaoServicesImpl();
 }

 public LoginServices getLoginServices() {
  if (name_persistent.equalsIgnoreCase("file")) {
   return new LoginServicesImpl();
  }
  return new LoginDaoServicesImpl();
 }

 public OutfitsServices getOutfitServices() {
  if (name_persistent.equalsIgnoreCase("file")) {
   return new OutfitsServicesImpl();
  }
  return new OutfitsDaoServicesImpl();
 }

 public OrderServices getOrderServices() {
  if (name_persistent.equalsIgnoreCase("file")) {
   return new OrderServicesImpl();
  }
  return new OrderDaoServicesImpl();
 }

 public MasterReceiverServices getMasterReceiverServices() {
  if (name_persistent.equalsIgnoreCase("file")) {
   return new MasterReceiverServicesImpl();
  }
  return new MasterReceiverDaoServicesImpl();
 }


 public Object daoFactory(TypeFactory factory) {
  switch (factory) {
   case CLIENT_SERVICES: {
    return this.getClientServices();
   }
   case MASTER_SERVICES: {
    return this.getMasterServices();
   }
   case LOGIN_SERVICES: {
    return this.getLoginServices();
   }
   case MASTER_RECEIVER_SERVICES: {
    return this.getMasterReceiverServices();
   }
   case ORDER_SERVICES: {
    return this.getOrderServices();
   }
   case OUTFITS_SERVICES: {
    return this.getOutfitServices();
   }
  }
  log.warn("Service take error");
  throw new IllegalArgumentException("Service take error");
 }

}

