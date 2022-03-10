package com.netcracker;

import com.netcracker.menu.startMenu.StartMenu;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.Impl.LoginServicesImpl;
import com.netcracker.servisec.LoginService;
import com.netcracker.servisec.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

@Slf4j
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] arg) throws IOException {
        // from java.util.Date to instant
        //   Date date=new Date(); date.setTime(13131331);
        //   Instant instanceNow2 = date.toInstant();
        //   System.out.println(instanceNow2);
        try (Scanner in = new Scanner(System.in)) {
            new FileService().initMethod();//initialization data input method run with empty files
            new StartMenu().run(in, "");
        }
    }
}

