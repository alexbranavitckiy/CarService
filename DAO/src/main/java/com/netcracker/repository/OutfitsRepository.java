package com.netcracker.repository;

import com.netcracker.order.Orders;
import com.netcracker.outfit.Outfit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OutfitsRepository extends PagingAndSortingRepository<Outfit, UUID> {

 Optional<Outfit> getAllById(UUID id);

 List<Outfit> findAllBy();

List<Outfit> getOutfitsOrderByDateStart(Date dateStart);

}
