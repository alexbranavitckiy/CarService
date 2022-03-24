package com.netcracker.jdbc.services.impl;

import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.TemplateJDBCDao;
import com.netcracker.marka.CarClient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CarClientDaoImpl extends TemplateJDBCDao<CarClient, UUID> {


    @Override
    public String getSelectQuery() {
        return "SELECT id, id_clients, id_breakdown, summar, ear, run, metadatacar , descriptions FROM public.car_clients;";
    }

    @Override
    public String getSelectByIdQuery() {
        return "SELECT id, id_clients, id_breakdown, summar, ear, run, metadatacar , descriptions FROM public.car_clients where  id=?;";
    }

    @Override
    public String getCreateQuery() {
        return " INSERT INTO public.car_clients(id, id_clients, id_breakdown, summar, ear, run, metadatacar , descriptions) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE public.car_clients SET id=?, id_clients=?, id_breakdown=?, summar=?, ear=?, run=?, metadatacar=? , descriptions=?   where  id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM public.car_clients WHERE id=?;";
    }



    @Override
    protected List<CarClient> parseResultSet(ResultSet rs) throws PersistException {
        List<CarClient> carClients = new ArrayList<>();
        try {
            while (rs.next()) {
                CarClient carClient = CarClient.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .summer(rs.getString("summar"))
                        .ear(rs.getString("ear"))
                        .run(rs.getString("run"))
                        .metadataCar(rs.getString("metadatacar"))
                        .carBreakdowns(List.of((UUID) rs.getObject("id_breakdown")))
                        .descriptions(rs.getString("descriptions"))
                        .id_clients((UUID) rs.getObject("id_clients"))
                        .build();
                carClients.add(carClient);
            }
            return carClients;
        } catch (Exception e) {
            throw new PersistException(e);
        }

    }



    @Override
    protected void prepareStatementForInsert(PreparedStatement preparedStatement, CarClient carClient)
            throws PersistException {
        try {
            addQuery(preparedStatement, carClient);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }


    @Override
    protected void prepareStatementForUpdate(PreparedStatement preparedStatement, CarClient carClient)
            throws PersistException {
        try {
            addQuery(preparedStatement, carClient);
            preparedStatement.setObject(9, carClient.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    private void addQuery(PreparedStatement preparedStatement, CarClient carClient) throws SQLException {
        preparedStatement.setObject(1, carClient.getId());
        preparedStatement.setObject(2, carClient.getId_clients());
        preparedStatement.setObject(3, carClient.getCarBreakdowns().get(0));
        preparedStatement.setObject(4, carClient.getSummer());
        preparedStatement.setObject(5, carClient.getEar());
        preparedStatement.setObject(6, carClient.getRun());
        preparedStatement.setObject(7, carClient.getMetadataCar());
        preparedStatement.setObject(8, carClient.getDescriptions());
    }
}