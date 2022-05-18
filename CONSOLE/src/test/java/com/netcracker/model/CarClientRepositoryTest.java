package com.netcracker.model;

import com.netcracker.CarServiceApplication;
import com.netcracker.repository.CarBreakdownRepository;
import com.netcracker.repository.CarClientRepository;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.repository.MarkRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static java.util.UUID.randomUUID;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CarServiceApplication.class)
@Transactional
public class CarClientRepositoryTest {

 @Autowired
 private CarClientRepository carClientRepository;
 @Autowired
 private MarkRepository markRepository;

 @Test
 void testGetAllByLike() {
  Assertions.assertNotNull(carClientRepository.getAllByLike(""));
 }

 @Test
 void updateCarClientById() {
  Assertions.assertEquals(0, carClientRepository.updateCarClientById("", randomUUID(), ""));
 }

 @Test
 void updateCarClientByIdWithoutMachineNumber() {
  Assertions.assertEquals(0, carClientRepository
   .updateCarClientByIdWithoutMachineNumber(" ", new Date(), 12, randomUUID(), ""));
 }

 @Test
 void createCarOnLogin() {
  Assertions.assertEquals(1, carClientRepository
   .createCarOnLogin(UUID.randomUUID(), "description", new Date(), " ", 12,
    "str", "login", markRepository.getAllBy().get(1).getId()));
 }

 @Test
 void createCarOnMaster() {
  Assertions.assertEquals(1, carClientRepository
   .createCarOnMaster(UUID.randomUUID(), "description", new Date(), " ", 12,
    "str", markRepository.getAllBy().get(1).getId()));
 }

}
