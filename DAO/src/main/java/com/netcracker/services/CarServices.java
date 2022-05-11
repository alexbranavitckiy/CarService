package com.netcracker.services;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.car.CarClient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarServices {

 boolean idChek(UUID uuid) throws SaveSearchErrorException;

 boolean metadataCarChek(String metadata) throws SaveSearchErrorException;

 Optional<CarClient> getCarByIdOnMaster(UUID uuidCar);

 List<CarClientDto> getCarByLoginClient(String login);

 boolean createCarOnClient(CarClientDto carClient, String nameClients) throws SaveSearchErrorException;

 Optional<CarClientDto> getCarByIdCarOnClient(UUID uuidCar, String login);

 boolean updateCarClientByLogin(CarClientDto carClient, String nameClients) throws SaveSearchErrorException;

 boolean updateCarClientByIdWithMachineNumber(CarClientDto carClient, String login) throws SaveSearchErrorException;

 UUID createCarOnMaster(CarClientDto carClient) throws SaveSearchErrorException;

 List<CarClientDto> getAllCarOnMaster() throws SaveSearchErrorException;

 List<CarClientDto> getSearchCarOnMaster(String search, Integer offset, Integer limit) throws SaveSearchErrorException;

}
