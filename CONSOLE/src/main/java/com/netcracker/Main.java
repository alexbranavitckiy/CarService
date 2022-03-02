package com.netcracker;

import com.netcracker.menu.startMenu.StartMenu;


import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] arg) throws IOException {
        Scanner in = new Scanner(System.in);
        new StartMenu().run(in, "");
        in.close();
    }
}

