package com.netcracker.jdbc.services.impl.outfit;

import com.netcracker.OutfitsServices;
import com.netcracker.jdbc.services.CrudDao;
import com.netcracker.jdbc.services.impl.OutfitsDaoImpl;
import com.netcracker.outfit.Outfit;
import lombok.SneakyThrows;

import java.util.List;
import java.util.UUID;

public class OutfitsDaoServicesImpl implements OutfitsServices {

    private final CrudDao<Outfit, UUID> crudServices = new OutfitsDaoImpl();

    @Override
    @SneakyThrows
    public List<Outfit> getAllOutfits() {
        return crudServices.getAll();
    }

    @Override
    @SneakyThrows
    public boolean addObjectInOutfits(Outfit o) {
        return this.crudServices.addObject(o);
    }

    @Override
    @SneakyThrows
    public boolean updateOutfit(Outfit outfit) {
        return crudServices.update(outfit);
    }

}
