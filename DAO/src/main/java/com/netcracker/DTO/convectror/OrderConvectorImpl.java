package com.netcracker.DTO.convectror;

import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.order.Order;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Objects;


@Slf4j
@Component
@Qualifier("OrderConvectorImpl")
public class OrderConvectorImpl implements MapperDto<OrderDto, Order> {

 private final ModelMapper mapper;


 @Autowired
 OrderConvectorImpl(ModelMapper modelMapper) {
  this.mapper = modelMapper;
 }

 @Override
 public Order toEntity(OrderDto dto) {
  return Objects.isNull(dto) ? null : mapper.map(dto, Order.class);
 }

 @Override
 public OrderDto toDto(Order entity) {
  OrderDto orderDto=Objects.isNull(entity) ? null : mapper.map(entity, OrderDto.class);
  orderDto.setCarClient(entity.getCarClient().getId());
  return orderDto;
 }

}
