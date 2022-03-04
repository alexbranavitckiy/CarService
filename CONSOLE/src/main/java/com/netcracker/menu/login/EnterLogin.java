package com.netcracker.menu.login;

import com.netcracker.menu.Menu;
import com.netcracker.menu.userMenu.ClientMenu;
import com.netcracker.menu.userMenu.MasterMenu;
import com.netcracker.menu.userMenu.MasterReceiverMenu;
import com.netcracker.servisec.UserSession;

import java.io.IOException;
import java.util.Scanner;

public class EnterLogin implements Menu {

    private boolean flag = true;

    EnterLogin() {

    }

    @Override
    public void preMessage(String parentsName) {

    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        if (UserSession.getMasterSession().isPresent()) {
            new MasterMenu().run(in, "");
        }
        if (UserSession.getMasterReceiverSession().isPresent()) {
            new MasterReceiverMenu().run(in, "");
        }
        if (UserSession.getClientSession().isPresent()) {
            new ClientMenu().run(in, "");
        }
    }
}
