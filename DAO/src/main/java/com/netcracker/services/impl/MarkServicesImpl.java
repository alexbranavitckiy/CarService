package com.netcracker.services.impl;

import com.netcracker.car.Mark;
import com.netcracker.repository.MarkRepository;
import com.netcracker.services.MarkServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class MarkServicesImpl implements MarkServices {

 private final MarkRepository markRepository;

 @Autowired
 MarkServicesImpl(MarkRepository markRepository) {
  this.markRepository = markRepository;
 }

 @Override
 public List<Mark> getAllMark() {
  return markRepository.getAllBy();
 }

 @Override
 public List<Mark> getMarkById(UUID uuid) {
  Optional<Mark> mark = markRepository.findById(uuid);
  if (mark.isEmpty()) return new ArrayList<>();
  return List.of(mark.get());
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
 public boolean deleteMark(Mark mark) {
  try {
   markRepository.delete(mark);
   return true;
  } catch (Exception e) {
   log.info(e.getMessage());
  }
  return false;
 }

}
