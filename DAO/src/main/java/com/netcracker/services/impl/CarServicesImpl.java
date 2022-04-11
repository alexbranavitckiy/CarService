package com.netcracker.services.impl;

import com.netcracker.car.CarClient;
import com.netcracker.order.State;
import com.netcracker.repository.CarClientRepository;
import com.netcracker.services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarServicesImpl implements CarServices {

 private final CarClientRepository carClientRepository;


 @Autowired
 private CarServicesImpl(CarClientRepository carClientRepository){
  this.carClientRepository=carClientRepository;
 }

 @Override
 public List<CarClient> getCarByIdClient(UUID uuidClient) {
  return null;
 }

 @Override
 public List<CarClient> getCarByLoginClient(String login) {
  return null;
 }

 @Override
 public Optional<CarClient> getCarByIdCar(UUID uuidCar) {
  return Optional.empty();
 }

 @Override
 public List<CarClient> getAllCar() {
  return null;
 }

 @Override
 public boolean addCar(CarClient carClient, String nameClients) {
  return false;
 }

 @Override
 public boolean updateCarClient(CarClient carClient, String nameClients) {
  return false;
 }

 @Override
 public List<CarClient> getAllCarClientWaitState(State state, UUID uuidClient) {
  return null;
 }

 @Override
 public List<CarClient> getAllCarClientWithState(State state) {
  return null;
 }
}
