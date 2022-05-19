package com.netcracker.services;

import com.netcracker.DTO.car.MarkDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.DTO.page.Paged;
import com.netcracker.car.Mark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MarkServices {

 List<MarkDto> getAllMark(Integer offset, Integer limit) throws SaveSearchErrorException;

 Page<Mark> getPageMark(Pageable pageable) throws SaveSearchErrorException;

 Paged<Mark> getPage(int pageNumber, int size);

 boolean addMark(Mark mark);

 boolean deleteMark(UUID mark);

 List<Mark> getMarkById(UUID uuid);

 void metadataMark(UUID uuidMark) throws SaveSearchErrorException;

 List<MarkDto> getSearchMark(String regex, Integer offset, Integer limit) throws SaveSearchErrorException;

 Optional<Mark> markById(UUID uuid);
}
