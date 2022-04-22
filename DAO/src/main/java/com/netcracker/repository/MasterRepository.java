package com.netcracker.repository;

import com.netcracker.user.Master;
import com.netcracker.user.MasterReceiver;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MasterRepository extends CrudRepository<Master, UUID> {
 Optional<Master> getAllById(UUID id);

 Optional<Master> getAllByLogin(String name);

 @Transactional
 @Modifying(clearAutomatically = true)
 @Query("update master c set c.login = ?1, c.password = ?2 where c.login = ?3")
 int updatePassword(String newLogin, String password, String login);

 List<Master> getAllBy();

 @Transactional
 @Modifying
 @Query("update master c set c.name = ?1, c.phone = ?2 , c.mail = ?3, c.description = ?4, c.homeAddress = ?4 ,c.qualification=?5 ,c.education=?6 where c.login = ?7")
 int updateMaster(String name, String phone, String email, String description, String homeAddress, String education, String login);


}
