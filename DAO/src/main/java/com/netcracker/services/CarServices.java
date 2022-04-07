package com.netcracker.services;

import com.netcracker.car.CarClient;
import com.netcracker.order.State;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface CarServices {

 List<CarClient> getCarByIdClient(UUID uuidClient) ;

 List<CarClient> getAllCar() ;

 boolean addCar(CarClient carClient) ;

 boolean updateCarClient(CarClient carClient) ;

 List<CarClient> getAllCarClientWaitState(State state, UUID uuidClient);

 List<CarClient> getAllCarClientWithState(State state);

}
