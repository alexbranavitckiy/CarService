package com.netcracker.services;


import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.ord.OutfitDto;
import com.netcracker.DTO.time.TimeDto;
import com.netcracker.outfit.State;

import java.security.Principal;
import java.util.List;
import java.util.UUID;


public interface OutfitsServices {

 List<OutfitDto> getAllMasterOutfitWithStateAndSort(State state, String login) throws SaveSearchErrorException;

 boolean outfitStartWork(String login, UUID uuid) throws SaveSearchErrorException;

 boolean updateOutfitByMaster(OutfitDto outfitDto, String login) throws SaveSearchErrorException;

 boolean outfitEndWork(String name) throws SaveSearchErrorException;

 UUID createOutfitOnMasterR(OutfitDto outfitDto, String login) throws SaveSearchErrorException;

 List<TimeDto> getAllOutfitByTime() throws SaveSearchErrorException;

 boolean updateOutfitByMasterR(OutfitDto outfitDto, String name) throws SaveSearchErrorException;
}
