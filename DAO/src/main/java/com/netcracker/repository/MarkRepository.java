package com.netcracker.repository;

import com.netcracker.car.Mark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Repository
public interface MarkRepository extends CrudRepository<Mark, UUID> {

 @Query(value = "SELECT * FROM mark WHERE(name like :regex)", nativeQuery = true)
 List<Mark> searchMark(String regex);

 List<Mark> getAllBy();

 Optional<Mark> findById(UUID id);

 boolean existsById(UUID uuid);
}
