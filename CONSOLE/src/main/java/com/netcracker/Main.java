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
import java.util.Scanner;

@Slf4j
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] arg) throws IOException {

        // LoginService loginService=new LoginServicesImpl();
        //        loginService.searchByUserLoginAndPassword("alex","alex");
        //        System.out.println(UserSession.getClientSession());
        //
        //        loginService.searchByUserLoginAndPassword("ale12x","al2ex");
        //        System.out.println(UserSession.getClientSession());

        try (Scanner in = new Scanner(System.in)) {
            new FileService().initMethod();//initialization data input method run with empty files
            new StartMenu().run(in, "");
        }
    }
}

