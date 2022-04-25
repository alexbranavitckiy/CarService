package com.netcracker.services;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.errs.SaveErrorException;
import com.netcracker.car.CarClient;
import com.netcracker.order.State;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarServices {

 boolean metadataCarChek(String metadata) throws SaveErrorException;


 Optional<CarClient> getCarByIdOnMaster(UUID uuidCar);

 List<CarClientDto> getCarByLoginClient(String login);

 boolean createCarOnClient(CarClientDto carClient, String nameClients) throws SaveErrorException;

 Optional<CarClientDto> getCarByIdCarOnClient(UUID uuidCar, String login);

 boolean updateCarClientByLogin(CarClientDto carClient, String nameClients);

 List<CarClientDto> getCarByIdClientOnClient(UUID uuidClient, String login);

}
