package com.netcracker.services;

import com.netcracker.DTO.car.CarBreakdownDto;
import com.netcracker.DTO.car.CarBreakdownForm;
import com.netcracker.breakdown.State;

import java.util.List;
import java.util.UUID;

public interface CarBreakdownServices {

 //--Client--//
 List<CarBreakdownDto> getAllBreakdownByCarSortDesc(String login);

 List<CarBreakdownDto> getAllBreakdownByCarIdLogin(UUID carUUID, String login);

 List<CarBreakdownDto> getAllBreakdownByCarIdLoginSort(UUID carUUID, String login);

 List<CarBreakdownDto> getAllBreakdownByCarAndStateSortDesc(UUID carUUID, State state, String login);
 //--Client--//

 //--Master--//
 boolean addBreakdownOnMaster(CarBreakdownForm carBreakdownForm);

 List<CarBreakdownDto> getAllBreakDownByCarIdOnMaster(UUID carId);

 boolean updateBreakdownOnMaster(CarBreakdownForm carBreakdownForm);
 //--Master--//


}
