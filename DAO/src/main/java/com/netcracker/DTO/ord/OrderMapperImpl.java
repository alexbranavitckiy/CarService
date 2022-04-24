package com.netcracker.DTO.ord;

import com.netcracker.order.Order;
import com.netcracker.outfit.Outfit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
public class OrderMapperImpl implements OrdMapper {


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
