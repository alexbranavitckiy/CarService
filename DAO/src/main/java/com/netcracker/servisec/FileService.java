package com.netcracker.servisec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.marka.CarClient;
import com.netcracker.user.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

@Slf4j
public class FileService {

    public static final String USER_PATH = "src/main/resources/user.json";
    public static final String MASTER_PATH = "src/main/resources/master.json";
    public static final String RECEIVER_PATH = "src/main/resources/receiver.json";
    public static final String ORDERS_PATH = "src/main/resources/orders.json";
    public static final String OUTFIT_PATH = "src/main/resources/outfit.json";
    public static final String NOT_FOUND = "User is not found";
    public static final String CONTACT_INFORMATION = "Contact Information: Address: Minsk, Gintovta st., 1, 3rd floor\nPhone:+375(33)330-89-79\nOpening hours:8.00-23.00";

    private final File user = new File(USER_PATH);
    private final File master = new File(MASTER_PATH);
    private final File receiver = new File(RECEIVER_PATH);
    private final File orders = new File(ORDERS_PATH);
    private final File outfit = new File(ORDERS_PATH);


    public File getUserFile() {
        return user;
    }

    public File getOrders() {
        return orders;
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

    public File getOutfit() {
        return outfit;
    }

    public void initMethod() throws IOException {//Data for the first launch of the application
        try {
            ObjectMapperServices.getObjectMapper().readTree(orders);
            ObjectMapperServices.getObjectMapper().readTree(user);
            ObjectMapperServices.getObjectMapper().readTree(master);
            ObjectMapperServices.getObjectMapper().readTree(receiver);
            ObjectMapperServices.getObjectMapper().readTree(outfit);
        } catch (FileNotFoundException e) {
            log.info("Creating init versions of files");
            init();
        }
    }


    public void init() throws IOException {
        
        ObjectMapperServices.getObjectMapper().writeValue(orders, "");
        ObjectMapperServices.getObjectMapper().writeValue(outfit, "");
        String test = "test";
        Set<CarClient> carClients = new HashSet<>();
        Client client = Client.builder()
                .carClients(carClients)
                .password(test)
                .description(test)
                .id(UUID.randomUUID())
                .email(test)
                .login(test + "1")
                .name(test).roleuser(RoleUser.REGISTERED).build();
        ObjectMapperServices.getObjectMapper().writeValue(getUserFile(), List.of(client));

        Master master = Master.builder()
                .mail(test)
                .description(test)
                .id(UUID.randomUUID())
                .description(test)
                .password(test)
                .name(test)
                .login(test + "2")
                .build();
        ObjectMapperServices.getObjectMapper().writeValue(getMasterFile(), List.of(master));

        MasterReceiver masterReceiver = MasterReceiver.builder()
                .mail(test)
                .password(test)
                .description(test)
                .id(UUID.randomUUID())
                .name(test)
                .login(test + "3")
                .build();
        ObjectMapperServices.getObjectMapper().writeValue(getReceiverFile(), List.of(masterReceiver));
    }

}
