package servisec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.netcracker.user.User;
import javassist.NotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserServices {


    Optional<List<JsonNode>> getUserByName(String name, ObjectMapper objectMapper, File file) throws IOException, NotFoundException;

    boolean addUser(User user, ObjectMapper objectMapper, File file);

    boolean deleteUserById(String id, ObjectMapper objectMapper, File file);

    ArrayNode getAllUserArrayNode(ObjectMapper objectMapper, File file) throws IOException;

    List<User> getAllUserByListUsers(ObjectMapper objectMapper, File file) throws IOException;


}
