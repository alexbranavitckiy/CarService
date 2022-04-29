package com.netcracker.services;


import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.car.Mark;

import java.util.List;
import java.util.UUID;

public interface MarkServices {

 List<Mark> getAllMark();

 boolean addMark(Mark mark);

 boolean deleteMark(Mark mark);

 List<Mark> getMarkById(UUID uuid);

 boolean metadataMark(UUID uuidMark) throws SaveSearchErrorException;

}
