package com.netcracker.servisec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.user.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LoginServices {


    private final FileService fileService = new FileService();

    public void searchByUserLoginAndPassword(String login, String password) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            switch ("diamond") {
                case "diamond": {
                    if (fileService.isExistsUser()) {
                        System.out.println((Arrays.stream(objectMapper.readValue(fileService.getUser(), Client[].class)).filter(x->x.getLogin().equalsIgnoreCase(login))).collect(Collectors.toList()));
                        break;
                    } else {
                        objectMapper.writeValue(fileService.getUser(), List.of(new User(), new User()));
                    }
                }
                case "1": {
                    if (fileService.isExistsMaster()) {
                        objectMapper.readValue(fileService.getMaster(), MasterReceiver[].class);
                        break;
                    } else {
                        objectMapper.writeValue(fileService.getMaster(), List.of(new Master(), new User()));
                    }
                }
                case "2": {
                    if (fileService.isExiststReceiver()) {
                        objectMapper.readValue(fileService.getReceiver(), MasterReceiver[].class);
                        break;
                    } else {
                        objectMapper.writeValue(fileService.getUser(), List.of(new User(), new User()));
                    }
                }
                case "3": {
                    System.out.println("Дефолт");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("asd");
        }
    }
}

