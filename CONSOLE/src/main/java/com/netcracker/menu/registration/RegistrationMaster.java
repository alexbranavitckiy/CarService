package com.netcracker.menu.registration;

import com.netcracker.menu.Menu;
import com.netcracker.servisec.ClientServices;
import com.netcracker.servisec.Impl.client.ClientServicesImpl;
import com.netcracker.user.Master;
import com.netcracker.user.Role;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class RegistrationMaster implements Menu {

    private boolean flag = true;
    private final ClientServices clientServices = new ClientServicesImpl();


    @Override
    public void preMessage(String parentsName) {
        log.info("Enter 1 {}", parentsName);
        log.info("Enter 2 to continue registration");
    }

    @Override
    public void run(Scanner in, String parentsName) throws IOException {
        this.preMessage(parentsName);
        while (flag) {
            switch (in.next()) {
                case "2": {
                    Master master = new Master();
                    log.info("Enter login");
                    master.setLogin(in.next());
                    log.info("Enter password");
                    master.setPassword(in.next());
                    log.info("Enter phone");
                    master.setPhone(in.next());
                    log.info("Name");
                    master.setName(in.next());
                    log.info("Description");
                    master.setDescription(in.next());
                    log.info("Education");
                    master.setEducation(in.next());
                    log.info("Mail");
                    master.setMail(in.next());
                    log.info("Mail");
                    master.setOutfits(new ArrayList<>());
                    master.setRole(Role.MASTER);
                    //  master.setId(UUID.randomUUID())
                   //  if (clientServices.addObjectInClient(client)) {
                 //    System.out.println("User created successfully");
                 //    this.flag = false;
                //     } else {
                //     System.out.println("Invalid data. Repeat registration");
                //     this.preMessage(parentsName);
               //      }
                    break;
                }
                case "1": {
                    this.flag = false;
                    break;
                }
                default: {
                    this.preMessage(parentsName);
                    break;
                }
            }
        }
    }
}