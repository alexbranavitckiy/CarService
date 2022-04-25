package com.netcracker.repository;

import com.netcracker.DTO.errs.SaveErrorException;
import com.netcracker.car.Mark;
import com.netcracker.user.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MarkRepository  extends CrudRepository<Mark, UUID> {

 List<Mark> getAllBy();

 Optional<Mark> findById(UUID id);

 Optional<Mark> getById(UUID uuid);

}
