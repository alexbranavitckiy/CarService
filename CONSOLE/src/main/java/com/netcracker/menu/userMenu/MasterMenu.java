package com.netcracker.menu.userMenu;

import com.netcracker.menu.Menu;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class MasterMenu implements Menu {

    @Override
    public void preMessage(String nameMenu) {

    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        // TODO add here code!!!
        log.info("MasterMenu");

    }
}
