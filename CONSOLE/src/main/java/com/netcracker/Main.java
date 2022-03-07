package com.netcracker;

import com.netcracker.menu.startMenu.StartMenu;
import com.netcracker.order.Order;
import com.netcracker.servisec.FileService;
import lombok.extern.slf4j.Slf4j;


import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class Main {
    public static void main(String[] arg) throws IOException {
        try (Scanner in = new Scanner(System.in)) {
            new FileService().initMethod();//method of entering init data
            new StartMenu().run(in, "");
        }
    }
}

