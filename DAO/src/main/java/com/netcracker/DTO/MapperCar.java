package com.netcracker.DTO;

import com.netcracker.DTO.car.CarClientDto;
import com.netcracker.car.CarClient;
import com.netcracker.user.Client;

public interface MapperCar {

 CarClientDto toDto(CarClient carClient);

 CarClient toEntity(CarClientDto carClientDto, Client client);

 CarClient toEntityUpdate(CarClientDto newCar, CarClient updateCar);

}
