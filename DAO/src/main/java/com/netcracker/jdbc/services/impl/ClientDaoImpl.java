package com.netcracker.jdbc.services.impl;

import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;
import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.ConnectorDB;
import com.netcracker.jdbc.services.CrudDaoServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.SneakyThrows;

public class ClientDaoImpl implements CrudDaoServices<Client, UUID> {

  @Override
  @SneakyThrows
  public List<Client> getAll() throws SQLException {
    String sql = "SELECT id, password, login, ferst_name, role_user, last_name,  email, phone, descriptions FROM public.clients;";
    List<Client> list = new ArrayList<>();
    try (Connection connection = ConnectorDB.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        list.add(this.takeObject(rs).get());
      }
      preparedStatement.close();
    }
    return list;
  }

  @Override
  public Optional<Client> getById(UUID id) throws PersistException {
    Client client = new Client();
    String sql = "SELECT id, password, login, ferst_name, role_user, last_name,  email, phone, descriptions FROM public.clients where  id=?;";
    try (Connection connection = ConnectorDB.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setObject(1, id);
      ResultSet rs = preparedStatement.executeQuery();
      while (rs.next()) {
        client.setLogin(rs.getString("login"));
        client.setDescription(rs.getString("descriptions"));
      }
      preparedStatement.close();
    } catch (Exception e) {
      throw new PersistException(e);
    }
    return Optional.of(client);
  }


  @Override
  public boolean addObject(Client client) throws PersistException {
    String sql = "INSERT INTO clients(id, password, login, ferst_name, role_user, last_name, email, phone, descriptions)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    try (Connection connection = ConnectorDB.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      this.insertDate(preparedStatement, client);
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (Exception e) {
      throw new PersistException(e);
    }
    return false;
  }


  @Override
  public boolean update(Client client) throws PersistException {
    if (this.getById(client.getId()).isPresent()) {
      try (Connection connection = ConnectorDB.getConnection()) {
        PreparedStatement preparedStatement = connection.prepareStatement(
          "UPDATE clients SET id=?, password=?, login=?, ferst_name=?, role_user=?, last_name=?, email=?, phone=?, descriptions=? WHERE clients.id=?;");
        this.insertDateForUpdate(preparedStatement, client);
        preparedStatement.executeUpdate();
        preparedStatement.close();
      } catch (Exception e) {
        throw new PersistException(e);
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean delete(Client client) throws PersistException {
    String sql = "DELETE FROM public.clients WHERE clients.id= ?;";
    try (Connection connection = ConnectorDB.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setObject(1, client.getId());
      preparedStatement.executeUpdate();
      preparedStatement.close();
      return true;
    } catch (Exception e) {
      throw new PersistException(e);
    }
  }

  private Optional<Client> takeObject(ResultSet rs)
    throws PersistException {
    Client client = null;
    try {
      //  UUID.nameUUIDFromBytes(new String(rs.getString("role_user")).getBytes(StandardCharsets.UTF_8));
      while (rs.next()) {
        client = Client.builder()
          .id(UUID.fromString(rs.getString("id")))
          .description(rs.getString("descriptions"))
          .phone(rs.getString("phone"))
          .login(rs.getString("login"))
          .password(rs.getString("password"))
          .name(rs.getString("ferst_name") + rs.getString("last_name"))
          .email(rs.getString("email"))
          .roleuser(RoleUser.REGISTERED)
          .build();
      }
      return Optional.ofNullable(client);
    } catch (Exception e) {
      throw new PersistException(e);
    }
  }


  private void insertDateForUpdate(PreparedStatement preparedStatement, Client client)
    throws PersistException {
    try {
      preparedStatement.setObject(1, client.getId());
      preparedStatement.setString(2, client.getPassword());
      preparedStatement.setString(3, client.getLogin());
      preparedStatement.setString(4, client.getName());//TODO!!
      preparedStatement.setObject(5, client.getRoleuser().name());
      preparedStatement.setString(6, client.getName());//TODO!!
      preparedStatement.setString(7, client.getEmail());
      preparedStatement.setObject(8, client.getPhone());
      preparedStatement.setString(9, client.getDescription());
      preparedStatement.setObject(10, client.getId());
    } catch (Exception e) {
      throw new PersistException(e);
    }
  }

  private void insertDate(PreparedStatement preparedStatement, Client client)
    throws PersistException {
    try {
      preparedStatement.setObject(1, client.getId());
      preparedStatement.setString(2, client.getPassword());
      preparedStatement.setString(3, client.getLogin());
      preparedStatement.setString(4, client.getName());//TODO!!
      preparedStatement.setObject(5, client.getRoleuser().name());//.getId()
      preparedStatement.setString(6, client.getName());//TODO!!
      preparedStatement.setString(7, client.getEmail());
      preparedStatement.setObject(8, client.getPhone());
      preparedStatement.setString(9, client.getDescription());
    } catch (Exception e) {
      throw new PersistException(e);
    }
  }


}
