package com.netcracker.menu.car;

import com.netcracker.CarServices;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.factory.ServicesFactory;
import com.netcracker.file.services.impl.car.CarServicesImpl;
import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.ClientServices;
import com.netcracker.file.services.impl.client.ClientServicesImpl;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ListCarClient implements Menu {

 private CarClient carClient;
 private final UUID uuid;
 private final CarServices carServices;


 public ListCarClient(ServicesFactory servicesFactory, UUID uuid) {
  this.carServices = servicesFactory.getCarServices();
  this.uuid = uuid;
 }

 @Override
 public void preMessage(String parentsName) {
  log.info("Enter 1: {}", parentsName);
  log.info("Enter 2 Display a list of car.");
 }

 @Override
 public void run(Scanner in, String parentsName) throws IOException {
  List<CarClient> clientList;
  try {
   clientList = carServices.getCarByIdClient(this.uuid);
   if (clientList.size() > 0) {
    for (int x = 1; x < clientList.size() + 1; x++) {
     log.info("Id:{} {} ", x, clientList.get(x - 1));
    }
    log.info("Enter car id");
    this.carClient = clientList.get(in.nextInt() - 1);
   }
  } catch (InputMismatchException e) {
   log.warn("Invalid data:{}. Please try again", e.getMessage());
  } catch (IndexOutOfBoundsException e) {
   log.info("Selected client not found");
  } catch (EmptySearchException e) {
   log.warn("The search has not given any results. {}", e.getMessage());
  }
 }

 public Optional<CarClient> getClient() {
  return Optional.ofNullable(carClient);
 }

}