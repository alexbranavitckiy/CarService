package com.netcracker.servisec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.netcracker.user.User;
import javassist.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CRUDServices {

    Optional<List<JsonNode>> getByName(String name,String fild, ObjectMapper objectMapper, File file) throws IOException, NotFoundException;

    boolean addObject(Object user, ObjectMapper objectMapper, File file);

    boolean deleteById(String id, ObjectMapper objectMapper, File file);

    ArrayNode getAllArrayNode(ObjectMapper objectMapper, File file) throws IOException;

    boolean updateObject(Object o, String id, ObjectMapper objectMapper, File file);
}
