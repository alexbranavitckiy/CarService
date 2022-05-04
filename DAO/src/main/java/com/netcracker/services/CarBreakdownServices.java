package com.netcracker.services;

import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.breakdown.State;

import java.util.List;
import java.util.UUID;

public interface CarBreakdownServices {

 //--Client--//
 List<CarBreakdownDto> getAllBreakdownByCarSortDesc(String login);

 List<CarBreakdownDto> getAllBreakdownByCarIdLogin(UUID carUUID, String login);

 List<CarBreakdownDto> getAllBreakdownByCarIdLoginSort(UUID carUUID, String login) ;

 List<CarBreakdownDto> getAllBreakdownByCarAndStateSortDesc(UUID carUUID, State state, String login);
 //--Client--//

 //--Master--//
 boolean addBreakdownOnMaster(CarBreakdownDto carBreakdownForm,UUID idOrders,String login);

 List<CarBreakdownDto> getAllBreakDownByCarIdOnMaster(UUID carId);

 boolean updateBreakdownOnMaster(CarBreakdownDto carBreakdownForm,String name) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakDownBOnMaster(String name) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakDownBOnMaster(String name,UUID id) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakDownOnCar(UUID id) throws SaveSearchErrorException;
 //--Master--//


}
