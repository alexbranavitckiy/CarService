package com.netcracker.servisec;

import com.netcracker.errors.WritingException;
import com.netcracker.user.Client;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface CRUDServices {



    boolean addObject(Object o, File file) throws WritingException;

    boolean deleteObjectById(String id, File file);

    boolean updateObject(Object o, String id, File file) ;


}
