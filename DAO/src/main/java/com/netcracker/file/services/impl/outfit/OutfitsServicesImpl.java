package com.netcracker.file.services.impl.outfit;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.outfit.Outfit;
import com.netcracker.file.services.CRUDServices;
import com.netcracker.file.FileService;
import com.netcracker.OutfitsServices;
import com.netcracker.file.services.impl.CRUDServicesImpl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutfitsServicesImpl implements OutfitsServices {

    private CRUDServices<Outfit, UUID> crudServices = new CRUDServicesImpl<>();

    private FileService fileService = new FileService();

    public OutfitsServicesImpl() {
    }

    @Override
    public List<Outfit> getAllOutfits() throws EmptySearchException {
        return crudServices.getAll(fileService.getOutfit(), Outfit[].class);
    }

    @Override
    public boolean addObjectInOutfits(Outfit o) {
        return this.crudServices.addObject(o, fileService.getOutfit(), Outfit[].class);
    }

    @Override
    public boolean updateOutfit(Outfit outfit) {
        return crudServices.updateObject(outfit,
                new File(FileService.USER_PATH), Outfit[].class);
    }

}
