package com.netcracker.jdbc.services.impl;

import com.netcracker.errors.PersistException;
import com.netcracker.file.services.impl.CRUDServicesImpl;
import com.netcracker.jdbc.ConnectorDB;
import com.netcracker.jdbc.services.CrudDaoServices;
import com.netcracker.user.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class ClientDaoImpl extends CRUDServicesImpl<Client> implements
  CrudDaoServices<Client, UUID> {

  @Override
  public List<Client> getAll() throws SQLException {
    String sql = "SELECT id, password, login, ferst_name, role_user, last_name, id_role_use, email, phone, descriptions FROM public.clients;";
    List<Client> list = new ArrayList<Client>();
    try (Connection connection = ConnectorDB.getConnection()) {
      ResultSet rs = connection.prepareStatement(sql).executeQuery();
      while (rs.next()) {
        Client g = new Client();
        g.setLogin(rs.getString("login"));
        g.setDescription(rs.getString("descriptions"));
        list.add(g);
      }
    }
    return list;
  }

  @Override
  public Optional<Client> getById(UUID id) throws PersistException {
    Client client = new Client();
    String sql = "SELECT id, password, login, ferst_name, role_user, last_name, id_role_use, email, phone, descriptions FROM public.clients where  id=?;";
    try (Connection connection = ConnectorDB.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setObject(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        client.setLogin(rs.getString("login"));
        client.setDescription(rs.getString("descriptions"));
      }
    } catch (Exception e) {
      throw new PersistException(e);
    }
    return Optional.of(client);
  }

  @Override
  public boolean create(Client client) throws PersistException {
    String sql = "INSERT INTO public.clients(id, password, login, ferst_name, role_user, last_name, id_role_use, email, phone, descriptions)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    try (Connection connection = ConnectorDB.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
    } catch (Exception e) {
      throw new PersistException(e);
    }
    return false;
  }

  @Override
  public boolean addObject(Client client) throws PersistException {
    String sql = "INSERT INTO public.clients(id, password, login, ferst_name, role_user, last_name, id_role_use, email, phone, descriptions)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    try (Connection connection = ConnectorDB.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
    } catch (Exception e) {
      throw new PersistException(e);
    }
    return false;
  }


  @Override
  public boolean update(Client object) throws PersistException {

    return false;
  }

  @Override
  public boolean delete(Client object) throws PersistException {
    return false;
  }
}
