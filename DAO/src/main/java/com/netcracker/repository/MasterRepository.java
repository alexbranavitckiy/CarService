package com.netcracker.repository;

import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MasterRepository extends CrudRepository<Master, UUID> {
}
