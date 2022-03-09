package com.netcracker;

import com.netcracker.menu.startMenu.StartMenu;
import com.netcracker.servisec.FileService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] arg) throws IOException {
        try (Scanner in = new Scanner(System.in)) {
            new FileService().initMethod();//initialization data input method run with empty files
            new StartMenu().run(in, "");
        }


    }
}

