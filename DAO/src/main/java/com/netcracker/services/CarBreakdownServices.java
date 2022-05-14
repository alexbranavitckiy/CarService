package com.netcracker.services;

import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.breakdown.State;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface CarBreakdownServices {

 //--Client--//
 List<CarBreakdownDto> getAllBreakdownByCarSortDesc(int offset,
                                                    int limit,String login) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakdownByCarIdLogin(UUID carUUID, String login) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakdownByCarIdLoginSort(UUID carUUID, String login) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakdownByCarAndStateSortDesc(UUID carUUID, State state, String login)
  throws SaveSearchErrorException;
 //--Client--//

 //--Master--//
 boolean addBreakdownOnMaster(CarBreakdownDto carBreakdownForm, UUID idOrders, String login);

 List<CarBreakdownDto> getAllBreakDownByCarIdOnMaster(UUID carId) throws SaveSearchErrorException;

 boolean updateBreakdownOnMaster(CarBreakdownDto carBreakdownForm, String name) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakDownBOnMaster(String name) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakDownBOnMaster(String name, UUID id) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakDownOnCar(UUID id) throws SaveSearchErrorException;
 //--Master--//

 boolean updateBreakdownOnMasterR(CarBreakdownDto carBreakdownForm, String login) throws SaveSearchErrorException;

 List<CarBreakdownDto> getAllBreakDownBOnMasterR(String name, int offset, int limit) throws SaveSearchErrorException;
}
