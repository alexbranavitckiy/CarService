package com.netcracker.DTO.car;


import com.netcracker.DTO.MapperCar;
import com.netcracker.car.CarClient;
import com.netcracker.user.Client;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class MapperCarImpl implements MapperCar {

 public CarClientDto toDto(CarClient carClient) {
  CarClientDto carClientDto = CarClientDto.builder()
   .idClient(carClient.getClient().getId())
   .metadataCar(carClient.getMetadataCar())
   .description(carClient.getDescription())
   .ear(carClient.getEar())
   .summary(carClient.getSummary())
   .run(carClient.getRun())
   .id(carClient.getId())
   .build();
  if (carClient.getMark() != null)
   carClientDto.setMark(carClient.getMark());
  return carClientDto;
 }

 public CarClient toEntityUpdate(CarClientDto newCar, CarClient updateCar) {
  updateCar.setMark(newCar.getMark());
  updateCar.setDescription(newCar.getDescription());
  updateCar.setMetadataCar(newCar.getMetadataCar());
  updateCar.setMark(newCar.getMark());
  updateCar.setEar(newCar.getEar());
  updateCar.setRun(newCar.getRun());
  return updateCar;
 }


 public CarClient toEntity(CarClientDto carClientDto, Client client) {
  CarClient carClient = CarClient.builder()
   .client(client)
   .metadataCar(carClientDto.getMetadataCar())
   .description(carClientDto.getDescription())
   .ear(carClientDto.getEar())
   .id(carClientDto.getId())
   .run(carClientDto.getRun())
   .summary(carClientDto.getSummary())
   .build();
  if (carClientDto.getMark() != null)
   carClient.setMark(carClientDto.getMark());
  return carClient;
 }

}
