package com.netcracker.repository;

import com.netcracker.car.Mark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MarkRepository extends PagingAndSortingRepository<Mark, UUID> {

 @Query(value = "SELECT * FROM mark WHERE(name like :regex)", nativeQuery = true)
 Page<Mark> searchMark(String regex,Pageable nextPage);

 Page<Mark> getAllBy(Pageable nextPage);

 Optional<Mark> findById(UUID id);

 boolean existsById(UUID uuid);
}
