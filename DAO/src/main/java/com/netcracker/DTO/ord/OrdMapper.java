package com.netcracker.DTO.ord;

import com.netcracker.order.Order;
import com.netcracker.outfit.Outfit;

public interface OrdMapper {

 OutfitDto toDto(Outfit outfit);

 Order toEntity(OrderDto orderDto);

 Outfit toEntity(OutfitDto outfitDto, Outfit outfit);

 OrderDto toDto(Order order);
}
