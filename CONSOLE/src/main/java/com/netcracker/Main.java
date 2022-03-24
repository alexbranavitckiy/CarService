package com.netcracker;

import com.netcracker.errors.PersistException;
import com.netcracker.jdbc.services.impl.MasterDaoImpl;
import com.netcracker.menu.startMenu.StartMenu;
import com.netcracker.file.FileService;
import com.netcracker.user.Master;
import com.netcracker.user.Qualification;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class Main {

    public static void main(String[] arg) throws IOException, SQLException, PersistException {
        MasterDaoImpl masterDaoServices = new MasterDaoImpl();

        Master master2 = Master.builder()
                .mail("test")
                .description("test")
                .id(UUID.fromString("1c4b171a-391b-447b-9624-062693f27715"))
                .description("test")
                .qualificationEnum(Qualification.ELECTRICIAN.getId())
                .education("asdassda")
                .homeAddress("asdasdasd")
                .phone("asdasdasdasdsad")
                .password("test122112121212")
                .name("test")
                .login("test" + "2")
                .build();

        master2.setDescription("За альхалллу!!!!!!!");
        master2.setName("Alex.))");
        master2.setPassword("asdsadasd3");
        master2.setId(UUID.fromString("1c4b171a-391b-447b-9624-062693f27722"));

        masterDaoServices.update(master2);

        try (Scanner in = new Scanner(System.in)) {
            new FileService().initMethod();//initialization data input method run with empty files
            new StartMenu().run(in, "");
        }
    }

}

