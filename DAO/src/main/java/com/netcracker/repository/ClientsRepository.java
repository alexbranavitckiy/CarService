package com.netcracker.repository;

import com.netcracker.user.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
public interface ClientsRepository extends CrudRepository<Client, UUID> {

 Optional<Client> getAllByLogin(String name);

 Optional<Client> getByLogin(String name);

 Optional<Client> getByName(String name);

 List<Client> getAllBy();

 @Query(value = "SELECT  role ,login,password  FROM clients  where clients.login=?1 union SELECT  role ,login,password  FROM master   where master .login=?2 union SELECT  role ,login,password  FROM master_receiver  where master_receiver .login=?3", nativeQuery = true)
 Map<String, Object> getMyUser(String login, String login2, String login3);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update clients c set c.login = ?1, c.password = ?2 where c.login = ?3")
 int updatePassword( String  newLogin ,String password, String login);


 @Transactional
 @Modifying
 @Query("update clients c set c.name = ?1, c.phone = ?2 , c.email = ?3, c.description = ?4 where c.login = ?5")
 int updateClient( String  name ,String phone,String email,String description,String login);

}
