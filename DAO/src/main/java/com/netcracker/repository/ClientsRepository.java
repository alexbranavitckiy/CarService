package com.netcracker.repository;

import com.netcracker.user.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;

@Repository
public interface ClientsRepository extends PagingAndSortingRepository<Client, UUID> {

 boolean existsByLogin(String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 void deleteAllByLogin(String login);

 boolean existsById(String login);

 boolean existsByEmail(String email);

 boolean existsByPassword(String pas);

 boolean existsByPhone(String phone);

 Optional<Client> getAllByLogin(String name);

 Optional<Client> getByName(String name);

 List<Client> getAllBy();

 @Query(value = "SELECT * FROM clients  WHERE (email like :searchClient) OR (name like :searchClient) " +
  "OR  (phone like :searchClient) or (login like :searchClient) ", nativeQuery = true)
 List<Client> getAllByLike(@Param("searchClient") String searchClient);

 @Query(value = "SELECT (COUNT(clients.login)+COUNT(master.login)) FROM clients  join master on" +
  " clients.login=?1 or master.login=?2", nativeQuery = true)
 int existsByLoginClientAndMaster(String login, String login2);

 @Query(value = "SELECT  role ,login,password  FROM clients  where clients.login=?1 union SELECT" +
  "  role ,login,password  FROM master   where master .login=?2", nativeQuery = true)
 Map<String, Object> getMyUser(String login, String login2);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update clients c set c.roleUser = 'REGISTERED', c.login = ?1, c.password = ?2 where c.login = ?3")
 int updatePasswordAndLogin(String newLogin, String password, String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update clients c set c.roleUser = 'REGISTERED',  c.password = ?1 where c.login = ?2")
 int updatePassword(String password, String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update clients c set c.roleUser = 'REGISTERED',  c.login = ?1 where c.login = ?2")
 int updateLogin(String password, String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update clients c set c.roleUser = 'REGISTERED',  c.phone = ?1 where c.login = ?2")
 int updatePhone(String phone, String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update clients c set c.roleUser = 'REGISTERED',  c.email = ?1 where c.login = ?2")
 int updateEmail(String email, String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update clients c set c.roleUser = 'REGISTERED',  c.name = ?1 where c.login = ?2")
 int updateName(String name, String login);

 @Transactional
 @Modifying
 @Query("update clients c set c.roleUser = 'REGISTERED', c.name = ?1, c.phone = ?2 , c.email = ?3, " +
  "c.description = ?4 where c.login = ?5")
 int updateClient(String name, String phone, String email, String description, String login);

 @Transactional
 @Modifying
 @Query(
  value =
   "insert into clients (id,description,email,login, name,password,phone,role) values" +
    " (:id,:description,:email, :login, :name, :password, :phone, :role)",
  nativeQuery = true)
 int insertClient(@Param("id") UUID id, @Param("description") String description, @Param("email") String email,
                  @Param("login") String login, @Param("name") String name,
                  @Param("password") String password, @Param("phone") String phone, @Param("role") String role);

}
