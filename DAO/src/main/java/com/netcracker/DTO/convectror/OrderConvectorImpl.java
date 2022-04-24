package com.netcracker.DTO.convectror;

import com.netcracker.DTO.clients.MasterDto;
import com.netcracker.DTO.ord.OrderDto;
import com.netcracker.order.Order;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Slf4j
@Component
@Qualifier("OrderConvectorImpl")
public class OrderConvectorImpl implements MapperDto<OrderDto,Order>{

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
  return Objects.isNull(entity) ? null : mapper.map(entity, OrderDto.class);
 }

}
