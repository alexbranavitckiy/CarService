package com.netcracker;

import com.netcracker.menu.errors.InvalidValuesException;
import com.netcracker.menu.startMenu.StartMenu;
import com.netcracker.servisec.FileService;
import com.netcracker.servisec.UserSession;


import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] arg) throws IOException {

        Scanner in = new Scanner(System.in);
        new FileService().initMethod();//method of entering init data
        new StartMenu().run(in, "");
        in.close();
    }
}

