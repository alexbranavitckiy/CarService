package com.netcracker.file.services.impl.car;

import com.netcracker.CarServices;
import com.netcracker.errors.EmptySearchException;
import com.netcracker.file.FileService;
import com.netcracker.file.services.CRUDServices;
import com.netcracker.file.services.impl.CRUDServicesImpl;
import com.netcracker.marka.CarClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CarServicesImpl implements CarServices {

 private final FileService fileService = new FileService();
 private final CRUDServices<CarClient, UUID> crudServices = new CRUDServicesImpl<>();

 @Override
 public List<CarClient> getCarByIdClient(UUID uuidClient) throws EmptySearchException {
  return crudServices.getAll(fileService.getCar(), CarClient[].class)
   .stream().filter(x -> {
    if (x.getId_clients() != null)
     return x.getId_clients().equals(uuidClient);
    return false;
   }).collect(Collectors.toList());
 }

 @Override
 public List<CarClient> getAllCar() throws EmptySearchException {
  return crudServices.getAll(fileService.getCar(), CarClient[].class);
 }

 @Override
 public boolean addCar(CarClient client) {
  return crudServices.addObject(client, fileService.getCar(), CarClient[].class);
 }

 @Override
 public boolean updateCarClient(CarClient carClient) {
  return crudServices.updateObject(carClient, fileService.getCar(), CarClient[].class);
 }
}
