package com.netcracker.repository;

import com.netcracker.user.Master;
import com.netcracker.user.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.management.relation.RoleResult;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MasterRepository extends PagingAndSortingRepository<Master, UUID> {

 Optional<Master> getAllByLogin(String name);

 List<Master> getAllByRole(Role role);

 @Transactional
 @Modifying
 @Query("update master c set c.name = ?1, c.description = ?2, c.homeAddress = ?3  ,c.education=?4 where c.login = ?5")
 int updateDate(String name, String description, String homeAddress, String education, String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update master c set  c.password = ?1 where c.login = ?2")
 int updatePassword(String password, String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update master c set  c.login = ?1 where c.login = ?2")
 int updateLogin(String password, String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update master c set  c.phone = ?1 where c.login = ?2")
 int updatePhone(String phone, String login);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update master c set  c.mail = ?1 where c.login = ?2")
 int updateEmail(String email, String login);

 @Transactional
 @Modifying
 @Query(value =
  "insert into master (id,description,education,home_address,login, mail,name,password,phone,qualification,role)" +
   " values (:id,:description,:education, :home_address, :login, :mail, :name, :password, :phone, :qualification," +
   " :role)", nativeQuery = true)
 int createMaster(@Param("id") UUID id, @Param("mail") String mail, @Param("description") String description,
                  @Param("education") String education,
                  @Param("home_address") String home_address, @Param("login") String login,
                  @Param("name") String name, @Param("password") String password, @Param("phone") String phone,
                  @Param("qualification") String qualification, @Param("role") String role);


}