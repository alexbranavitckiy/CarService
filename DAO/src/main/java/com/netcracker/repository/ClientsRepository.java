package com.netcracker.repository;

import com.netcracker.user.Clients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientsRepository extends CrudRepository<Clients, UUID> {
}
