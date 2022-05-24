package com.netcracker.repository;

import com.netcracker.outfit.Outfit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OutfitsRepository extends PagingAndSortingRepository<Outfit, UUID> {

 @Query(value = "SELECT * FROM public.outfit order by date_start desc;", nativeQuery = true)
 List<Outfit> getAllByOutfitByDateStartDesc();

 @Query(value = "SELECT o.id, o.date_end, o.date_start, o.description, o.name, o.state_outfit, o.id_master FROM " +
  "public.outfit o  right join public.master m on (m.login=?1 and o.state_outfit=?2) where  o.id is not null " +
  "order by o.date_start desc;", nativeQuery = true)
 List<Outfit> getAllByStateOutfitAndMasterLogin(String login, String state);



 @Transactional
 @Modifying
 @Query(value = "UPDATE public.outfit SET state_outfit =?1  where outfit.id = ?2 and outfit.id_master " +
  "in(select master.id from master  where master.login=?3)", nativeQuery = true)
 int startWorkMaster(String work, UUID uuid, String login);


 @Transactional
 @Modifying
 @Query(value = "UPDATE public.outfit SET id_master =?1  where id = ?2", nativeQuery = true)
 int updateMasterOnMasterR( UUID uuidMaster,UUID uuidOutfit);

 @Transactional
 @Modifying
 @Query(value = "UPDATE car_breakdown  set state='IMPORTANT'  where  state='IMPORTANT' and  id_outfit" +
  " in( SELECT id FROM outfit where  state_outfit='WORK' and id_master in( select id from master  where login=:login));"
  , nativeQuery = true)
 int checkingOutfit(String login);

 @Transactional
 @Modifying
 @Query(value = "UPDATE public.outfit SET state_outfit =?1,date_end=?2 where  outfit.state_outfit='WORK' " +
  " and outfit.id_master in( select master.id from master  where master.login=?3)", nativeQuery = true)
 int endWorkMaster(String state, Date date,String login);

 @Transactional
 @Modifying
 @Query(value = "UPDATE public.outfit SET  date_end=?1,date_start=?2,  description=?3, name=?4  where" +
  " outfit.state_outfit=?5  and outfit.id_master in(select id from master  where login=?6)", nativeQuery = true)
 int updateWorkMaster(Date dateEnd, Date dateStart, String description, String name, String state, String login);


 @Query(value = "SELECT * FROM public.outfit where date_end between :start and :end and date_start  between " +
  " :start and :end  and id_master = :master", nativeQuery = true)
 List<Outfit> getAllOutfit(Date start, Date end, UUID master);


}
