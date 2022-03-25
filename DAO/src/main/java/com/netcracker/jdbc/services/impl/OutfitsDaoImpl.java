package com.netcracker.jdbc.services.impl;

import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.TemplateJDBCDao;
import com.netcracker.outfit.Outfit;
import lombok.extern.slf4j.Slf4j;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
public class OutfitsDaoImpl extends TemplateJDBCDao<Outfit, UUID> {

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM public.outfits;";
    }

    @Override
    public String getSelectByIdQuery() {
        return "SELECT * FROM public.outfits where id=?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO public.outfits(id, name_outfits, descriptions, start_date, end_date, price_sum, state_outfits) VALUES (?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE public.outfits SET id=?, name_outfits=?, descriptions=?, start_date=?, end_date=?, price_sum=?, state_outfits=? where id=?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM public.outfits WHERE id=?;";
    }

    @Override
    protected List<Outfit> parseResultSet(ResultSet rs) throws PersistException {
        List<Outfit> masters = new ArrayList<>();
        try {
            while (rs.next()) {
                Outfit master = Outfit.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .name(rs.getString("name_outfits"))
                        .descriptions(rs.getString("descriptions"))
                        .dateStart((Date) rs.getObject("start_date"))
                        .dateEnt((Date) rs.getObject("end_date"))
                        .price(rs.getDouble("price_sum"))
                        .stateOutfit((UUID) rs.getObject("state_outfits"))
                        .build();
                masters.add(master);
            }
            return masters;
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Outfit outfit)
            throws PersistException {
        addQuery(statement, outfit);
    }

    private void addQuery(PreparedStatement statement, Outfit outfit) throws PersistException {
        try {
            statement.setObject(1, outfit.getId());
            statement.setObject(2, outfit.getName());
            statement.setObject(3, outfit.getDescriptions());
            statement.setObject(4, outfit.getDateStart());
            statement.setObject(5, outfit.getDateEnt());
            statement.setObject(6, outfit.getPrice());
            statement.setObject(7, outfit.getStateOutfit());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Outfit outfit)
            throws PersistException {
        addQuery(statement, outfit);
        try {
            statement.setObject(8, outfit.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }


}
