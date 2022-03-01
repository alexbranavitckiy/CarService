package servisec.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.netcracker.user.User;
import servisec.UserServices;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class UserServicesImpl implements UserServices {


    @Override
    public Optional<List<JsonNode>> getUserByName(String name, ObjectMapper objectMapper, File file) throws IOException {
        try {
            if (file != null && file.exists()) {
                ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
                return Optional.of(arrayNode.findParents("name")
                        .stream()
                        .filter(x -> x.findValue("name").asText().equalsIgnoreCase(name))
                        .collect(Collectors.toList()));
            }
        } catch (IOException exception) {
            throw new NoSuchFileException(exception + "Error in getUserByName");
        }
        throw new NoSuchFileException("User is not found");
    }

    @Override
    public boolean addUser(User user, ObjectMapper objectMapper, File file) {
        try {
            ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
            arrayNode.insertPOJO(arrayNode.size() + 1, user);
            objectMapper.writeValue(file, arrayNode);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(UserServicesImpl.class.getSimpleName()).warning("Error in add User:" + ex);
        }
        return false;
    }

    @Override
    public boolean deleteUserById(String id, ObjectMapper objectMapper, File file) {
        try {
            ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(file);
            objectMapper.writeValue(file, arrayNode.findParents("id")
                    .stream()
                    .filter(x -> !x.findValue("id").asText().equalsIgnoreCase(id)
                    )
                    .collect(Collectors.toList()));
            return true;
        } catch (IOException ex) {
            Logger.getLogger(UserServicesImpl.class.getSimpleName()).warning("Error in  delete User:" + ex);
        }
        return false;
    }

    @Override
    public ArrayNode getAllUserArrayNode(ObjectMapper objectMapper, File file) throws IOException {
        return (ArrayNode) objectMapper.readTree(file);
    }

    @Override
    public List<User> getAllUserByListUsers(ObjectMapper objectMapper, File file) throws IOException {
        return List.of(objectMapper.readValue(new File("src/main/resources/entry.json"), User[].class));
    }

}
