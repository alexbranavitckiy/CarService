package com.netcracker.services;


import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.outfit.State;

import java.util.List;
import java.util.UUID;


public interface OutfitsServices {

 List<OutfitDto> getAllMasterOutfitWithStateAndSort(State state, String login) throws SaveSearchErrorException;

 boolean outfitStartWork( String login, UUID uuid) throws SaveSearchErrorException;

 boolean updateOutfitByMaster(OutfitDto outfitDto, String login) throws SaveSearchErrorException;

 boolean outfitEndWork(String name, UUID uuidOutfit)  throws SaveSearchErrorException;
}
