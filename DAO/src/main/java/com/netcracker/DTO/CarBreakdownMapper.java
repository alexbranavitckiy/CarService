package com.netcracker.DTO;

import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.car.CarBreakdownForm;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.car.CarClient;
import com.netcracker.user.Client;

public interface CarBreakdownMapper {

 CarBreakdown toEntity(CarBreakdownDto carBreakdownDto, Client client);

 CarBreakdown toEntityWithNewCar(CarBreakdownForm carBreakdownDto, CarClient carClient);

 CarBreakdown toEntity(CarBreakdownForm carBreakdownForm, CarBreakdown carBreakdown);
}
