package com.netcracker.oldClass;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.ObjectMapperServices;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Slf4j
public class CRUDServicesImplOld implements CRUDServicesOld {//deprecated methods


  @Override
  public Optional<List<JsonNode>> getByName(String name, String field, File file)
      throws IOException {
    try {
      if (file != null && file.exists()) {
        ArrayNode arrayNode = (ArrayNode) ObjectMapperServices.getObjectMapper().readTree(file);
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
  public boolean addObject(Object o, File file) {
    try {
      if (file != null && file.exists()) {
        ArrayNode arrayNode = (ArrayNode) ObjectMapperServices.getObjectMapper().readTree(file);
        arrayNode.insertPOJO(arrayNode.size() + 1, o);
        ObjectMapperServices.getObjectMapper().writeValue(file, arrayNode);
        return true;
      } else {
        ObjectMapperServices.getObjectMapper().writeValue(file, o);
      }
    } catch (IOException ex) {
      log.warn("Error in add User:" + ex);
    }
    return false;
  }

  @Override
  public boolean deleteById(String id, File file) {
    try {
      ArrayNode arrayNode = (ArrayNode) ObjectMapperServices.getObjectMapper().readTree(file);
      List<JsonNode> clients = arrayNode.findParents("id")
          .stream()
          .filter(x -> !x.findValue("id").asText().equalsIgnoreCase(id)
          )
          .collect(Collectors.toList());
      System.out.println(clients);
      ObjectMapperServices.getObjectMapper().writeValue(file, clients);
      return true;
    } catch (IOException ex) {
      log.warn("Error in  delete User:", ex);
    }
    return false;
  }

  @Override
  public boolean updateObject(Object o, String id, File file) {
    if (this.deleteById(id, new File(FileService.USER_PATH))) {
      return true;
    }//&&this.addObject(o, new File(FileService.USER_PATH))
    log.error("Error deleting and adding user!!!");
    return false;
  }


  @Override
  public ArrayNode getAllArrayNode(File file) throws IOException {
    return (ArrayNode) ObjectMapperServices.getObjectMapper().readTree(file);
  }


}
