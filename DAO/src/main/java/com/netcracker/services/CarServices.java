package com.netcracker.services;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.car.CarClient;
import com.netcracker.order.State;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarServices {

 Optional<CarClient> getCarByIdOnMaster(UUID uuidCar);

 List<CarClientDto> getCarByLoginClient(String login);

 boolean createCarOnClient(CarClientDto carClient, String nameClients);

 Optional<CarClientDto> getCarByIdCarOnClient(UUID uuidCar, String login);

 boolean updateCarClientByLogin(CarClientDto carClient, String nameClients);

 List<CarClientDto> getCarByIdClientOnClient(UUID uuidClient, String login);

}
