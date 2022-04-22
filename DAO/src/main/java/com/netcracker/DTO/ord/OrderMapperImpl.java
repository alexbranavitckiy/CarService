package com.netcracker.DTO.ord;

import com.netcracker.order.Order;
import com.netcracker.outfit.Outfit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class OrderMapperImpl implements OrdMapper {

 @Autowired
 OrderMapperImpl() {
 }

 public Order toEntity(OrderDto orderDto) {
  return Order.builder()
   .createdDate(orderDto.getCreatedDate())
   .description(orderDto.getDescription())
   .id(orderDto.getId())
   .state(orderDto.getState())
   .updatedDate(orderDto.getUpdatedDate())
   .build();
 }

 public OrderDto toDto(Order order) {
  return OrderDto.builder()
   .createdDate(order.getCreatedDate())
   .description(order.getDescription())
   .state(order.getState())
   .updatedDate(order.getUpdatedDate())
   .id(order.getId())
   .carClient(order.getCarClient().getId())
   .build();
 }

 public OutfitDto toDto(Outfit outfit) {
  return OutfitDto.builder()
   .id(outfit.getId())
   .dateEnt(outfit.getDateEnt())
   .dateStart(outfit.getDateStart())
   .description(outfit.getDescription())
   .name(outfit.getName())
   .price(outfit.getPrice())
   .stateOutfit(outfit.getStateOutfit())
   .build();
 }


 @Override
 public Outfit toEntity(OutfitDto outfitDto,Outfit outfit) {
  outfit.setStateOutfit(outfitDto.getStateOutfit());
  outfit.setPrice(outfitDto.getPrice());
  outfit.setDateEnt(outfitDto.getDateEnt());
  outfit.setName(outfitDto.getName());
  outfit.setDateStart(outfitDto.getDateStart());
  outfit.setDescription(outfitDto.getDescription());
  return outfit;
 }


}
