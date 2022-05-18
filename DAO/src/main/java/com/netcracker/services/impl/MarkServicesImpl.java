package com.netcracker.services.impl;

import com.netcracker.DTO.car.MarkDto;
import com.netcracker.DTO.convectror.MapperDto;
import com.netcracker.DTO.errs.SaveSearchErrorException;
import com.netcracker.car.Mark;
import com.netcracker.repository.MarkRepository;
import com.netcracker.services.MarkServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MarkServicesImpl implements MarkServices {

 private final MarkRepository markRepository;
 private final MapperDto<MarkDto, Mark> mapper;

 @Autowired
 MarkServicesImpl(MapperDto<MarkDto, Mark> mapper, MarkRepository markRepository) {
  this.markRepository = markRepository;
  this.mapper = mapper;
 }

 @Override
 public List<MarkDto> getAllMark(Integer offset, Integer limit) throws SaveSearchErrorException {
  try {
   Pageable nextPage = PageRequest.of(offset, limit);
   return markRepository.getAllBy(nextPage).stream()
    .map(mapper::toDto)
    .collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("An error occurred while searching:" + e.getMessage(), "search");
  }
 }

 @Override
 public Optional<Mark> markById(UUID uuid) {
  return markRepository.findById(uuid);
 }

 @Override
 public List<Mark> getMarkById(UUID uuid) {
  Optional<Mark> mark = markRepository.findById(uuid);
  if (mark.isEmpty()) return new ArrayList<>();
  return List.of(mark.get());
 }


 @Override
 public void metadataMark(UUID uuid) throws SaveSearchErrorException {
  if (markRepository.existsById(uuid)) {
   return;
  }
  throw new SaveSearchErrorException("Entered data of a non-existent mark.", "mark");
 }

 @Override
 public List<MarkDto> getSearchMark(String regex, Integer offset, Integer limit) throws SaveSearchErrorException {
  try {
   Pageable nextPage = PageRequest.of(offset, limit);
   return markRepository.searchMark("%" + regex + "%", nextPage).stream()
    .map(mapper::toDto)
    .collect(Collectors.toList());
  } catch (Exception e) {
   throw new SaveSearchErrorException("An error occurred while searching:" + e.getMessage(), "regex");
  }
 }

 @Override
 public boolean addMark(Mark mark) {
  try {
   markRepository.save(mark);
   return true;
  } catch (Exception e) {
   log.info(e.getMessage());
  }
  return false;
 }

 @Override
 public boolean deleteMark(UUID mark) {
  try {
   markRepository.deleteById(mark);
   return true;
  } catch (Exception e) {
   log.info(e.getMessage());
  }
  return false;
 }

}
