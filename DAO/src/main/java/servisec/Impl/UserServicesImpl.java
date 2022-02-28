package servisec.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.user.User;
import servisec.UserServices;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;


public class UserServicesImpl implements UserServices {



    @Override
    public Optional<User> getUserByName(String name, ObjectMapper objectMapper,File file) throws IOException {
        return Stream.of(objectMapper.readValue(file, User[].class)).filter(x -> x.getName().equalsIgnoreCase(name)).findFirst();
    }



}
