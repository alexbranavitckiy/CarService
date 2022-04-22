package com.netcracker.DTO.car;

import com.netcracker.DTO.CarBreakdownMapper;
import com.netcracker.DTO.MapperCar;
import com.netcracker.breakdown.CarBreakdown;
import com.netcracker.car.CarClient;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CarBreakdownMapperImpl implements CarBreakdownMapper {

 private final MapperCar mapperCar;

 @Autowired
 CarBreakdownMapperImpl(MapperCar mapperCar) {
  this.mapperCar = mapperCar;
 }

 public CarBreakdown toEntity(CarBreakdownDto carBreakdownDto, Client client) {
  return CarBreakdown.builder()
   .id(carBreakdownDto.getId())
   .state(carBreakdownDto.getState())
   .description(carBreakdownDto.getDescription())
   .runCarSize(carBreakdownDto.getRunCarSize())
   .location(carBreakdownDto.getLocation())
   .updateDate(carBreakdownDto.getUpdateDate())
   .carClient(mapperCar.toEntity(carBreakdownDto.getCarClient(), client))
   .build();
 }

 public CarBreakdownDto toDto(CarBreakdown carBreakdown) {
  return CarBreakdownDto.builder()
   .carClient(mapperCar.toDto(carBreakdown.getCarClient()))
   .id(carBreakdown.getId())
   .description(carBreakdown.getDescription())
   .location(carBreakdown.getLocation())
   .runCarSize(carBreakdown.getRunCarSize())
   .state(carBreakdown.getState())
   .updateDate(carBreakdown.getUpdateDate())
   .build();
 }

 public CarBreakdown toEntityWithNewCar(CarBreakdownForm carBreakdownDto, CarClient carClient) {
  return CarBreakdown.builder()
   .carClient(carClient)
   .updateDate(carBreakdownDto.getUpdateDate())
   .location(carBreakdownDto.getLocation())
   .runCarSize(carBreakdownDto.getRunCarSize())
   .description(carBreakdownDto.getDescription())
   .state(carBreakdownDto.getState())
   .id(UUID.randomUUID())
   .build();
 }

 @Override
 public CarBreakdown toEntity(CarBreakdownForm carBreakdownForm, CarBreakdown carBreakdown) {
  carBreakdown.setState(carBreakdownForm.getState());
  carBreakdown.setRunCarSize(carBreakdownForm.getRunCarSize());
  carBreakdown.setUpdateDate(carBreakdownForm.getUpdateDate());
  carBreakdown.setLocation(carBreakdownForm.getLocation());
  carBreakdown.setDescription(carBreakdownForm.getDescription());
  return carBreakdown;
 }


}
