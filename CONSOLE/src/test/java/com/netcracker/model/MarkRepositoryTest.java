package com.netcracker.model;


import com.netcracker.CarServiceApplication;
import com.netcracker.repository.CarBreakdownRepository;
import com.netcracker.repository.CarClientRepository;
import com.netcracker.repository.ClientsRepository;
import com.netcracker.repository.MarkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CarServiceApplication.class)
@Transactional
public class MarkRepositoryTest {


 private final MarkRepository markRepository;

 @Autowired
 MarkRepositoryTest(MarkRepository markRepository) {
  this.markRepository = markRepository;
 }

 @Test
 void searchMark() {
  Assertions.assertNotNull(markRepository.searchMark("", Pageable.unpaged()));
 }

}
