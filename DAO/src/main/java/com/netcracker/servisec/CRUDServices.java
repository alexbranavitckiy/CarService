package com.netcracker.servisec;

import com.netcracker.errors.EmptySearchException;
import java.io.File;
import java.util.List;

public interface CRUDServices<T> {

  List<T> getAll(File file, Class<T[]> object) throws EmptySearchException;

  boolean addObject(T addObject, File file, Class<T[]> typeObject);

  boolean deleteObjectById(T deleteObject, File file, Class<T[]> typeObject);

  boolean updateObject(T object, File file, Class<T[]> typeObject);

}
