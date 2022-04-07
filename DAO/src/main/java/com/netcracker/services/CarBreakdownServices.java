package com.netcracker.services;

import com.netcracker.breakdown.CarBreakdown;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarBreakdownServices {

 List<CarBreakdown> getAllBreakdown() ;

 boolean addBreakdown(CarBreakdown carBreakdown);

 Optional<CarBreakdown> getBreakdownById(UUID uuid);

 boolean updateBreakdown(CarBreakdown carBreakdown);

 List<CarBreakdown> getAllBreakdownByCar(UUID uuid) ;

}
