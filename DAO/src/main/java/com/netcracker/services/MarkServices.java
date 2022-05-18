package com.netcracker.services;


import com.netcracker.DTO.car.MarkDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.car.Mark;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MarkServices {

 List<MarkDto> getAllMark(Integer offset, Integer limit) throws SaveSearchErrorException;

 boolean addMark(Mark mark);

 boolean deleteMark(UUID mark);

 List<Mark> getMarkById(UUID uuid);

 void metadataMark(UUID uuidMark) throws SaveSearchErrorException;

 List<MarkDto> getSearchMark(String regex,Integer offset,Integer limit)  throws SaveSearchErrorException;

 Optional<Mark> markById(UUID uuid);
}
