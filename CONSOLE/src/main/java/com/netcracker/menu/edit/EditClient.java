package com.netcracker.menu.edit;

import com.netcracker.marka.CarClient;
import com.netcracker.menu.Menu;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.Client;

import java.io.IOException;
import java.util.Scanner;

public class EditClient implements Menu {

    private Client client;
    private final StringBuilder stringNew = new StringBuilder(20);

    @Override
    public void preMessage(String parentsName) {
        System.out.println("Enter 1 " + parentsName);

    }

    public EditClient() {
        this.client = UserSession.getCloneClientSession();
    }


    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        System.out.println("Descriptions");
        if (this.edit(this.client.getDescription(), in)) {
            this.client.setDescription(stringNew.toString());
        }
        System.out.println("Name");
        if (this.edit(this.client.getName(), in)) {
            this.client.setName(stringNew.toString());
        }
        System.out.println("Login");
        if (this.edit(this.client.getLogin(), in)) {
            this.client.setLogin(stringNew.toString());
        }
        System.out.println("Password");
        if (this.edit(this.client.getPassword(), in)) {
            this.client.setPassword(stringNew.toString());
        }
        System.out.println("Phone");
        if (this.edit(this.client.getPhone(), in)) {
            this.client.setPhone(stringNew.toString());
        }
        System.out.println("Email");
        if (this.edit(this.client.getEmail(), in)) {
            this.client.setPassword(stringNew.toString());
        }
    }

    public Client getClient() {
        return client;
    }


    public void changeMessage() {
        System.out.println("Enter 1 to skip");
        System.out.println("Enter 2 to edit");
    }


    public boolean edit(String fieldName, Scanner in) {
        System.out.println(fieldName);
        this.changeMessage();
        stringNew.delete(0, stringNew.length());
        if (in.next().equalsIgnoreCase("2")) {
            System.out.println("Enter values");
            stringNew.append(in.next());
            return true;
        }
        return false;
    }

}