package com.netcracker.servisec.Impl;

import com.netcracker.errors.EmptySearchException;
import junit.framework.TestCase;

import java.io.File;

public class CRUDServicesClientImplTest extends TestCase {

    private final CRUDServicesClientImpl crudServicesClient = new CRUDServicesClientImpl();
    private final OrderServicesImpl orderServices = new OrderServicesImpl();

    public void testGetAll() {
        try {
            crudServicesClient.getAll(new File(""));
            orderServices.getAll(new File(""));
        } catch (EmptySearchException | NullPointerException e) {
            assertEquals("No orders available", e.getMessage());
        }
    }

}