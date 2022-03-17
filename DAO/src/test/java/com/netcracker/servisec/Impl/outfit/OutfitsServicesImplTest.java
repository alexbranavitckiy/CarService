package com.netcracker.servisec.Impl.outfit;

import static org.mockito.Mockito.verify;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.outfit.Outfit;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.Impl.CRUDServicesImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.when;

public class OutfitsServicesImplTest {

  @Mock
  private CRUDServices<Outfit> crudServices = new CRUDServicesImpl<>();

  private final Outfit outfit = Outfit.builder().id(UUID.randomUUID()).build();

  @Spy
  private List<Outfit> outfitList = new ArrayList<>(List.of(outfit, outfit));

  @InjectMocks
  private OutfitsServicesImpl outfitsServices = new OutfitsServicesImpl();


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testGetAllOutfits_Should_Return_True() throws EmptySearchException {
    when(crudServices.getAll(Mockito.anyObject(), Mockito.any())).thenReturn(outfitList);
    Assert.assertEquals(outfitsServices.getAllOutfits(), outfitList);
    verify(outfitsServices.getAllOutfits());
    Assert.assertEquals(outfitsServices.getAllOutfits(), outfitList);
  }

  @Test
  public void testAddObjectInOutfits_Should_Return_True() {
    when(crudServices.addObject(Mockito.anyObject(), Mockito.any(), Mockito.any())).thenReturn(true);
    Assert.assertTrue(outfitsServices.addObjectInOutfits(outfit));
  }

  @Test
  public void testUpdateOutfit_Should_Return_True() {
    when(crudServices.updateObject(Mockito.anyObject(), Mockito.any(), Mockito.any())).thenReturn(true);
    Assert.assertTrue(outfitsServices.updateOutfit(outfit));
  }

  @Test
  public void testAddObjectInOutfits_Should_Return_False() {
    when(crudServices.addObject(null, new File(""),Outfit[].class)).thenReturn(false);
    Assert.assertFalse(outfitsServices.addObjectInOutfits(outfit));
  }

  @Test
  public void testUpdateOutfit_Should_Return_False() {
    when(crudServices.updateObject(null, new File(""),Outfit[].class)).thenReturn(false);
    Assert.assertFalse(outfitsServices.updateOutfit(null));
  }
}