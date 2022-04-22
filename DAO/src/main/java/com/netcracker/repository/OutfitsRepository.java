package com.netcracker.repository;

import com.netcracker.outfit.Outfit;
import com.netcracker.outfit.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OutfitsRepository extends PagingAndSortingRepository<Outfit, UUID> {

 @Query(value = "SELECT o.id, o.date_end, o.date_start, o.description, o.name, o.price, o.state_outfit, o.id_master FROM public.outfit o  right join public.master m on (m.login=1? and o.state_outfit=2?) order by o.date_start desc;", nativeQuery = true)
 List<Outfit> getAllByStateOutfitAndMasterLogin(State state,String login);

}
