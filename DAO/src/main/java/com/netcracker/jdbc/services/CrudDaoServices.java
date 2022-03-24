package com.netcracker.jdbc.services;

import com.netcracker.EntityId;
import com.netcracker.errors.PersistException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface CrudDaoServices<T extends EntityId<K>, K extends Serializable>  {

  boolean addObject(T object) throws PersistException;

  Optional<T> getById(K key) throws PersistException;

  boolean update(T object) throws PersistException;

  boolean delete(T object) throws PersistException;

  List<T> getAll() throws PersistException, SQLException;

}

