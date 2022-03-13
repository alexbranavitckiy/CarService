package com.netcracker;

import com.netcracker.menu.startMenu.StartMenu;
import com.netcracker.servisec.FileService;

import java.io.IOException;
import java.util.Scanner;


public class Main {

  public static void main(String[] arg) throws IOException {
    try (Scanner in = new Scanner(System.in)) {
      new FileService().initMethod();//initialization data input method run with empty files
      new StartMenu().run(in, "");
    }
  }
}

