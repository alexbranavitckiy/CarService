package com.netcracker.services;

import com.netcracker.outfit.Outfit;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface OutfitsServices {

 List<Outfit> getAllOutfits() ;

 boolean addObjectInOutfits(Outfit o);

 boolean updateOutfit(Outfit outfit);

 List<Outfit> getAllOutfitsAndSortingByData(Date dateStart);

 List<Outfit> getAllOutfitsByData(String start, String end);

 List<Outfit> getAllByMaster(UUID uuidMaster);

 List<Outfit> getAllByMasterAndState(UUID uuidMaster, UUID state);

 List<Outfit> getAllByState(UUID state);

}
