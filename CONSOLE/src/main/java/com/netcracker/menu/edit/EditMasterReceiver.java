package com.netcracker.menu.edit;

import com.netcracker.menu.Menu;
import com.netcracker.servisec.Impl.masterReceiver.MasterReceiverServicesImpl;
import com.netcracker.servisec.MasterReceiverServices;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.MasterReceiver;

import java.io.IOException;
import java.util.Scanner;

public class EditMasterReceiver implements Menu {

    private MasterReceiver masterReceiver;
    private final StringBuilder stringNew = new StringBuilder(20);
    private MasterReceiverServices masterReceiverServices = new MasterReceiverServicesImpl();

    @Override
    public void preMessage(String parentsName) {
        System.out.println("Enter 1 " + parentsName);

    }

    public EditMasterReceiver() {
        this.masterReceiver = UserSession.getCloneMasterReceiverSession();
    }


    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        System.out.println("Name");
        if (this.edit(this.masterReceiver.getName(), in)) {
            this.masterReceiver.setName(stringNew.toString());
        }
        System.out.println("Descriptions");
        if (this.edit(this.masterReceiver.getDescription(), in)) {
            this.masterReceiver.setDescription(stringNew.toString());
        }
        System.out.println("Login");
        if (this.edit(this.masterReceiver.getLogin(), in)) {
            this.masterReceiver.setLogin(stringNew.toString());
        }
        System.out.println("Password");
        if (this.edit(this.masterReceiver.getPassword(), in)) {
            this.masterReceiver.setPassword(stringNew.toString());
        }
        System.out.println("Education");
        if (this.edit(this.masterReceiver.getEducation(), in)) {
            this.masterReceiver.setPassword(stringNew.toString());
        }
        System.out.println("HomeAddress");
        if (this.edit(this.masterReceiver.getHomeAddress(), in)) {
            this.masterReceiver.setHomeAddress(stringNew.toString());
        }
        System.out.println("Mail");
        if (this.edit(this.masterReceiver.getMail(), in)) {
            this.masterReceiver.setMail(stringNew.toString());
        }
        System.out.println("Phone");
        if (this.edit(this.masterReceiver.getPhone(), in)) {
            this.masterReceiver.setPhone(stringNew.toString());
        }
        if (masterReceiverServices.updateMaster(masterReceiver)) {
            System.out.println("Data entered successfully");
        } else
            System.out.println("An input error occurred while entering data. Retry data change");
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
