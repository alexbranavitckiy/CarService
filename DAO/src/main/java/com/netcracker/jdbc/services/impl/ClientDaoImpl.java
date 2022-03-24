package com.netcracker.jdbc.services.impl;

import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.TemplateJDBCDao;
import com.netcracker.user.Client;
import com.netcracker.user.RoleUser;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class ClientDaoImpl extends TemplateJDBCDao<Client, UUID> {

    @Override
    public String getSelectQuery() {
        return "SELECT id, password, login, ferst_name, role_user, last_name,  email, phone, descriptions FROM public.clients;";
    }

    @Override
    public String getSelectByIdQuery() {
        return "SELECT id, password, login, ferst_name, role_user, last_name,  email, phone, descriptions FROM public.clients where  id=?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO clients(id, password, login, ferst_name, role_user, last_name, email, phone, descriptions)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE clients SET id=?, password=?, login=?, ferst_name=?, role_user=?, last_name=?, email=?, phone=?, descriptions=? WHERE clients.id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM public.clients WHERE clients.id= ?;";
    }

    @Override
    protected List<Client> parseResultSet(ResultSet rs) throws PersistException {
        List<Client> clients = new ArrayList<>();
        try {
            while (rs.next()) {
                Client client = Client.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .description(rs.getString("descriptions"))
                        .phone(rs.getString("phone"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .name(rs.getString("ferst_name") + rs.getString("last_name"))
                        .email(rs.getString("email"))
                        .roleUser((UUID) rs.getObject("role_user"))
                        .build();
                clients.add(client);
            }
            return clients;
        } catch (Exception e) {
            throw new PersistException(e);
        }

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, Client client)
            throws PersistException {
        try {
            preparedStatement.setObject(1, client.getId());
            preparedStatement.setString(2, client.getPassword());
            preparedStatement.setString(3, client.getLogin());
            preparedStatement.setString(4, client.getName());//TODO!!
            preparedStatement.setObject(5, client.getRoleUser());//.getId()
            preparedStatement.setString(6, client.getName());//TODO!!
            preparedStatement.setString(7, client.getEmail());
            preparedStatement.setObject(8, client.getPhone());
            preparedStatement.setString(9, client.getDescription());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Client client)
            throws PersistException {
        try {
            statement.setObject(1, client.getId());
            statement.setString(2, client.getPassword());
            statement.setString(3, client.getLogin());
            statement.setString(4, client.getName());//TODO!!
            statement.setObject(5, client.getRoleUser());
            statement.setString(6, client.getName());//TODO!!
            statement.setString(7, client.getEmail());
            statement.setObject(8, client.getPhone());
            statement.setString(9, client.getDescription());
            statement.setObject(10, client.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
}