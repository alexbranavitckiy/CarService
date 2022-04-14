package com.netcracker.repository;

import com.netcracker.user.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ClientsRepository extends CrudRepository<Client, UUID> {

 Optional<Client> getAllByLogin(String name);

 Optional<Client> getByLogin(String name);

 Optional<Client> getByName(String name);

 List<Client> getAllBy();

 @Query(value = "SELECT  role ,login,password  FROM clients  where clients.login=?1 union SELECT  role ,login,password FROM master   where master .login=?2 union SELECT  role ,login,password  FROM master_receiver  where master_receiver .login=?3", nativeQuery = true)
 Map<String, Object> getMyM(String login, String login2, String login3);

}
