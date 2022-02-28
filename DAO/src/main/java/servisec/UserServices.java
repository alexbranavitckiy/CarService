package servisec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.user.User;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface UserServices {


    Optional<User> getUserByName(String name, ObjectMapper objectMapper, File file) throws IOException;






}
