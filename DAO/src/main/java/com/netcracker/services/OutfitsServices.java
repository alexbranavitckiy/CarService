package com.netcracker.services;


import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.outfit.State;

import java.util.List;
import java.util.UUID;


public interface OutfitsServices {

 List<OutfitDto> getAllMasterOutfitWithStateAndSort(State state, String login);

 boolean outfitStartWork(State state, String login, UUID uuid);

 boolean updateOutfitByMaster(OutfitDto outfitDto, String login);

}
