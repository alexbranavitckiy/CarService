package com.netcracker.repository;

import com.netcracker.outfit.Outfit;
import com.netcracker.outfit.State;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OutfitsRepository extends PagingAndSortingRepository<Outfit, UUID> {

 @Query(value = "SELECT o.id, o.date_end, o.date_start, o.description, o.name, o.price, o.state_outfit, o.id_master FROM public.outfit o  right join public.master m on (m.login=?1 and o.state_outfit=?2) where  o.id is not null order by o.date_start desc;", nativeQuery = true)
 List<Outfit> getAllByStateOutfitAndMasterLogin(String state, String login);

 @Transactional
 @Modifying
 @Query(value = "UPDATE public.outfit SET state =?1,updated_date=?1  where outfit.id = ?2 and outfit.id_master in( select master.id from master  where master.login=?3))", nativeQuery = true)
 int startWorkMaster(String work, UUID uuid, String login);

 @Transactional
 @Modifying
 @Query(value = "UPDATE public.outfit SET date_end=?1,date_start=?2,  description=?3, name=?4  where outfit.state_outfit=?5  and outfit.id_master in(select master.id from master  where master.login=?6)", nativeQuery = true)
 int updateWorkMaster(Date dateEnd, Date dateStart, String description, String name, String state, String login);

}
