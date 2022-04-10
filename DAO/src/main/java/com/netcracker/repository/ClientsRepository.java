package com.netcracker.repository;

import com.netcracker.DTO.ClientsDto;
import com.netcracker.user.Clients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientsRepository extends CrudRepository<Clients, UUID> {


 Optional<Clients> getAllByName(String name);

 Optional<ClientsDto> getByName(String name);

}
