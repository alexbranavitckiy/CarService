package com.netcracker.menu.userMenu;

import com.netcracker.menu.Menu;

import java.io.IOException;
import java.util.Scanner;

import static com.netcracker.servisec.FileService.CONTACT_INFORMATION;

public class InfoMenu implements Menu {

    @Override
    public void preMessage(String nameMenu) {

    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        System.out.println(CONTACT_INFORMATION);
    }
}
