package com.netcracker.servisec.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.netcracker.servisec.CRUDServices;
import com.netcracker.servisec.SearchServices;
import com.netcracker.user.User;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
public class CRUDServicesImpl implements CRUDServices {


    @Override
    public Optional<List<JsonNode>> getByName(String name, String field, ObjectMapper objectMapper, File file) throws IOException {
        try {
            if (file != null && file.exists()) {
                ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
                return Optional.of(arrayNode.findParents(field)
                        .stream()
                        .filter(x -> x.findValue(field).asText().equalsIgnoreCase(name))
                        .collect(Collectors.toList()));
            }
        } catch (IOException exception) {
            throw new NoSuchFileException(exception + "Error in getUserByName");
        }
        throw new NoSuchFileException("User is not found");
    }

    @Override
    public boolean addObject(Object o, ObjectMapper objectMapper, File file) {
        try {
            if (file != null && file.exists()) {
                ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
                arrayNode.insertPOJO(arrayNode.size() + 1, o);
                objectMapper.writeValue(file, arrayNode);
                return true;
            } else {
                objectMapper.writeValue(file, o);
            }
        } catch (IOException ex) {
            Logger.getLogger(CRUDServicesImpl.class.getSimpleName()).warning("Error in add User:" + ex);
        }
        return false;
    }

    @Override
    public boolean deleteById(String id, ObjectMapper objectMapper, File file) {
        try {
            ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
            objectMapper.writeValue(file, arrayNode.findParents("id")
                    .stream()
                    .filter(x -> !x.findValue("id").asText().equalsIgnoreCase(id)
                    )
                    .collect(Collectors.toList()));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CRUDServicesImpl.class.getSimpleName()).warning("Error in  delete User:" + ex);
        }
        return false;
    }

    @Override
    public boolean updateObject(Object o, String id, ObjectMapper objectMapper, File file) {
        if (this.deleteById(id, objectMapper, file) &&
                this.addObject(o, objectMapper, file)) {
            return true;
        }
        log.error("Error deleting and adding user!!!");
        return false;
    }


    @Override
    public ArrayNode getAllArrayNode(ObjectMapper objectMapper, File file) throws IOException {
        return (ArrayNode) objectMapper.readTree(file);
    }


    public List<Object> getAllByListUser(ObjectMapper objectMapper, File file) throws IOException {
        return List.of(objectMapper.readValue(file, User[].class));
    }


}
