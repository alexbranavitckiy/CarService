package com.netcracker;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.outfit.Outfit;
import java.util.List;

public interface OutfitsServices {

  List<Outfit> getAllOutfits() throws EmptySearchException;

  boolean addObjectInOutfits(Outfit o);

  boolean updateOutfit(Outfit outfit);

}
