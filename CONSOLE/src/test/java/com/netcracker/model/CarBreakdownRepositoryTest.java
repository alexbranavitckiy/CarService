package com.netcracker.model;

import com.netcracker.CarServiceApplication;
import com.netcracker.car.Mark;
import com.netcracker.repository.CarBreakdownRepository;
import com.netcracker.repository.CarClientRepository;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.repository.MarkRepository;
import com.netcracker.user.RoleUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.Date;
import java.util.UUID;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CarServiceApplication.class)
@Transactional
class CarBreakdownRepositoryTest {

 private final CarBreakdownRepository carBreakdownRepository;
 private final ClientsRepository clientsRepository;
 private final CarClientRepository carClientRepository;
 private final MarkRepository markRepository;

 @Autowired
 CarBreakdownRepositoryTest(MarkRepository markRepository, CarClientRepository carClientRepository, CarBreakdownRepository carBreakdownRepository, ClientsRepository clientsRepository) {
  this.carBreakdownRepository = carBreakdownRepository;
  this.markRepository = markRepository;
  this.clientsRepository = clientsRepository;
  this.carClientRepository = carClientRepository;
 }

 @Test
 void getAllByLoginTest() {
  UUID uuid = UUID.randomUUID();
  clientsRepository.insertClient(uuid, "description",
   "email", "UUID.randomUUID()", "name", "password",
   "phone", RoleUser.REGISTERED.getCode());
  assertThat(clientsRepository.existsById(uuid)).isTrue();
  Mark mark = Mark.builder()
   .id(uuid)
   .name("name")
   .yearEnd(new Date())
   .yearEnd(new Date()).build();
  markRepository.save(mark);
  Assertions.assertEquals(1, carClientRepository.createCarOnLogin(uuid,
   "description", new Date(), "asdads", 12, "summary", "login", uuid));
 }

 @Test
 void getAllByCarBreakdownByStateAndSortDesc() {
  Assertions.assertNotNull(carBreakdownRepository.
   getAllByCarBreakdownByStateAndSortDesc(UUID.randomUUID(), "", ""));
 }

 @Test
 void getAllByCarBreakdownByIdCarAndLoginSortDesc() {
  Assertions.assertNotNull(carBreakdownRepository.
   getAllByCarBreakdownByIdCarAndLoginSortDesc("", UUID.randomUUID()));
 }

 @Test
 void getAllByCarBreakdownByIdCarAndLogin() {
  Assertions.assertNotNull(carBreakdownRepository.getAllByCarBreakdownByIdCarAndLogin("", UUID.randomUUID()));
 }

 @Test
 void getAllByCarClientId() {
  Assertions.assertNotNull(carBreakdownRepository.getAllByCarClientId(UUID.randomUUID()));
 }


 @Test
 void getAllByMaster() {
  Assertions.assertNotNull(carBreakdownRepository.getAllByMaster("Master"));
 }

 @Test
 void getAllByIdOnMaster() {
  Assertions.assertNotNull(carBreakdownRepository.getAllByIdOnMaster("Master", UUID.randomUUID()));
 }


}