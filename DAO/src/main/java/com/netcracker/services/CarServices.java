package com.netcracker.services;

import com.netcracker.car.CarClient;
import com.netcracker.order.State;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarServices {

 List<CarClient> getCarByIdClient(UUID uuidClient);

 List<CarClient> getCarByLoginClient(String login);

 Optional<CarClient> getCarByIdCar(UUID uuidCar);

 List<CarClient> getAllCar() ;

 boolean addCar(CarClient carClient,String nameClients) ;

 boolean updateCarClient(CarClient carClient,String nameClients) ;

 List<CarClient> getAllCarClientWaitState(State state, UUID uuidClient);

 List<CarClient> getAllCarClientWithState(State state);

}
