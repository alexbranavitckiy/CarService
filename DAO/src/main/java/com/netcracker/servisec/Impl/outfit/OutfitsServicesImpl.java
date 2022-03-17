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

  private CRUDServices<Outfit> crudServices = new CRUDServicesImpl<>();

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
