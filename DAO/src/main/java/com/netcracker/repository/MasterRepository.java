package com.netcracker.repository;

import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MasterRepository extends CrudRepository<Master, UUID> {
 Optional<Master> getAllById(UUID id);

 Optional<Master> getAllByLogin(String name);
}
