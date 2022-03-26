package com.netcracker.jdbc.services.impl.car;

import com.netcracker.CarServices;
import com.netcracker.jdbc.services.CarDao;
import com.netcracker.jdbc.services.impl.CarClientDaoImpl;
import com.netcracker.marka.CarClient;
import lombok.SneakyThrows;

import java.util.List;
import java.util.UUID;

public class CarDaoServicesImpl implements CarServices {

 private final CarDao carDao = new CarClientDaoImpl();

 @Override
 @SneakyThrows
 public List<CarClient> getCarByIdClient(UUID uuidClient) {
  return carDao.getAllCarClientByIdClient(uuidClient);
 }

 @Override
 @SneakyThrows
 public List<CarClient> getAllCar() {
  return carDao.getAll();
 }

 @Override
 @SneakyThrows
 public boolean addCar(CarClient carClient) {
  return this.carDao.addObject(carClient);
 }

 @Override
 @SneakyThrows
 public boolean updateCarClient(CarClient carClient) {
  return this.carDao.update(carClient);
 }

}
