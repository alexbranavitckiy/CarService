package com.netcracker.repository;

import com.netcracker.DTO.clients.ClientsDto;
import com.netcracker.user.Clients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientsRepository extends CrudRepository<Clients, UUID> {


 Optional<Clients> getAllByLogin(String name);

 Optional<ClientsDto> getByLogin(String name);

 Optional<ClientsDto> getByName(String name);

 List<ClientsDto> getAllBy();

}
