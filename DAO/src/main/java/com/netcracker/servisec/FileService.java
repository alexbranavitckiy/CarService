package com.netcracker.servisec;

import java.io.File;
import java.nio.file.Files;

public class FileService {

    private final String PATH_USER = "src/main/resources/user.json";
    private final String PATH_MASTER = "src/main/resources/master.json";
    private final String PATH_RECEIVER = "src/main/resources/receiver.json";
    private final File user=new File(PATH_USER);
    private final File master=new File(PATH_MASTER);
    private final File receiver=new File(PATH_RECEIVER);


    public String getPATH_USER() {
        return PATH_USER;
    }

    public String getPATH_MASTER() {
        return PATH_MASTER;
    }

    public String getPATH_RECEIVER() {
        return PATH_RECEIVER;
    }

    public File getUser() {
        return user;
    }

    public File getMaster() {
        return master;
    }

    public File getReceiver() {
        return receiver;
    }

    public boolean isExistsUser() {
        return  Files.exists(user.toPath());
    }

    public boolean isExistsMaster() {
        return  Files.exists(master.toPath());
    }

    public boolean isExiststReceiver() {
        return Files.exists(receiver.toPath());
    }
}
