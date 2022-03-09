package com.netcracker;

import com.netcracker.menu.startMenu.StartMenu;
import com.netcracker.servisec.FileService;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] arg) throws IOException {

        log.debug("SAD");
        log.info("122133123");


        try (Scanner in = new Scanner(System.in)) {
            new FileService().initMethod();//initialization data input method run with empty files
            new StartMenu().run(in, "");
        }
    }
}

