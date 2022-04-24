package com.netcracker.DTO.ord;

import com.netcracker.order.Order;
import com.netcracker.outfit.Outfit;

public interface OrdMapper {

 Outfit toEntity(OutfitDto outfitDto, Outfit outfit);

}
