package com.netcracker.menu.edit;

import com.netcracker.menu.Menu;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.Client;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;


@Slf4j
public class EditClient implements Menu {

    private Client client;
    private final StringBuilder stringNew = new StringBuilder(20);

    @Override
    public void preMessage(String parentsName) {
        log.info("Enter 1 {}" , parentsName);

    }

    public EditClient() {
        this.client = UserSession.getCloneClientSession();
    }

    public EditClient(Client client) {
        this.client = UserSession.getCloneClient(client);
    }


    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        log.info("Descriptions");
        if (this.edit(this.client.getDescription(), in)) {
            this.client.setDescription(stringNew.toString());
        }
        log.info("Name");
        if (this.edit(this.client.getName(), in)) {
            this.client.setName(stringNew.toString());
        }
        log.info("Login");
        if (this.edit(this.client.getLogin(), in)) {
            this.client.setLogin(stringNew.toString());
        }
        log.info("Password");
        if (this.edit(this.client.getPassword(), in)) {
            this.client.setPassword(stringNew.toString());
        }
        log.info("Phone");
        if (this.edit(this.client.getPhone(), in)) {
            this.client.setPhone(stringNew.toString());
        }
        log.info("Email");
        if (this.edit(this.client.getEmail(), in)) {
            this.client.setPassword(stringNew.toString());
        }
    }

    public Client getClient() {
        return client;
    }


    public void changeMessage() {
        log.info("Enter 1 to skip");
        log.info("Enter 2 to edit");
    }


    public boolean edit(String fieldName, Scanner in) {
        log.info(fieldName);
        this.changeMessage();
        stringNew.delete(0, stringNew.length());
        if (in.next().equalsIgnoreCase("2")) {
            log.info("Enter values");
            stringNew.append(in.next());
            return true;
        }
        return false;
    }

}