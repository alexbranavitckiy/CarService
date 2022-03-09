package com.netcracker.menu.edit;

import com.netcracker.menu.Menu;
import com.netcracker.servisec.Impl.masterReceiver.MasterReceiverServicesImpl;
import com.netcracker.servisec.MasterReceiverServices;
import com.netcracker.servisec.UserSession;
import com.netcracker.user.MasterReceiver;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Scanner;

@Slf4j
public class EditMasterReceiver implements Menu {

    private MasterReceiver masterReceiver;
    private final StringBuilder stringNew = new StringBuilder(20);
    private MasterReceiverServices masterReceiverServices = new MasterReceiverServicesImpl();

    @Override
    public void preMessage(String parentsName) {
        log.info("Enter 1 {}", parentsName);

    }

    public EditMasterReceiver() {
        this.masterReceiver = UserSession.getCloneMasterReceiverSession();
    }


    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        log.info("Name");
        if (this.edit(this.masterReceiver.getName(), in)) {
            this.masterReceiver.setName(stringNew.toString());
        }
        log.info("Descriptions");
        if (this.edit(this.masterReceiver.getDescription(), in)) {
            this.masterReceiver.setDescription(stringNew.toString());
        }
        log.info("Login");
        if (this.edit(this.masterReceiver.getLogin(), in)) {
            this.masterReceiver.setLogin(stringNew.toString());
        }
        log.info("Password");
        if (this.edit(this.masterReceiver.getPassword(), in)) {
            this.masterReceiver.setPassword(stringNew.toString());
        }
        log.info("Education");
        if (this.edit(this.masterReceiver.getEducation(), in)) {
            this.masterReceiver.setPassword(stringNew.toString());
        }
        log.info("HomeAddress");
        if (this.edit(this.masterReceiver.getHomeAddress(), in)) {
            this.masterReceiver.setHomeAddress(stringNew.toString());
        }
        log.info("Mail");
        if (this.edit(this.masterReceiver.getMail(), in)) {
            this.masterReceiver.setMail(stringNew.toString());
        }
        log.info("Phone");
        if (this.edit(this.masterReceiver.getPhone(), in)) {
            this.masterReceiver.setPhone(stringNew.toString());
        }
        if (masterReceiverServices.updateMaster(masterReceiver)) {
            log.info("Data entered successfully");
        } else
            log.info("An input error occurred while entering data. Retry data change");
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
