package com.netcracker.servisec.Impl;

import com.netcracker.errors.EmptySearchException;
import com.netcracker.servisec.Impl.client.CRUDServicesClientImpl;
import com.netcracker.servisec.Impl.order.CRUDOrderServicesImpl;
import junit.framework.TestCase;

import java.io.File;

public class CRUDServicesClientImplTest extends TestCase {

    private final CRUDServicesClientImpl crudServicesClient = new CRUDServicesClientImpl();
    private final CRUDOrderServicesImpl orderServices = new CRUDOrderServicesImpl();

    public void testGetAll() {
        try {
            crudServicesClient.getAll(new File(""));
            orderServices.getAll(new File(""));
        } catch (EmptySearchException | NullPointerException e) {
            assertEquals("No orders available", e.getMessage());
        }
    }

}