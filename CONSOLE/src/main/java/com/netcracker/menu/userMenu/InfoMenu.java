package com.netcracker.menu.userMenu;

import com.netcracker.menu.Menu;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Scanner;

import static com.netcracker.servisec.FileService.CONTACT_INFORMATION;

@Slf4j
public class InfoMenu implements Menu {

  @Override
  public void preMessage(String nameMenu) {

  }

  @Override
  public void run(Scanner in, String parentsName) throws IOException {
    log.info(CONTACT_INFORMATION);
  }
}
