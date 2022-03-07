package com.netcracker.servisec;

import com.netcracker.user.Client;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface CRUDServices {

    void FindObjectBy(Client o, File file);

    boolean addObject(Object o, File file);

    boolean deleteObjectById(String id, File file);

    boolean updateObject(Object o, String id, File file);


}
