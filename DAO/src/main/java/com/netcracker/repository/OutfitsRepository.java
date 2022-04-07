package com.netcracker.repository;

import com.netcracker.outfit.Outfit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutfitsRepository extends CrudRepository<Outfit, UUID> {
}
