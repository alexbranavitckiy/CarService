package com.netcracker.servisec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.user.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

public class FileService {

    private final String PATH_USER = "src/main/resources/user.json";
    private final String PATH_MASTER = "src/main/resources/master.json";
    private final String PATH_RECEIVER = "src/main/resources/receiver.json";
    private final String NOT_FOUND="User is not found";
    private final File user = new File(PATH_USER);
    private final File master = new File(PATH_MASTER);
    private final File receiver = new File(PATH_RECEIVER);


    public String getPATH_USER() {
        return PATH_USER;
    }

    public String getPATH_MASTER() {
        return PATH_MASTER;
    }

    public String getPATH_RECEIVER() {
        return PATH_RECEIVER;
    }

    public File getUserFile() {
        return user;
    }

    public File getMasterFile() {
        return master;
    }

    public File getReceiverFile() {
        return receiver;
    }

    public boolean isExistsUser() {
        return Files.exists(user.toPath());
    }

    public boolean isExistsMaster() {
        return Files.exists(master.toPath());
    }

    public boolean isExistsReceiver() {
        return Files.exists(receiver.toPath());
    }

    public String getNOT_FOUND() {
        return NOT_FOUND;
    }

    public void initMethod() throws IOException {//Data for the first launch of the application
        String test = "test";
        ObjectMapper objectMapper = new ObjectMapper();

        Client client = Client.builder()
                .password(test)
                .description(test)
                .id(UUID.randomUUID())
                .email(test)
                .login(test+"1")
                .name(test).roleuser(RoleUser.REGISTERED).build();
        objectMapper.writeValue(getUserFile(), List.of(client, client));

        Master master = Master.builder()
                .mail(test)
                .description(test)
                .id(UUID.randomUUID())
                .description(test)
                .password(test)
                .name(test)
                .login(test+"2")
                .build();
        objectMapper.writeValue(getMasterFile(), List.of(master, master));

        MasterReceiver masterReceiver = MasterReceiver.builder()
                .mail(test)
                .password(test)
                .description(test)
                .id(UUID.randomUUID())
                .name(test)
                .login(test+"3")
                .build();
        objectMapper.writeValue(getReceiverFile(), List.of(masterReceiver, masterReceiver));
    }


}
