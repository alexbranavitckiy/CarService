package com.netcracker.servisec.Impl.outfit;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.outfit.Outfit;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.Impl.CRUDServicesImpl;
import com.netcracker.servisec.OutfitsServices;
import java.io.File;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutfitsServicesImpl implements OutfitsServices {

  private final CRUDServices<Outfit> searchServices = new CRUDServicesImpl<>();
  private final FileService fileService = new FileService();


  @Override
  public List<Outfit> getAllOutfits() throws EmptySearchException {
    return searchServices.getAll(fileService.getOutfit(), Outfit[].class);
  }

  @Override
  public boolean addObjectInOutfits(Outfit o) {
    return this.searchServices.addObject(o, fileService.getOutfit(), Outfit[].class);
  }

  @Override
  public boolean updateOutfit(Outfit outfit) {
    return searchServices.updateObject(outfit,
        new File(FileService.USER_PATH), Outfit[].class);
  }

}
