package com.netcracker;

import com.netcracker.menu.startMenu.StartMenu;
import com.netcracker.file.FileService;
import com.netcracker.jdbc.services.impl.ClientDaoImpl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {

  public static void main(String[] arg) throws IOException, SQLException {

    ClientDaoImpl clientDao=new ClientDaoImpl();
    System.out.println(clientDao.getAll());
    System.out.println(clientDao.getAll());
    System.out.println(clientDao.getAll());
    try (Scanner in = new Scanner(System.in)) {
      new FileService().initMethod();//initialization data input method run with empty files
      new StartMenu().run(in, "");
    }
  }

}

