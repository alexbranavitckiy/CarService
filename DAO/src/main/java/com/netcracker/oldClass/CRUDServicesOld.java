package com.netcracker.oldClass;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import javassist.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CRUDServicesOld {

    Optional<List<JsonNode>> getByName(String name,String fild, File file) throws IOException, NotFoundException;

    boolean addObject(Object user, File file);

    boolean deleteById(String id, File file);

    ArrayNode getAllArrayNode( File file) throws IOException;

    boolean updateObject(Object o, String id, File file) ;
}
